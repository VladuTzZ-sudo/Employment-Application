package com.TEMA;

import java.util.*;

public abstract class Consumer {
    ArrayList<Consumer> consumers;
    int Code;
    Resume cv;

    public Consumer(ArrayList<Consumer> consumers, Resume cv, int Code) {
        this.consumers = consumers;
        this.cv = cv;
        this.Code = Code;
    }

    public Consumer(ArrayList<Consumer> consumers, Resume cv) {
        this.consumers = consumers;
        this.cv = cv;
    }

    public Consumer(Resume cv, int code) {
        this.cv = cv;
        this.Code = code;
        consumers = new ArrayList<>();
    }

    public Consumer(Resume cv) {
        this.cv = cv;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public void add(Education education) {
        cv.educations.add(education);
    }

    public void add(Experience experience) {
        cv.experiences.add(experience);
    }

    public void add(Consumer consumer) {
        consumers.add(consumer);
    }

    public int getDegreeInFriendship(Consumer consumer) {
        ArrayList<Consumer> vizitat = new ArrayList<Consumer>();
        Consumer consumerActual;
        LinkedList<Consumer> queue = new LinkedList<Consumer>();
        LinkedList<Integer> grades = new LinkedList<Integer>();
        Integer grade = 0;

        vizitat.add(this);
        queue.add(this);
        grades.add(grade);

        while (queue.size() != 0) {
            consumerActual = queue.poll();
            grade = grades.poll();
            if (consumerActual.consumers.contains(consumer)) {
                if (grade != null) {
                    return (grade + 1);
                }
            }

            for (Consumer value : consumerActual.consumers) {
                if (!vizitat.contains(value)) {
                    vizitat.add(value);
                    queue.add(value);
                    if (grade != null) {
                        grades.add(grade + 1);
                    }
                }
            }
        }
        return -1;
    }

    public void remove(Consumer consumer) {
        if (consumers.contains(consumer)) {
            consumers.remove(consumer);
        }
    }

    public Integer getGraduationYear() {
        Integer an = -1;
        for (Education education : cv.educations) {
            if (education.level.compareTo("college") == 0) {
                if (education.end == null) {
                    an = 3000;
                } else {
                    an = education.end.get(Calendar.YEAR);
                }
            }
        }
        return an;
    }

    public Double meanGPA() {
        Double medie;
        int i = 0;
        Double suma = 0.0;
        for (Education education : cv.educations) {
            suma = suma + education.medie;
            i++;
        }
        medie = suma / i;
        return medie;
    }

    public ArrayList<String> consumersToString() {
        ArrayList<String> consumers1 = new ArrayList<>();
        for (Consumer consumer : consumers) {
            consumers1.add(consumer.cv.information.getNume());
        }
        return consumers1;
    }
}


