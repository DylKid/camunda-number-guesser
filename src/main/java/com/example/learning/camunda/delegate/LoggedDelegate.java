package com.example.learning.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Random;
import java.util.logging.Logger;

public abstract class LoggedDelegate implements JavaDelegate {

    private final Logger log = Logger.getLogger(LoggedDelegate.class.getName());

    private final Boolean randomDelay = false;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("\n\n ...delegate invoked by "
         + "\n processDefinitionId=" + execution.getProcessDefinitionId()
         + "\n activityId=" + execution.getCurrentActivityId()
         + "\n activityName=" + execution.getCurrentActivityName()
         + "\n businessKey=" + execution.getBusinessKey()
         + "\n executionId=" + execution.getId()
         + "\n random_number=" + execution.getVariable("random_number")
         + "\n current_guess=" + execution.getVariable("current_guess")
         + "\n guess_result=" + execution.getVariable("guess_result")
         + "\n guess_count" + execution.getVariable("guess_count")
         + "\n\n"
        );
        if(randomDelay) {
            Thread.sleep(100 * new Random().nextInt(10));
        }
    }
}
