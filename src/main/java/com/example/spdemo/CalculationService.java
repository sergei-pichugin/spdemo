package com.example.spdemo;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public int findMin(int[] numbers, int order) {
        if (order < 1) {
            throw new IllegalArgumentException("order must be greater than 0");
        }
        if (order > numbers.length) {
            order = numbers.length;
        }
        if (order == 1) {
            return findFirstMin(numbers);
        }
        QuickSort.sort(numbers, 0, 0, numbers.length);
        return numbers[order-1];
    }

    private int findFirstMin(int[] numbers) {
        int min = numbers[0];
        for (int number: numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }
}
