package com.netrox.demo;

import com.netrox.demo.model.Question;
import com.netrox.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@Configuration
@EnableJpaAuditing
public class DemoApplication {


    public static void main(String[] args) {
        QuestionService kurcina = new QuestionService();
        Question qustion = new Question();
        qustion.setId(1L);
        qustion.setQuestion("asime konju");
        kurcina.saveQuestion(qustion);


        SpringApplication.run(DemoApplication.class, args);
    }


}



/*
@RestController
@RequestMapping(path = "/")
class DemoController{
    @RequestMapping(value="print/{msg}",method = RequestMethod.GET)
    public String print(@PathVariable(value="") String msg ){
        return "Hello From " + msg;
    }
}*/