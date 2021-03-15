package com.example.learning.service;

import com.example.learning.model.GuessResult;

public interface NumberGuessingService {
    Integer guessNewNumber(Integer currentGuess, GuessResult guessResult);
}
