package com.learn.sonarpractice.controller;

import com.learn.sonarpractice.dto.EmployeeDTO;
import com.learn.sonarpractice.entity.Employee;
import com.learn.sonarpractice.service.EmployeeService;

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Planted issue: unused method parameter "requestId"
    public Employee createEmployee(Employee employee, String requestId) {
        return employeeService.addEmployee(employee);
    }

    public EmployeeDTO fetchEmployee(int id) {
        return employeeService.getEmployee(id);
    }

    public double fetchBonus(int id, int yearsExperience) {
        return employeeService.calculateBonus(id, yearsExperience);
    }
}
