package com.learn.sonarpractice.util;

public class SalaryCalculator {

    // Planted issue: public mutable static field (should be private + final)
    public static double lastCalculatedBonus = 0;

    // Planted issue: utility class (all static methods) should have a private constructor.
    public SalaryCalculator() {
    }

    public static double calculateBonus(String department, double baseSalary, int yearsExperience) {
        double bonus;

        // Planted issue: deeply nested if-else -> high cognitive complexity (Major)
        if (department.equals("ENGINEERING")) {
            if (yearsExperience > 5) {
                if (baseSalary > 500000) {
                    if (yearsExperience > 10) {
                        bonus = baseSalary * 0.25;
                    } else {
                        bonus = baseSalary * 0.2;
                    }
                } else {
                    bonus = baseSalary * 0.15;
                }
            } else {
                bonus = baseSalary * 0.1;
            }
        } else if (department.equals("SALES")) {
            if (yearsExperience > 5) {
                if (baseSalary > 500000) {
                    bonus = baseSalary * 0.22;
                } else {
                    bonus = baseSalary * 0.17;
                }
            } else {
                bonus = baseSalary * 0.09;
            }
        } else {
            if (yearsExperience > 5) {
                if (baseSalary > 500000) {
                    bonus = baseSalary * 0.2;
                } else {
                    bonus = baseSalary * 0.15;
                }
            } else {
                bonus = baseSalary * 0.08;
            }
        }

        lastCalculatedBonus = bonus;
        return bonus;
    }

    public static double calculateHike(double baseSalary, double performanceRating) {
        // Planted issue: magic numbers with no named constants
        if (performanceRating >= 4.5) {
            return baseSalary * 0.18;
        } else if (performanceRating >= 3.5) {
            return baseSalary * 0.1;
        }
        return baseSalary * 0.03;
    }
}
