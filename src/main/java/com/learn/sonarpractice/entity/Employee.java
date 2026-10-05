package com.learn.sonarpractice.entity;

public class Employee {

    // Planted issue: public mutable fields instead of private + getters/setters
    public int id;
    public String name;
    public String department;
    public double baseSalary;

    public Employee(int id, String name, String department, double baseSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
    }
}
