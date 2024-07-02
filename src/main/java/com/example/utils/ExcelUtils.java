package com.example.utils;

import com.example.model.Company;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<List<String>> getAllData() {
        List<List<String>> tableData = new ArrayList<>();
        int rowCount = getRowCount();
        DataFormatter formatter = new DataFormatter();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            List<String> rowData = new ArrayList<>();
            for (int j = 0; j < getColumnCount(); j++) {
                Cell cell = row.getCell(j);
                rowData.add(formatter.formatCellValue(cell));
            }
            tableData.add(rowData);
        }
        return tableData;
    }


    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
