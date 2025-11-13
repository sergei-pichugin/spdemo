package com.example.spdemo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    public int[] getNumbersFromXlsx(String filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            int[] result = new int[sheet.getLastRowNum()+1];
            int index = 0;

            for (Row currentRow : sheet) {
                Iterator<Cell> cellIterator = currentRow.iterator();

                Cell currentCell = cellIterator.next();
                // Handle different cell types (String, Numeric, Date, etc.)
                if (Objects.requireNonNull(currentCell.getCellType()) == CellType.NUMERIC) {
                    result[index++] = (int) currentCell.getNumericCellValue();
                }
            }
            return result;
        }
    }
}
