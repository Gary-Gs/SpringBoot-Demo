package com.codetest.springbootapi.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TestService {

    public int generateRandomNumber() {
        return new Random().nextInt(10);
    }
}
