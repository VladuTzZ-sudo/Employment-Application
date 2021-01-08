package com.TEMA;

public class Marketing extends Departament{
    public Marketing(){
        super();
    }

    @Override
    public String getType() {
        return "Marketing";
    }

    @Override
    public double getTotalSalaryBudget() {
        double suma = 0;
        for (Employee employee : employees) {
            double salariu = employee.salary;
            if (salariu > 5000) {
                salariu += salariu * 0.10;
            }
            if (salariu >= 3000 && salariu <= 5000) {
                salariu += salariu * 0.16;
            }
            suma += salariu;
        }
        return suma;
    }
}
