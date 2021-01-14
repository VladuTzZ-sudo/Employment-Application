package com.TEMA;

import java.util.ArrayList;

public class Manager extends Employee {
    ArrayList <Request<Job, Consumer>> requests;

    public Manager(ArrayList<Consumer> consumers, Resume cv, String company, Double salary, ArrayList<Request<Job, Consumer>> requests) {
        super(consumers, cv, company, salary);
        this.requests = requests;
    }

    public Manager(Resume cv, String company, Double salary) {
        super(cv, company, salary);
        requests = new ArrayList<>();
    }

    public void process(Job job) {
        Application application = Application.getInstance();
        int positions = 0;
        boolean userAngajat = false;
        ArrayList<Request> requestsForDelete = new ArrayList<>();
        for (Request request : requests) {
            Job jobRequest = (Job) request.getKey();
            User user = (User) request.getValue1();
            //System.out.println(user.cv.information.getNume() + " " + jobRequest.name + " " + jobRequest.company + " " + request.getScore());
            if (jobRequest.name.compareTo(job.name) == 0 && jobRequest.company.compareTo(job.company) == 0 && job.valabil) {
                userAngajat = false;
                if (positions < job.noPositions) {
                    if (application.Users.contains(user)) {
                        if (job.meetsRequirments(user)) {
                            positions++;
                            userAngajat = true;
                            Company company = application.getCompany(job.company);
                            application.remove(user);
                            job.removeObserver(user);
                            Notification notification3 = new Notification(company, job, user);
                            notification3.notificationType = Notification.NotificationType.Accepted;
                            user.update(notification3);
                            Employee employee = user.convert();
                            employee.salary = job.salary;
                            employee.company = job.company;
                            requestsForDelete.add(request);
                            Departament departament = company.search(job);
                            departament.add(employee);
                        }
                    }
                }
                if (!userAngajat) {
                    requestsForDelete.add(request);
                } else {
                    ArrayList<Company> companies = application.getCompanies();
                    for (Company company1 : companies) {
                        company1.users.remove(user);
                    }
                }
            }
        }

        for (Request request : requestsForDelete) {
            requests.remove(request);
        }

        Company company = application.getCompany(job.company);
        Notification notification1 = new Notification(company, job);
        notification1.notificationType = Notification.NotificationType.RequestDeclined;
        job.makeNotification(notification1);
        job.notifyAllObservers();

        if (positions == job.noPositions) {
            job.valabil = false;

            Notification notification2 = new Notification(company, job);
            notification2.notificationType = Notification.NotificationType.ClosedJob;
            company.makeNotification(notification2);
            company.notifyAllObservers();
        }
    }

    @Override
    public String toString() {
        return "Manager" + '\n' + '\t' + cv + '\n' +
                "requests" + requests + '\n' + '\n';
    }
}
