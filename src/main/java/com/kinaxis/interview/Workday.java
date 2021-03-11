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
        // TODO: test and implement me!

        if (numberOfWOrkdays < 0) {
            throw new RuntimeException("Number of work days should be > 0");
        }
        if (numberOfWOrkdays == 0) {
            return startDate;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        for (int i = 0; i < numberOfWOrkdays; ) {
            if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                i++;
            }
            calendar.add(Calendar.DATE, 1);
        }

        return calendar.getTime();
    }

    public static void main( String[] args )
    {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, 2, 16);
        Date date = cal.getTime();
        System.out.println(Workday.calculateWorkday(date, 2));
    }
}
