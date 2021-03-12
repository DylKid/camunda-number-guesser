package com.example.learning.camunda.delegate;

import com.example.learning.model.GuessResult;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public class GuessHigherDelegate extends LoggedDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        execution.setVariable("guess_result", GuessResult.HIGHER);
    }
}
