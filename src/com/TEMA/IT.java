package com.TEMA;

public class IT extends Departament {
    public IT(){
        super();
    }

    @Override
    public String getType() {
        return "IT";
    }

    @Override
    public double getTotalSalaryBudget() {
        double suma = 0;
        for (Employee employee : employees) {
            suma = suma + employee.salary;
        }
        return suma;
    }
}
