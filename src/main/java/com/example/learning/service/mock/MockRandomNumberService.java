package com.example.learning.service.mock;

import com.example.learning.service.RandomNumberService;

public class MockRandomNumberService implements RandomNumberService {

    @Override
    public Integer getRandomNumber() {
        return 100;
    }
}
