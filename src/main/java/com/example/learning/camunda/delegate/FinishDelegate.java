package com.example.learning.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class FinishDelegate extends LoggedDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("<------ FINISHED IN " + execution.getVariable("guess_count").toString() + " GUESSES ------>");
    }
}
