package com.example.learning.service;

import com.example.learning.config.YAMLConfig;
import com.example.learning.model.CamundaVariableNames;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberServiceImpl implements RandomNumberService {

    @Autowired
    YAMLConfig yamlConfig;

    public Integer getRandomNumber()  {
        return new Random().nextInt(yamlConfig.randomNumberRange());
    }
}
