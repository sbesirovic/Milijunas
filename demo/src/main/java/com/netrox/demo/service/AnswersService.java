package com.netrox.demo.service;

import com.netrox.demo.model.AnswerModel;
import com.netrox.demo.repository.AnswersRepo;
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

    public AnswerModel getAnswerById(Long id)
    {
            AnswerModel answerModel= rep.findByIdAndDeleten(id,false);
            if(answerModel !=null) return answerModel;
            else return null;
    }

    public List<AnswerModel> getAllAnswers()
    {
        return rep.findAllByDeleten(false);
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
        AnswerModel answerModel = rep.findByIdAndDeleten(id,false);
        if(answerModel != null)
        {
            answerModel.setDeleten(true);
            rep.save(answerModel); // BITNO BA
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
