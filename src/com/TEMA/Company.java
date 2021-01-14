package com.TEMA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Company implements Subject{
    String name;
    Manager manager;
    ArrayList<Departament> departaments;
    ArrayList<Recruiter> recruiters;
    ArrayList<User> users = new ArrayList<>();
    Notification notification;

    public void addManager(Manager manager){
        this.manager = manager;
    }

    public Company(String name, ArrayList<Departament> departaments) {
        this.name = name;
        this.departaments = departaments;
        recruiters = new ArrayList<>();
    }

    public void add(Departament departament) {
        departaments.add(departament);
    }

    public void add(Recruiter recruiter) {
        recruiters.add(recruiter);
    }

    public void add(Employee employee, Departament departament) {
        departament.employees.add(employee);
    }

    public void remove(Employee employee) {
        for (Departament departament : departaments) {
            if (departament.employees.contains(employee)) {
                departament.employees.remove(employee);
                break;
            }
        }
    }

    public void remove(Departament departament) {
        for (Employee employee : departament.employees) {
            departament.remove(employee);
        }
        departaments.remove(departament);
    }

    public void remove(Recruiter recruiter) {
        recruiters.remove(recruiter);
        remove(recruiter);
    }

    public void move(Departament source, Departament destination) {
        if (destination != null) {
            for (Employee employee : source.employees) {
                destination.add(employee);
                source.remove(employee);
            }
            for (Job job : source.jobs) {
                destination.add(job);
            }
            remove(destination);
        }
    }

    public void move(Employee employee, Departament newDepartment) {
        if ( newDepartment != null) {
            remove(employee);
            newDepartment.add(employee);
        }
    }

    public boolean contains(Departament departament) {
        return departaments.contains(departament);
    }

    public boolean contains(Employee employee) {
        for (Departament departament : departaments) {
            if (departament.employees.contains(employee)) {
                departament.employees.remove(employee);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user) {
        LinkedList<Recruiter> recruiters1 = new LinkedList<>();
        int gradeMax = 0;
        int grade;

        for (Recruiter recruiter : recruiters) {
            grade = user.getDegreeInFriendship(recruiter);
            if (grade > gradeMax) {
                recruiters1.clear();
                recruiters1.add(recruiter);
                gradeMax = grade;
            } else {
                if (grade == gradeMax) {
                    recruiters1.add(recruiter);
                }
            }
        }
        if (!recruiters1.isEmpty()) {
            Collections.sort(recruiters1, new RecruiterComparator());
        }

        return recruiters1.pollFirst();
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        for (Departament departament : departaments) {
            jobs.addAll(departament.getJobs());
        }
        return jobs;
    }

    public Departament search(Job job) {
        for (Departament departament : departaments) {
            for (Job job1 : departament.jobs) {
                if (job1.company.compareTo(job.company) == 0 && job1.name.compareTo(job.name) == 0) {
                    return departament;
                }
            }
        }
        return null;
    }
    public void makeNotification(Notification notification){
        this.notification = notification;
    }

    @Override
    public void addObserver(User user) {
        users.add(user);
    }

    @Override
    public void removeObserver(User c) {
        users.remove(c);
    }

    @Override
    public void notifyAllObservers() {
        for(User user : users){
            user.update(notification);
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\n' +
                ", manager=" + manager + '\n' +
                ", departaments=" + departaments + '\n' +
                ", recruiters=" + recruiters + '\n' +
                //", users=" + users + '\n' +
                ", notification=" + notification + '\n' +
                '}' + '\n';
    }
}
