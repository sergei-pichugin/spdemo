package com.example.spdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService service;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void shouldGetNumbersFromXlsx() throws IOException {
        //Given
        Resource resource = resourceLoader.getResource("classpath:testfile.xlsx");
        assertNotNull(resource);
        assertTrue(resource.exists());

        //When
        int[] numbers = service.getNumbersFromXlsx(resource.getURI().getPath());

        //Then
        assertEquals(5, numbers.length);
        assertEquals(3, numbers[numbers.length-1]);
    }
}