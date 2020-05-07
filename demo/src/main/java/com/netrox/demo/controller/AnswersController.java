package com.netrox.demo.controller;

import com.netrox.demo.model.Answer;
import com.netrox.demo.service.AnswersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/answers")
public class AnswersController {
    @Autowired
    private AnswersService answersService;

    @GetMapping
    public List<Answer> getAllAnswers()
    {
        return answersService.getAllAnswers();
    }

    @GetMapping(path="/{id}")
    public Answer getAnswerById(@PathVariable (value="id") Long id)
    {
        return answersService.getAnswerById(id);
    }

    /*@PostMapping    RUTA POSTALA NEBITNA JER SE U PITANJE RUTI DODAJE ODGOVOR TACNO ZA TO PITANJE DA SE ADD U LISTU  i automatski se kreira vezom i orm.
    public AnswerModel addAnswer(@RequestBody AnswerModel answerModel )
    {
        return answersService.saveAnswer(answerModel);
    }*/

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Long> deleteAnswer(@PathVariable(value="id")Long id)
    {
        return answersService.deleteAnswer(id);
    }


}
