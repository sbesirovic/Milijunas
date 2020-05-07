package com.netrox.demo.service;

import com.netrox.demo.model.Answer;
import com.netrox.demo.repository.AnswersRepo;
import com.netrox.demo.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswersService {
    @Autowired
    private AnswersRepo rep;
    @Autowired
    private QuestionRepo questionRepo;

    public Answer getAnswerById(Long id)
    {
            Answer answer = rep.findByIdAndDeletedIsFalse(id);
            if(answer !=null) return answer;
            else return null;
    }

    public List<Answer> getAllAnswers()
    {
        return rep.findAllByDeletedIsFalse();
    }

    public Answer saveAnswer (Answer answer) // Ne koristi se vise jer se odgovor dodaje uz pitanje.
    {
        if(answer.getId() != null)
        {
            Optional<Answer> answerModelOptional = rep.findById(answer.getId());
            if(answerModelOptional.isPresent()) return rep.save(answer);
            else return null;
        }
        else return rep.save(answer);
    }

    public ResponseEntity<Long> deleteAnswer (Long id) // Ima li sad ovo smisla jer se nece obrisati iz liste odg pitanja ?
    {
        Answer answer = rep.findByIdAndDeletedIsFalse(id);
        if(answer != null)
        {
            answer.setDeleted(true);
            rep.save(answer); // BITNO BA
            //Optional<QuestionModel> optionalQuestionModel = questionRepo.findById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
