package com.TEMA;

public class Management extends Departament {
    public Management(){
        super();
    }

    @Override
    public String getType() {
        return "Management";
    }

    @Override
    public double getTotalSalaryBudget() {
        double suma = 0;
        for (Employee employee : employees) {
            suma = suma + employee.salary;
        }
        return suma + suma * 0.16;
    }
}
