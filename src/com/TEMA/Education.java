package com.TEMA;

import java.util.Calendar;

public class Education implements Comparable {
    Calendar start;
    Calendar end;
    String institutie;
    String level;
    Double medie;

    public Education(Calendar start, Calendar end, String institutie, String level, Double medie) throws InvalidDatesException {
        if (end != null) {
            if (end.before(start)) {
                throw new InvalidDatesException("Date incorecte.");
            } else {
                this.start = start;
                this.end = end;
                this.institutie = institutie;
                this.level = level;
                this.medie = medie;
            }
        } else {
            this.start = start;
            this.end = end;
            this.institutie = institutie;
            this.level = level;
            this.medie = medie;
        }
    }

    @Override
    public int compareTo(Object o) {
        Education obiect = (Education) o;
        if (end != null) {
            if (end.before(obiect.end)) {
                return 1;
            }
            if (!end.before(obiect.end)) {
                return -1;
            }
            if (end.equals(obiect.end)) {
                if (medie.compareTo(obiect.medie) < 0) {
                    return 1;
                }
                if (medie.compareTo(obiect.medie) >= 0) {
                    return -11;
                }
            }
        } else {
            if ( obiect.end == null ) {
                if (start.compareTo(obiect.start) <= 0) {
                    return -1;
                }
            }
            else {
                return -1;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        if (end != null) {
            return "-Education-" +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "start = " + start.getTime().toInstant() +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "end = " + end.getTime().toInstant() +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "institutie = " + institutie +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "level = " + level +
                    '\n' + '\t' + '\t' + '\t' + '\t' + "medie = " + medie +
                    '\n' + '\t' + '\t';
        }
        return "-Education-" +
                '\n' + '\t' + '\t' + '\t' + '\t' + "start = " + start.getTime().toInstant() +
                '\n' + '\t' + '\t' + '\t' + '\t' + "end = " + end +
                '\n' + '\t' + '\t' + '\t' + '\t' + "institutie = " + institutie +
                '\n' + '\t' + '\t' + '\t' + '\t' + "level = " + level +
                '\n' + '\t' + '\t' + '\t' + '\t' + "medie = " + medie +
                '\n' + '\t' + '\t';
    }
}
