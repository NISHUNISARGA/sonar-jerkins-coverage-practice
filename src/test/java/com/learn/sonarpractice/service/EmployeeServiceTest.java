package com.learn.sonarpractice.service;

import com.learn.sonarpractice.dto.EmployeeDTO;
import com.learn.sonarpractice.entity.Employee;
import com.learn.sonarpractice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    // Intentionally only 2 small tests -> coverage will look low.
    // Part of today's exercise is writing MORE tests to raise this.

    @Test
    void addEmployee_savesAndReturnsEmployee() {
        EmployeeService service = new EmployeeServiceImpl(new EmployeeRepository());
        Employee employee = new Employee(1, "Asha", "ENGINEERING", 600000);

        Employee saved = service.addEmployee(employee);

        assertEquals("Asha", saved.name);
    }

    @Test
    void getEmployee_returnsDtoWhenFound() {
        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeServiceImpl(repository);
        Employee employee = new Employee(2, "Ravi", "SALES", 400000);
        service.addEmployee(employee);

        EmployeeDTO dto = service.getEmployee(2);

        assertEquals("Ravi", dto.getName());
    }
}
