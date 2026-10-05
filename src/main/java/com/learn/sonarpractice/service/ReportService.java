package com.learn.sonarpractice.service;

import com.learn.sonarpractice.entity.Employee;

import java.io.FileWriter;
import java.io.IOException;

public class ReportService {

    // Planted issue: hard-coded credential (Blocker - Security)
    private static final String DB_PASSWORD = "Admin@123";

    public void exportEmployeeReport(Employee employee) throws IOException {
        // Planted issue: resource opened but never closed (Blocker/Critical - Reliability)
        FileWriter writer = new FileWriter("report.txt");
        writer.write("Employee: " + employee.name);

        // Planted issue: null checked, then dereferenced anyway (Critical - possible NullPointerException)
        String dept = employee.department;
        if (dept == null) {
            System.out.println("No department assigned");
        }
        System.out.println("Department length: " + dept.length());

        // Planted issue: redundant boolean literal comparison (Minor)
        boolean isActive = true;
        if (isActive == true) {
            System.out.println("Active employee");
        }

        // Planted issue: TODO tag left in code (Minor)
        // TODO: add proper exception handling and logging here
    }

    public String getDbPassword() {
        return DB_PASSWORD;
    }
}
