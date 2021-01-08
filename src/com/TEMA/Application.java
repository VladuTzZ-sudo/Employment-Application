package com.TEMA;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Application obj = null;
    ArrayList<Company> Companies;
    ArrayList<User> Users;

    private Application() {
        Companies = new ArrayList<>();
        Users = new ArrayList<>();
    }

    public static synchronized Application getInstance() {
        if (obj == null) {
            obj = new Application();
        }
        return obj;
    }

    public ArrayList<Company> getCompanies() {
        return Companies;
    }

    public Company getCompany(String name) {
        for (Company company : Companies) {
            if (company.name.compareTo(name) == 0) {
                return company;
            }
        }
        return null;
    }

    public void add(Company company) {
        Companies.add(company);
    }

    public void add(User user) {
        Users.add(user);
    }

    public boolean remove(Company company) {
        if (Companies.contains(company)) {
            Companies.remove(company);
            return true;
        }
        return false;
    }

    public boolean remove(User user) {
        if (Users.contains(user)) {
            Users.remove(user);
            return true;
        }
        return false;
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<>();
        for (String companyName : companies) {
            Company company = getCompany(companyName);
            jobs.addAll(company.getJobs());
        }
        return jobs;
    }

    public User getUserbyCode(int Code) {
        for (User user : Users) {
            if (user.Code == Code) {
                return user;
            }
        }
        return null;
    }

    public Recruiter getRecruiterbyCode(int Code) {
        for (Company company : Companies) {
            for (Recruiter recruiter : company.recruiters) {
                if (recruiter.Code == Code) {
                    return recruiter;
                }
            }
        }
        return null;
    }

    public Employee getEmployeebyCode(int Code) {
        for (Company company : Companies) {
            for (Departament departament : company.departaments) {
                for (Employee employee : departament.getEmployees()) {
                    if (employee.Code == Code && employee.getClass() == Employee.class) {
                        return employee;
                    }
                }
            }
        }
        return null;
    }
}
