package com.example.learning.camunda.delegate;

import com.example.learning.model.GuessResult;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.util.Random;

public class MakeGuessDelegate extends LoggedDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        // get variables
        Integer currentGuess = (Integer) execution.getVariable("current_guess");
        Integer guessCount = (Integer) execution.getVariable("guess_count");
        // first guess, initialize guess and guess count
        if(currentGuess == null) {
            currentGuess = new Random().nextInt(100);
            guessCount = 1;
        // a guess has already been made, make a new guess based on previous result and increment guess count
        } else {
            GuessResult guessResult = (GuessResult) execution.getVariable("guess_result");
            if(guessResult == GuessResult.HIGHER) {
                currentGuess = currentGuess + (new Random().nextInt(10) + 1);
            } else {
                currentGuess = currentGuess - (new Random().nextInt(10) + 1);
            }
            guessCount++;
        }
        // set variables
        execution.setVariable("guess_count", guessCount);
        execution.setVariable("current_guess", currentGuess);
    }
}
