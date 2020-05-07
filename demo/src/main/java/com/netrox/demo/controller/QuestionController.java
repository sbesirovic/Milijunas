package com.netrox.demo.controller;

import com.netrox.demo.model.Answer;
import com.netrox.demo.model.Question;
import com.netrox.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path="/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Long id)
    {
        System.out.println("id je: "+id);

        return questionService.getQuestionById(id);
    }

    @GetMapping
    public List<Question> getAllQuestions( )
    {
        return questionService.getAllQuestions();
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question rbdm )
    {
      return questionService.saveQuestion(rbdm);
    }

    @PostMapping(path="/{id}") // dodano zadnje za foreign key vezu
    public Question addAnswer(@PathVariable(value = "id") Long id, @RequestBody Answer aMdm )
    {
        return questionService.saveAnswer(id,aMdm);
    }


    //@DeleteMapping(value = "/posts/{id}") ako ne zelim preko parametara sa onim ?id = ... itd.
    @DeleteMapping
    public ResponseEntity<Long> deleteQuestion(@RequestParam Map<String, String> customQuery)
    {
        return questionService.deleteQuestion(customQuery);
    } //System.out.println("customQuery = brand " + customQuery.containsKey("id"));

}

// PUT I POST RAZLIKE