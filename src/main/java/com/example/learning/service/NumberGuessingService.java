package com.example.learning.service;

import com.example.learning.model.GuessResult;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGuessingService {

    public Integer guessNewNumber(Integer currentGuess, GuessResult guessResult) {
        Integer newGuess = currentGuess;
        // first guess, initialize guess and guess count else a guess has already been made,
        // make a new guess based on previous result
        if(newGuess == null) {
            newGuess = new Random().nextInt(100);
        } else {
            if(guessResult == GuessResult.HIGHER) {
                newGuess = currentGuess + (new Random().nextInt(10) + 1);
            } else {
                newGuess = currentGuess - (new Random().nextInt(10) + 1);
            }
        }
        return newGuess;
    }


}
