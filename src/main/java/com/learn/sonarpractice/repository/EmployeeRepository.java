package com.learn.sonarpractice.repository;

import com.learn.sonarpractice.entity.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {

    private final Map<Integer, Employee> store = new HashMap<>();

    public Employee save(Employee employee) {
        store.put(employee.id, employee);
        return employee;
    }

    public Employee findById(int id) {
        return store.get(id);
    }
}
