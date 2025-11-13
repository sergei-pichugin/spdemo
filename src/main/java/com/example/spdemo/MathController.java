package com.example.spdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MathController {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private FileService fileService;

    @GetMapping("/min")
    public MinResponse findMin(@RequestBody MinRequest req) {
        int[] numbers;
        try {
            numbers = fileService.getNumbersFromXlsx(req.getFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int min = calculationService.findMin(numbers, req.getOrder());

        return new MinResponse(min);
    }
}
