package com.TEMA;

import java.util.ArrayList;

public class Notification {
    NotificationType notificationType;
    Company company;
    Job job;
    User user;

    public Notification(Company company, Job job, User user){
        this.company = company;
        this.job = job;
        this.user = user;
    }

    public Notification(Company company, Job job){
        this.company = company;
        this.job = job;
    }

    enum NotificationType {
        NewJob,
        ClosedJob,
        RequestDeclined,
        Accepted
    }

    public void sendNotification(){
        switch (notificationType) {
            case NewJob -> System.out.println("In compania: " + company.name + " a aparut un job nou. Jobul se numeste: " + job.name + " . Aceasta notificare a fost primita de utilizatorul: " + user.cv.information.getNume());
            case ClosedJob -> System.out.println("In compania: " + company.name + " s-a inchis jobul: " + job.name + " . Aceasta notificare a fost primita de utilizatorul: " + user.cv.information.getNume());
            case RequestDeclined -> System.out.println("In compania: " + company.name + ", jobul: " + job.name + " user-ului cu numele: " + user.cv.information.getNume() + " i-a fost respinsa cererea de aplicare.");
            case Accepted -> System.out.println("User-ul " + user.cv.information.getNume() + " " + user.cv.information.getPrenume() + " a fost angajat in compania: " + company.name + " , job: " + job.name);
        }
    }

}
