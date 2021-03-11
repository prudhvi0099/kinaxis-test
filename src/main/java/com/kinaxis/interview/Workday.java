package com.kinaxis.interview;

import java.util.Calendar;
import java.util.Date;

/**
 * Calculate work days
 *
 */
public class Workday
{

    public static Date calculateWorkday(final Date startDate, final int numberOfWOrkdays) {

        if (numberOfWOrkdays < 0) {
            throw new RuntimeException("Number of work days should be > 0");
        }
        if (numberOfWOrkdays == 0) {
            return startDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        for (int i = 0; i < numberOfWOrkdays; ) {
            calendar.add(Calendar.DATE, 1);
            if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                i++;
            }
        }

        return calendar.getTime();
    }
}
