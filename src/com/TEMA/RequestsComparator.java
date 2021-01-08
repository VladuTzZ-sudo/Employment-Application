package com.TEMA;

import java.util.Comparator;

public class RequestsComparator implements Comparator<Request<Job, Consumer>> {
    @Override
    public int compare(Request<Job, Consumer> o1, Request<Job, Consumer> o2) {
        if ( o1.getScore() > o2.getScore() ) {
            return -1;
        }
        return 1;
    }
}
