package com.codetest.springbootapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @InjectMocks
    TestService testService;

    @Test
    void generateRandomNumber() {
        assertThat(testService.generateRandomNumber()).isLessThan(10);
    }
}