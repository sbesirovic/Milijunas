package com.netrox.demo.controller;

import com.netrox.demo.model.AnswerModel;
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
    public List<AnswerModel> getAllAnswers()
    {
        return answersService.getAllAnswers();
    }

    @GetMapping(path="/{id}")
    public AnswerModel getAnswerById(@PathVariable (value="id") Long id)
    {
        return answersService.getAnswerById(id);
    }

    @PostMapping
    public AnswerModel addAnswer(@RequestBody AnswerModel answerModel )
    {
        return answersService.saveAnswer(answerModel);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Long> deleteAnswer(@PathVariable(value="id")Long id)
    {
        return answersService.deleteAnswer(id);
    }




}
