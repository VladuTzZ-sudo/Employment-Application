package com.TEMA;

import java.util.ArrayList;

public abstract class Departament{
    ArrayList<Employee> employees;
    ArrayList<Job> jobs;

    public Departament() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public abstract String getType();
    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs() {
        ArrayList<Job> activejobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.valabil) {
                activejobs.add(job);
            }
        }
        return activejobs;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public void add(Job job) {                                                      //OBSERVER
        jobs.add(job);

        Application application = Application.getInstance();
        Company company = application.getCompany(job.company);
        Notification notification = new Notification(company, job);
        notification.notificationType = Notification.NotificationType.NewJob;
        company.makeNotification(notification);
        company.notifyAllObservers();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Departament{" + '\n' + '\t' + " -" + getType() + "-  " +  '\n' + '\t' +
                "employees=" + employees + '\n' + '\t' +
                ", jobs=" + jobs +  '\n' + '\t' +
                '}';
    }
}
