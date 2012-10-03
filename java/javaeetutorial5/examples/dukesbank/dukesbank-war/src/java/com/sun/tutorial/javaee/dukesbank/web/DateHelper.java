/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * This class contains helper methods for dealing with
 * Date objects.
 */
public class DateHelper {
    private Calendar cal = null;

    public DateHelper() {
        Date date = new Date();
        cal = new GregorianCalendar();
        cal.setTime(date);
    }

    public void setTime(Date date) {
        cal.setTime(date);
    }

    public int getYear() {
        return cal.get(Calendar.YEAR);
    } // getYear 

    public int getMonth() {
        int calendarMonth = cal.get(Calendar.MONTH);

        return calendarMonthToInt(calendarMonth);
    } // getMonth

    public int getDay() {
        return cal.get(Calendar.DAY_OF_MONTH);
    } // getDay

    public int getHour() {
        return cal.get(Calendar.HOUR_OF_DAY);
    } // geHour

    public int getMinute() {
        return cal.get(Calendar.MINUTE);
    } // geMinute

    private int calendarMonthToInt(int calendarMonth) {
        if (calendarMonth == Calendar.JANUARY) {
            return 1;
        } else if (calendarMonth == Calendar.FEBRUARY) {
            return 2;
        } else if (calendarMonth == Calendar.MARCH) {
            return 3;
        } else if (calendarMonth == Calendar.APRIL) {
            return 4;
        } else if (calendarMonth == Calendar.MAY) {
            return 5;
        } else if (calendarMonth == Calendar.JUNE) {
            return 6;
        } else if (calendarMonth == Calendar.JULY) {
            return 7;
        } else if (calendarMonth == Calendar.AUGUST) {
            return 8;
        } else if (calendarMonth == Calendar.SEPTEMBER) {
            return 9;
        } else if (calendarMonth == Calendar.OCTOBER) {
            return 10;
        } else if (calendarMonth == Calendar.NOVEMBER) {
            return 11;
        } else if (calendarMonth == Calendar.DECEMBER) {
            return 12;
        } else {
            return 1;
        }
    } // calendarMonthToInt
} // class 
