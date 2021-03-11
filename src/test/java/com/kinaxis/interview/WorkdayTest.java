package com.kinaxis.interview;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class WorkdayTest extends TestCase
{

    public Date getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);

        return calendar.getTime();
    }

    public void assertDate(Date expectedDate, Date actualDate) {
        Calendar actualCalendar = Calendar.getInstance();
        Calendar expectedCalendar = Calendar.getInstance();
        actualCalendar.setTime(actualDate);
        expectedCalendar.setTime(expectedDate);

        assertEquals(expectedCalendar.get(Calendar.YEAR), actualCalendar.get(Calendar.YEAR));
        assertEquals(expectedCalendar.get(Calendar.MONTH), actualCalendar.get(Calendar.MONTH));
        assertEquals(expectedCalendar.get(Calendar.DATE), actualCalendar.get(Calendar.DATE));
    }

    /**
     * test positive scenario
     */
    @Test
    public void testWorkday()
    {
        Date inputDate = getDate(2018, Calendar.MARCH, 16);
        Date expectedDate = getDate(2018, Calendar.MARCH, 20);

        Date actualDate = Workday.calculateWorkday(inputDate, 2);
        assertDate(expectedDate, actualDate);
    }

    /**
     * test zero work days.. start should be same as end date
     */
    @Test
    public void testZeroWorkdays()
    {
        Date inputDate = getDate(2018, Calendar.MARCH, 16);
        Date actualDate = Workday.calculateWorkday(inputDate, 0);
        assertDate(inputDate, actualDate);
    }

    /**
     * test negative work days.. throw error
     */
    @Test
    public void testNegativeWorkdays()
    {
        Date inputDate = getDate(2018, Calendar.MARCH, 16);
        try {
            Workday.calculateWorkday(inputDate, -1);
            Assert.fail(); // didn't throw exception for invalid inputs
        }
        catch (RuntimeException exception) {
            Assert.assertEquals(exception.getMessage(), "Number of work days should be > 0");
        }
    }

    /**
     * test null date.. should throw error
     */
    @Test
    public void testNullDate()
    {
        try {
            Workday.calculateWorkday(null, 2);
            Assert.fail(); // didn't throw exception for invalid inputs
        }
        catch (RuntimeException exception) {
            Assert.assertEquals(exception.getMessage(), "Start date cannot be null");
        }
    }

    /**
     * test change year.. assert that year is changed
     */
    @Test
    public void testChangeYear()
    {
        Date inputDate = getDate(2020, Calendar.DECEMBER, 31);
        Date expectedDate = getDate(2021, Calendar.JANUARY, 5);

        Date actualDate = Workday.calculateWorkday(inputDate, 3);
        assertDate(expectedDate, actualDate);
    }

    /**
     * test leap year.. assert that month is changed on leap year
     */
    @Test
    public void testLeapYear()
    {
        Date inputDate = getDate(2020, Calendar.FEBRUARY, 28);
        Date expectedDate = getDate(2020, Calendar.MARCH, 2);

        Date actualDate = Workday.calculateWorkday(inputDate, 1);
        assertDate(expectedDate, actualDate);
    }

    /**
     * test weekend date
     */
    @Test
    public void testWeekendDate()
    {
        Date inputDate = getDate(2018, Calendar.MARCH, 17);
        Date expectedDate = getDate(2018, Calendar.MARCH, 19);

        Date actualDate = Workday.calculateWorkday(inputDate, 1);
        assertDate(expectedDate, actualDate);
    }
}
