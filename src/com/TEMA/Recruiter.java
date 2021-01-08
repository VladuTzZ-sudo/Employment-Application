package com.TEMA;

import java.util.ArrayList;

public class Recruiter extends Employee {
    Double rating = 5.0;

    public Recruiter(ArrayList<Consumer> consumers, Resume cv, String company, Double salary, int Code) {
        super(consumers, cv, company, salary, Code);
    }

    public Recruiter(Resume cv, String company, Double salary, int Code) {
        super(cv, company, salary, Code);
    }

    public int evaluate(Job job, User user) {
        Application application = Application.getInstance();
        int result = (int) (rating * user.getTotalScore());
        Request<Job, Consumer> request = new Request<Job, Consumer>(job, user, this, rating * user.getTotalScore());
        rating = rating + 0.1;
        Company company1 = application.getCompany(job.company);
        Manager manager = company1.manager;
        manager.requests.add(request);
        return result;
    }

    @Override
    public String toString() {
        return "Recruiter" + '\n' + '\t' + cv + '\n' +
                "company = " + company + '\n' +
                "salary = " + salary + '\n' +
                "consumers = " + consumersToString() + '\n' +
                "departament = " + cv.experiences.first().department + '\n' + '\n';
    }
}

