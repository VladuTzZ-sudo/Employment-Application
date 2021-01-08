package com.TEMA;

import java.util.ArrayList;

public class Employee extends Consumer {
    String company = "";
    Double salary = 0.0;

    public Employee(ArrayList<Consumer> consumers, Resume cv, int code) {
        super(consumers, cv, code);
    }

    public Employee(Resume cv, int Code) {
        super(cv, Code);
    }

    public Employee(ArrayList<Consumer> consumers, Resume cv, String company, Double salary, int code) {
        super(consumers, cv, code);
        this.company = company;
        this.salary = salary;
    }

    public Employee(ArrayList<Consumer> consumers, Resume cv, String company, Double salary) {
        super(consumers, cv);
        this.company = company;
        this.salary = salary;
    }

    public Employee(Resume cv, String company, Double salary, int code) {
        super(cv, code);
        this.company = company;
        this.salary = salary;
    }

    public Employee(Resume cv, String company, Double salary) {
        super(cv);
        this.company = company;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee" + '\n' + '\t' + cv + '\n' +
                "company = " + company + '\n' +
                "salary = " + salary + '\n' +
                "consumers = " + consumersToString() + '\n' +
                "departament = " + cv.experiences.first().department + '\n' + '\n';
    }
}
