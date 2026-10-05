package com.learn.sonarpractice.service;

import com.learn.sonarpractice.dto.EmployeeDTO;
import com.learn.sonarpractice.entity.Employee;
import com.learn.sonarpractice.exception.EmployeeNotFoundException;
import com.learn.sonarpractice.repository.EmployeeRepository;
import com.learn.sonarpractice.util.SalaryCalculator;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        // Planted issue: unused local variable (dead store)
        String status = "PENDING";

        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            // Planted issue: empty catch block - exception swallowed silently
        }
        return null;
    }

    @Override
    public EmployeeDTO getEmployee(int id) {
        Employee employee = employeeRepository.findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id " + id);
        }

        // Planted issue: System.out.println instead of a proper logger (Sonar rule S106)
        System.out.println("Fetched employee: " + employee.name);

        return new EmployeeDTO(employee.id, employee.name, employee.department);
    }

    @Override
    public double calculateBonus(int id, int yearsExperience) {
        Employee employee = employeeRepository.findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id " + id);
        }

        // Planted issue: comparing String objects with == instead of .equals()
        if (employee.department == "ENGINEERING") {
            System.out.println("Engineering bonus path");
        }

        return SalaryCalculator.calculateBonus(employee.department, employee.baseSalary, yearsExperience);
    }
}
