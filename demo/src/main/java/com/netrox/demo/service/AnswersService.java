package com.netrox.demo.service;

import com.netrox.demo.model.AnswerModel;
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
    private QuestionRepo repDem;

    public AnswerModel getAnswerById(Long id)
    {
            AnswerModel answerModel= rep.findByIdAndDeleted(id,false);
            if(answerModel !=null) return answerModel;
            else return null;
    }

    public List<AnswerModel> getAllAnswers()
    {
        return rep.findAllByDeleted(false);
    }

    public AnswerModel saveAnswer (AnswerModel answerModel)
    {
        if(answerModel.getId() != null)
        {
            Optional<AnswerModel> answerModelOptional = rep.findById(answerModel.getId());
            if(answerModelOptional.isPresent()) return rep.save(answerModel);
            else return null;
        }
        else return rep.save(answerModel);

    }

    public ResponseEntity<Long> deleteAnswer (Long id)
    {
        AnswerModel answerModel = rep.findByIdAndDeleted(id,false);
        if(answerModel != null)
        {
            answerModel.setDeleted(true);
            rep.save(answerModel); // BITNO BA
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
