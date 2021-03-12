package com.example.learning.camunda.delegate;

import com.example.learning.model.GuessResult;
import com.example.learning.service.NumberGuessingService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MakeGuessDelegate extends LoggedDelegate {

    @Autowired
    private NumberGuessingService numberGuessingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        // get variables
        Integer currentGuess = (Integer) execution.getVariable("current_guess");
        Integer guessCount = (Integer) execution.getVariable("guess_count");
        GuessResult guessResult = (GuessResult) execution.getVariable("guess_result");
        // get new guess from service
        Integer newGuess = numberGuessingService.guessNewNumber(currentGuess, guessResult);
        // set variables
        if(guessCount == null) {
            guessCount = 0;
        }
        execution.setVariable("guess_count", guessCount+1);
        execution.setVariable("current_guess", newGuess);
    }
}
