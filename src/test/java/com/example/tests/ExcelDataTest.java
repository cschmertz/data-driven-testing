package com.example.tests;

import com.example.utils.ExcelUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelDataTest {

    private static ExcelUtils employeesExcel;
    private static ExcelUtils vehiclesExcel;
    private static ExcelUtils companiesExcel;

    @BeforeAll
    public static void setup() throws IOException {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        employeesExcel = new ExcelUtils(desktopPath + "Employees.xlsx", "Employees");
        vehiclesExcel = new ExcelUtils(desktopPath + "Vehicles.xlsx", "Vehicles");
        companiesExcel = new ExcelUtils(desktopPath + "Companies.xlsx", "Companies");
    }

    @Test
    public void testEmployeesExcel() {
        // Validate some entries in the Employees.xlsx
        List<List<String>> employeesData = employeesExcel.getAllData();
        assertEquals("Kariotta", employeesData.get(1).get(1)); // First name
        assertEquals("McKernan", employeesData.get(1).get(2)); // Last name
        assertEquals("E567890", employeesData.get(1).get(3)); // Driver license
        assertEquals("1", employeesData.get(1).get(4)); // Company nam
    }

    @Test
    public void testVehiclesExcel() {
        // Validate some entries in the Vehicles.xlsx
        assertEquals(375291773, Integer.parseInt(vehiclesExcel.getCellData(1, 1))); // Mileage
        assertEquals("2001-11-07", vehiclesExcel.getCellData(1, 2)); // Last maintenance check
        assertEquals("4", vehiclesExcel.getCellData(1, 3)); // Company name
    }

    @Test
    public void testCompaniesExcel() {
        // Validate some entries in the Companies.xlsx
        assertEquals("Regenix", companiesExcel.getCellData(1, 1)); // Company name
        assertEquals("LeCombe", companiesExcel.getCellData(2, 1)); // Company name
    }

    @AfterAll
    public static void tearDown() throws IOException {
        employeesExcel.close();
        vehiclesExcel.close();
        companiesExcel.close();
    }
}
