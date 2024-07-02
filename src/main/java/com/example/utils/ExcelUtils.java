package com.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String excelFilePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
