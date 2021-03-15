package com.example.learning.config;

import com.example.learning.service.NumberGuessingService;
import com.example.learning.service.RandomNumberService;
import com.example.learning.service.mock.MockNumberGuessingService;
import com.example.learning.service.mock.MockRandomNumberService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    @Primary
    public NumberGuessingService numberGuessingService() {
        return new MockNumberGuessingService();
    }

    @Bean
    @Primary
    public RandomNumberService randomNumberService() {
        return new MockRandomNumberService();
    }
}
