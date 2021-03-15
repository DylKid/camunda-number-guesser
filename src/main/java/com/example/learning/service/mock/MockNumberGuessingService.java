package com.example.learning.service.mock;

import com.example.learning.model.GuessResult;
import com.example.learning.service.NumberGuessingService;

public class MockNumberGuessingService implements NumberGuessingService {
    @Override
    public Integer guessNewNumber(Integer currentGuess, GuessResult guessResult) {
        return 100;
    }
}
