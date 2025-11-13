package com.example.spdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MathController {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private FileService fileService;

    @GetMapping("/min")
    public MinResponse findMin(@RequestParam String filePath,
                               @RequestParam int order) {
        int[] numbers;
        try {
            numbers = fileService.getNumbersFromXlsx(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int min = calculationService.findMin(numbers, order);

        return new MinResponse(min);
    }
}
