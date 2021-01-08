package com.TEMA;

import java.util.Calendar;

public class Finance extends Departament {
    public Finance(){
        super();
    }

    @Override
    public String getType() {
        return "Finance";
    }

    @Override
    public double getTotalSalaryBudget() {

        double suma = 0;
        for (Employee employee : employees) {
            Experience experience = employee.cv.experiences.first();
            if (experience.company.compareTo(employee.company) == 0) {
                if (experience.end == null) {
                    Calendar calendar = Calendar.getInstance();
                    int years = calendar.get(Calendar.YEAR) - experience.start.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) - experience.start.get(Calendar.MONTH);
                    int days = calendar.get(Calendar.DATE) - experience.start.get(Calendar.DATE);
                    month = month + years * 12;
                    if ((month >= 12 && days > 0) || (month > 12)) {
                        double salariu = employee.salary + employee.salary * 0.16;
                        suma = suma + salariu;
                    } else {
                        double salariu = employee.salary + employee.salary * 0.10;
                        suma = suma + salariu;
                    }
                }
            }
        }
        return suma;
    }


}
