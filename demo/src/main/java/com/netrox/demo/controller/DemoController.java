package com.netrox.demo.controller;

import com.netrox.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/milioner")
public class DemoController {
        @Autowired
        private DemoService demoService;
        @GetMapping
        public String print( ){
            return demoService.printService();
        }

        @GetMapping (path = "/sepy")
        public String printSEC( ){
            return "Hello";
        }
}

