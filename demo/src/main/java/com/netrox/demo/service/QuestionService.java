package com.netrox.demo.service;

import com.netrox.demo.model.Answer;
import com.netrox.demo.model.Question;
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
public class QuestionService {  // answer service bi samo imao pomocne fje koje bi mu qustion service koristio jer doslovno answer bez servisa nema smisla
    @Autowired
    private QuestionRepo rep;

    public Question getQuestionById (Long id)
    {
        //QuestionModel questionModel =  rep.findByIdAndDeletedIsFalse(id);
        //Test questionModel = rep.findTestWithIdOne();
        Question question =  rep.findByKurac();


        //questionModel.getAnswers().remove(neki koji su sa false) TO BI BIO RUZNI NACIN, TREBA QUERY NAPRAVITI U PITANJIMA!
        if(question != null) return question;
        else return  null; // kasnije ubaciti izuzetke neke kada ih budes ucio ovdje koristiti...*/

    }

    public List<Question> getAllQuestions()
    {
        return rep.findAllByDeletedIsFalse();
    }

    public Question saveQuestion(Question question)
    {
        if(question.getId()!=null)  // post se htio koristiti za apdejt nekog pitanja
        {
            Optional<Question> questionModelOptional = rep.findById(question.getId());
            if(questionModelOptional.isPresent())  return rep.save(question);
            else return null;  // posalje u body za apdejt pitanja nepostojeci id pitanja!
        }
        else return rep.save(question); // post se htio koristiti za kreiranje novog
    }

    public Question saveAnswer (Long id, Answer answer) // sinoc dodano za FK vezu
    {
        Question question =  rep.findByIdAndDeletedIsFalse(id);
        // DODATNI NEW OVAJ IZUZETAK KAD POSALJE ID KOJI NE POSTOJI PITANJE. ( ili je obrisano ?)
        //eventualno se tu vrsi provjera i da li veci ima previse pitanaj povezanih na njega te da li ima previse tacnih odgovora.
        question.getAnswers().add(answer);
        answer.setQuestion(question);
        return rep.save(question);
    }

    public ResponseEntity<Long> deleteQuestion(Map<String,String> queryParams) // sacuvano za primjer rada sa parametrima u ruti.
    {
        Long id = parseLong(queryParams.get("id"));
        // trebalo bi provjeriti da li postoji id parametar bacat izuzetak itd

        Optional<Question> questionModelOptional = rep.findById(id);
        if(questionModelOptional.isPresent())
        {
            Question question = questionModelOptional.get();
            //rep.delete(demo);  NECEMO TAJ DELETE NEGO SOFT DELETE
            question.setDeleted(true);
            rep.save(question);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
