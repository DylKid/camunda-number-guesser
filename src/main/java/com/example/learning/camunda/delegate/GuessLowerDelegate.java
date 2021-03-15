package com.example.learning.camunda.delegate;

import com.example.learning.model.CamundaVariableNames;
import com.example.learning.model.GuessResult;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class GuessLowerDelegate extends LoggedDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        execution.setVariable(CamundaVariableNames.GUESS_RESULT, GuessResult.LOWER);
    }
}
