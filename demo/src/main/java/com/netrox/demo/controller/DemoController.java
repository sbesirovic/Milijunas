package com.netrox.demo.controller;

import com.netrox.demo.model.DemoModel;
import com.netrox.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path="/questions")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping(path = "/{id}")
    public DemoModel getQuestionById(@PathVariable(value = "id") Long id)
    {
        return demoService.getQuestionById(id);
    }

    @GetMapping
    public List<DemoModel> getAllQuestions( )
    {
        return demoService.getAllQuestions();
    }

    @PostMapping
    public DemoModel addQuestion(@RequestBody DemoModel rbdm )
    {
      return demoService.saveQuestion(rbdm);
    }

    //@DeleteMapping(value = "/posts/{id}") ako ne zelim preko parametara sa onim ?id = ... itd.
    @DeleteMapping
    public ResponseEntity<Long> deletePost(@RequestParam Map<String, String> customQuery)
    {
        return demoService.deleteQuestion(customQuery);
    } //System.out.println("customQuery = brand " + customQuery.containsKey("id"));


    /*  ###TESTED###
        @GetMapping
        public String print( ){
            return demoService.printService();
        }

        @GetMapping (path = "/testGet")
        public String printSEC( ){
            System.out.println("usao u hello");
            return "Hello";
        }
    */
}

// PUT I POST RAZLIKE