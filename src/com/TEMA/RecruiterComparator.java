package com.TEMA;

import java.util.Comparator;

public class RecruiterComparator implements Comparator<Recruiter> {
    @Override
    public int compare(Recruiter o1, Recruiter o2) {
        if (o1.rating.compareTo(o2.rating) > 0) {
            return 1;
        } else {
            if (o1.rating.compareTo(o2.rating) == 0) {
                return 0;
            }
            return -1;
        }
    }
}
