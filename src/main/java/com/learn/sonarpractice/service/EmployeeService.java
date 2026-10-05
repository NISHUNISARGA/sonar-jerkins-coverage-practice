package com.learn.sonarpractice.service;

import com.learn.sonarpractice.dto.EmployeeDTO;
import com.learn.sonarpractice.entity.Employee;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    EmployeeDTO getEmployee(int id);
    double calculateBonus(int id, int yearsExperience);
}
