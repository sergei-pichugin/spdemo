package com.example.spdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private FileService fileService;

    @GetMapping("/min")
    public MinResponse findMin(@RequestBody MinRequest req) {
        int[] numbers = fileService.getNumbers(req.getFilePath());
        return calculationService.findMin(numbers, req.getOrder());
    }
}
