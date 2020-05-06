package com.netrox.demo.controller;

import com.netrox.demo.model.AnswerModel;
import com.netrox.demo.model.QuestionModel;
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
    public QuestionModel getQuestionById(@PathVariable(value = "id") Long id)
    {
        return questionService.getQuestionById(id);
    }

    @GetMapping
    public List<QuestionModel> getAllQuestions( )
    {
        return questionService.getAllQuestions();
    }

    @PostMapping
    public QuestionModel addQuestion(@RequestBody QuestionModel rbdm )
    {
      return questionService.saveQuestion(rbdm);
    }

    @PostMapping(path="/{id}") // dodano zadnje za foreign key vezu
    public QuestionModel addQuestion(@PathVariable(value = "id") Long id, @RequestBody AnswerModel aMdm )
    {
        return questionService.saveAnswer(id,aMdm);
    }


    //@DeleteMapping(value = "/posts/{id}") ako ne zelim preko parametara sa onim ?id = ... itd.
    @DeleteMapping
    public ResponseEntity<Long> deleteQuestion(@RequestParam Map<String, String> customQuery)
    {
        return questionService.deleteQuestion(customQuery);
    } //System.out.println("customQuery = brand " + customQuery.containsKey("id"));


    /*  ###TESTED###
        @GetMapping
        public String print( ){
            return questionService.printService();
        }

        @GetMapping (path = "/testGet")
        public String printSEC( ){
            System.out.println("usao u hello");
            return "Hello";
        }
    */
}

// PUT I POST RAZLIKE