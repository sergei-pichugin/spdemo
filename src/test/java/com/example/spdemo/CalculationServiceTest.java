package com.example.spdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculationServiceTest {

    @Autowired
    private CalculationService service;

    @Test
    void shouldFindFirstMin() {
        // When
        int result = service.findMin(new int[]{20, 1, -2}, 1);

        // Then
        assertEquals(-2, result);
    }

    @Test
    void shouldFindSecondMin() {
        // When
        int result = service.findMin(new int[]{10, 20, -2}, 2);

        // Then
        assertEquals(10, result);
    }
}