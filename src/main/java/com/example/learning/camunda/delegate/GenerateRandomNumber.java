package com.example.learning.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateRandomNumber extends LoggedDelegate {

    @Override
    public void execute(DelegateExecution e) throws Exception {
        super.execute(e);
        e.setVariable("random_number", new Random().nextInt(100));
    }
}
