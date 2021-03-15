package com.example.learning.service;

import com.example.learning.config.YAMLConfig;
import com.example.learning.model.GuessResult;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGuessingServiceImpl implements NumberGuessingService{

    @Autowired
    YAMLConfig yamlConfig;

    public Integer guessNewNumber(Integer currentGuess, GuessResult guessResult) {
        Integer newGuess = currentGuess;
        // first guess, initialize guess and guess count else a guess has already been made,
        // make a new guess based on previous result
        if(newGuess == null) {
            newGuess = new Random().nextInt(yamlConfig.randomNumberRange());
        } else {
            if(guessResult == GuessResult.HIGHER) {
                newGuess = currentGuess + (new Random().nextInt(yamlConfig.getNumberGuessingRange()) + 1);
            } else {
                newGuess = currentGuess - (new Random().nextInt(yamlConfig.getNumberGuessingRange()) + 1);
            }
        }
        if(yamlConfig.getNumberGuessingDelay() > 0) {
            try {
                Thread.sleep(yamlConfig.getNumberGuessingDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return newGuess;
    }
}
