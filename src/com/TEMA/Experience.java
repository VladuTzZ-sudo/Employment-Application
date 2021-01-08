package com.TEMA;

import java.util.Calendar;

public class Experience implements Comparable {
    Calendar start;
    Calendar end;
    String department;
    String pozitie;
    String company;

    public Experience(Calendar start, Calendar end, String pozitie, String company, String department) throws InvalidDatesException {
        if (end != null) {
            if (start.compareTo(end) > 0) {
                throw new InvalidDatesException("Date incorecte.");
            } else {
                this.start = start;
                this.end = end;
                this.pozitie = pozitie;
                this.company = company;
                this.department = department;
            }
        } else {
            this.start = start;
            this.end = end;
            this.pozitie = pozitie;
            this.company = company;
            this.department = department;
        }
    }

    public Experience(Calendar start, Calendar end, String pozitie, String company) throws InvalidDatesException {
        if (end != null) {
            if (start.compareTo(end) > 0) {
                throw new InvalidDatesException("Date incorecte.");
            } else {
                this.start = start;
                this.end = end;
                this.pozitie = pozitie;
                this.company = company;
            }
        } else {
            this.start = start;
            this.end = end;
            this.pozitie = pozitie;
            this.company = company;
        }
    }

    public int getDurata() {
        if (end == null) {
            end = Calendar.getInstance();
        }
        int years = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int month = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        month = month + years * 12;
        return month;
    }

    @Override
    public int compareTo(Object o) {
        Experience obiect = (Experience) o;
        if (end != null) {
            if (end.before(obiect.end)) {
                return 1;
            }
            if (!end.before(obiect.end)) {
                return -1;
            }
            if (end.equals(obiect.end)) {
                if (company.compareTo(obiect.company) > 0) {
                    return 1;
                }
                if (company.compareTo(obiect.company) <= 0) {
                    return -1;
                }
            }
        } else {
            if (obiect.end == null ) {
                if (company.compareTo(obiect.company) > 0) {
                    return 1;
                }
            }
            else {
                return -1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (end != null) {
            return "-Experience-" +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "start = " + start.getTime().toInstant() +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "end = " + end.getTime().toInstant() +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "pozitie = " + pozitie +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "company = " + company +
                    '\n' + '\t' + '\t';
        }
        return "-Experience-" +
                '\n' + '\t' + '\t' + '\t' + '\t' + "start = " + start.getTime().toInstant() +
                '\n' + '\t' + '\t' + '\t' + '\t' + "end = " + end +
                '\n' + '\t' + '\t' + '\t' + '\t' + "pozitie = " + pozitie +
                '\n' + '\t' + '\t' + '\t' + '\t' + "company = " + company +
                '\n' + '\t' + '\t';
    }
}
