package com.example.learning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YAMLConfig {

    @Value("${number-guessing-delay}")
    private String numberGuessingDelay;

    @Value("${number-guessing-range}")
    private String numberGuessingRange;

    @Value("${random-number-range}")
    private String randomNumberRange;

    public Integer getNumberGuessingDelay() {
        return Integer.valueOf(numberGuessingDelay);
    }

    public Integer getNumberGuessingRange() {
        return Integer.valueOf(numberGuessingRange);
    }

    public Integer randomNumberRange() {
        return Integer.valueOf(randomNumberRange);
    }
}
