package com.netrox.demo.service;

import com.netrox.demo.model.DemoModel;
import com.netrox.demo.repository.DemoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class DemoService {
    @Autowired
    private DemoRepo rep;


    public DemoModel getQuestionById (Long id)
    {
        DemoModel demoModel =  rep.findByIdAndDeleten(id,false);
        if(demoModel != null) return demoModel;
        else return  null; // kasnije ubaciti izuzetke neke kada ih budes ucio ovdje koristiti...
    }

    public List<DemoModel> getAllQuestions()
    {
        return rep.findAllByDeleten(false);
    }

    public DemoModel saveQuestion(DemoModel question)
    {
        if(question.getId()!=null)
        {
            Optional<DemoModel> demoModelOptional = rep.findById(question.getId());
            if(demoModelOptional.isPresent())  return rep.save(question);
            else return null;
        }
        else return rep.save(question);
    }

    public ResponseEntity<Long> deleteQuestion(Map<String,String> queryParams)
    {
        Long id = parseLong(queryParams.get("id"));
        // trebalo bi provjeriti da li postoji id parametar bacat izuzetak itd

        Optional<DemoModel> demoModelOptional = rep.findById(id);
        if(demoModelOptional.isPresent())
        {
            DemoModel demo = demoModelOptional.get();
            //rep.delete(demo);  NECEMO TAJ DELETE NEGO SOFT DELETE
            demo.setDeleten(true);
            rep.save(demo);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    /*
    public String printService()
    {
        Optional<DemoModel> demoModelOptional = rep.findById(1L);
        if(demoModelOptional.isPresent())
        {
            return demoModelOptional.get().getQuestion();
        }
        else return "Nije pronadeno pod IDom 1 nista";
    }
    */
}
