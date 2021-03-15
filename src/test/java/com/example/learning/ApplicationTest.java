package com.example.learning;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    public RuntimeService runtimeService;

    @Test
    public void shouldGoStraightThroughIfICheat() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("number_guessing");
    }
}
