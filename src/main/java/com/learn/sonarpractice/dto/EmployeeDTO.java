package com.learn.sonarpractice.dto;

public class EmployeeDTO {

    private final int id;
    private final String name;
    private final String department;

    public EmployeeDTO(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
