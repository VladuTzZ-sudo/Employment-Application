package com.TEMA;

import java.util.ArrayList;
import java.util.List;

public class User extends Consumer implements Observer{
    List<String> companies;
    ArrayList<Notification> notifications = new ArrayList<>();

    public User(ArrayList<Consumer> consumers, Resume cv, List<String> companies, int Code) {
        super(consumers, cv, Code);
        this.companies = companies;

        Application application = Application.getInstance();
        for(String companyname : companies){
            Company company = application.getCompany(companyname);
            company.addObserver(this);
        }
    }

    public User( Resume cv, List<String> companies, int Code) {
        super(cv, Code);
        this.companies = companies;

        Application application = Application.getInstance();
        for(String companyname : companies){
            Company company = application.getCompany(companyname);
            company.addObserver(this);
        }
    }

    public Employee convert() {
        return new Employee(consumers, cv, Code);
    }

    public Double getTotalScore() {
        Double medie = meanGPA();
        double AniExperienta = 0.0;
        for (Experience experience : cv.experiences) {
            AniExperienta = AniExperienta + experience.getDurata();
        }
        if (AniExperienta % 12 > 0) {
            AniExperienta = AniExperienta / 12 + 1;
        } else {
            AniExperienta = AniExperienta / 12;
        }
        return AniExperienta*1.5 + medie;
    }

    @Override
    public void update(Notification notification) {
        notification.user = this;
        notifications.add(notification);
        notification.sendNotification();
    }

    @Override
    public String toString() {
        return "User" + '\n' + '\t' + cv + '\n';
                //"companies = " + companies + '\n' + "Consumers:" + consumersToString() + '\n' + "Codul este: " + Code + '\n' + '\n';
    }
}
