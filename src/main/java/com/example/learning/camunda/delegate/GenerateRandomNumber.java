package com.example.learning.camunda.delegate;

import com.example.learning.config.YAMLConfig;
import com.example.learning.model.CamundaVariableNames;
import com.example.learning.service.RandomNumberService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Component
public class GenerateRandomNumber extends LoggedDelegate {

    @Autowired
    RandomNumberService randomNumberService;

    private String test;

    @Override
    public void execute(DelegateExecution e) throws Exception {
        super.execute(e);
        e.setVariable(CamundaVariableNames.RANDOM_NUMBER, randomNumberService.getRandomNumber());
    }
}
