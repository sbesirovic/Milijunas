package com.netrox.demo.service;

import com.netrox.demo.model.AnswerModel;
import com.netrox.demo.model.QuestionModel;
import com.netrox.demo.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo rep;

    public QuestionModel getQuestionById (Long id)
    {
        QuestionModel questionModel =  rep.findByIdAndDeleted(id,false);
        if(questionModel != null) return questionModel;
        else return  null; // kasnije ubaciti izuzetke neke kada ih budes ucio ovdje koristiti...
    }

    public List<QuestionModel> getAllQuestions()
    {
        return rep.findAllByDeleted(false);
    }

    public QuestionModel saveQuestion(QuestionModel question)
    {
        if(question.getId()!=null)
        {
            Optional<QuestionModel> questionModelOptional = rep.findById(question.getId());
            if(questionModelOptional.isPresent())  return rep.save(question);
            else return null;
        }
        else return rep.save(question);
    }

    public QuestionModel saveAnswer (Long id, AnswerModel answerModel) // sinoc dodano za FK vezu
    {
        QuestionModel questionModel =  rep.findByIdAndDeleted(id,false);
        questionModel.getAnswers().add(answerModel);
        return rep.save(questionModel);
    }

    public ResponseEntity<Long> deleteQuestion(Map<String,String> queryParams)
    {
        Long id = parseLong(queryParams.get("id"));
        // trebalo bi provjeriti da li postoji id parametar bacat izuzetak itd

        Optional<QuestionModel> questionModelOptional = rep.findById(id);
        if(questionModelOptional.isPresent())
        {
            QuestionModel questionModel = questionModelOptional.get();
            //rep.delete(demo);  NECEMO TAJ DELETE NEGO SOFT DELETE
            questionModel.setDeleted(true);
            rep.save(questionModel);
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
