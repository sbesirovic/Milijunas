package com.netrox.demo.service;

import com.netrox.demo.model.DemoModel;
import com.netrox.demo.repository.DemoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoService {
    @Autowired
    private DemoRepo rep;

    public String printService()
    {
        Optional<DemoModel> demoModelOptional = rep.findById(1L);
        if(demoModelOptional.isPresent())
        {
            return demoModelOptional.get().getName();
        }
        else return "kurac";
    }
}
