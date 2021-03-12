package com.example.learning.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class FinishDelegate extends LoggedDelegate {

    private final Logger log = Logger.getLogger(LoggedDelegate.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("\n\n<------ FINISHED IN " + execution.getVariable("guess_count").toString() + " GUESSES ------>\n\n");
    }
}
