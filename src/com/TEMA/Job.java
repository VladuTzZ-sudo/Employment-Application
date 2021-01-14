package com.TEMA;

import java.util.ArrayList;

public class Job implements Subject{
    String name;
    String company;
    boolean valabil;
    Constraint anAbsolvire;
    Constraint aniExperienta;
    Constraint medieAcademica;
    int noPositions;
    ArrayList<User> users;
    double salary;
    Notification notification;

    public Job(String name, String company, boolean valabil, Constraint anAbsolvire, Constraint aniExperienta, Constraint medieAcademica, Double salary, int noPositions) {
        this.name = name;
        this.company = company;
        this.valabil = valabil;
        this.anAbsolvire = anAbsolvire;
        this.aniExperienta = aniExperienta;
        this.medieAcademica = medieAcademica;
        this.noPositions = noPositions;
        this.salary = salary;
        users = new ArrayList<>();
    }

    public void apply(User user) {
        Application application = Application.getInstance();
        Company company1 = application.getCompany(company);
        Recruiter recruiter = company1.getRecruiter(user);
            recruiter.evaluate(this, user);
        if (!company1.users.contains(user)) {
            company1.addObserver(user);
        }
        addObserver(user);
    }

    public boolean meetsRequirments(User user) {
        if (user.getGraduationYear() < anAbsolvire.inferior || user.getGraduationYear() > anAbsolvire.superior) {
            //System.out.println(user.cv.information.getNume() + ": " + "false - graduation :" + company + " : " + name);
            return false;
        }
        if (user.meanGPA() < medieAcademica.inferior || user.meanGPA() > medieAcademica.superior) {
            //System.out.println(user.cv.information.getNume() + ": " + "false - medie :" + company + " : " + name);
            return false;
        }
        //System.out.println(user.cv.information.getNume() + ": " + "true - medie :" + user.meanGPA() + " : " + name);
        double AniExperienta = 0.0;
        for (Experience experience : user.cv.experiences) {
            AniExperienta = AniExperienta + experience.getDurata();
        }
        if (AniExperienta % 12 > 0 ) {
            AniExperienta = AniExperienta / 12 + (1 - AniExperienta / 12 % 1);
        } else {
            AniExperienta = AniExperienta / 12;
        }
        if (AniExperienta < aniExperienta.inferior || AniExperienta > aniExperienta.superior) {
            //System.out.println(user.cv.information.getNume() + ": " + "false :" + company + " : " + name + AniExperienta);
            return false;
        }
        //System.out.println(user.cv.information.getNume() + ": " + "true :" + company + " : " + name);
        return true;
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
        users.clear();
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", valabil=" + valabil +
                ", anAbsolvire=" + anAbsolvire +
                ", aniExperienta=" + aniExperienta +
                ", medieAcademica=" + medieAcademica +
                ", noPositions=" + noPositions +
                ", users=" + users +
                ", salary=" + salary +
                ", notification=" + notification +
                '}' + '\n' + '\t';
    }
}
