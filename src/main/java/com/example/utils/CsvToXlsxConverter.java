package com.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;

public class CsvToXlsxConverter {

    private static final Logger logger = LogManager.getLogger(CsvToXlsxConverter.class);

    public static void convertCsvToXlsx(String csvFilePath, String xlsxFilePath) throws IOException {
        logger.info("Converting {} to {}", csvFilePath, xlsxFilePath);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Sheet1");
            String line;
            int rowNumber = 0;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] cells = line.split(",");
                Row row = sheet.createRow(rowNumber++);

                for (int i = 0; i < cells.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(cells[i].trim());
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(xlsxFilePath)) {
                workbook.write(fileOut);
            }
            logger.info("Successfully converted {} to {}", csvFilePath, xlsxFilePath);
        } catch (IOException e) {
            logger.error("Failed to convert {} to {}", csvFilePath, xlsxFilePath, e);
            throw e;
        }
    }

    public static void main(String[] args) {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String[] csvFiles = {"Employees.csv", "Vehicles.csv", "Companies.csv"};

        for (String csvFile : csvFiles) {
            String csvFilePath = desktopPath + csvFile;
            String xlsxFilePath = desktopPath + csvFile.replace(".csv", ".xlsx");

            try {
                convertCsvToXlsx(csvFilePath, xlsxFilePath);
                System.out.println("Converted " + csvFilePath + " to " + xlsxFilePath);
            } catch (IOException e) {
                System.err.println("Failed to convert " + csvFilePath + ": " + e.getMessage());
            }
        }
    }
}