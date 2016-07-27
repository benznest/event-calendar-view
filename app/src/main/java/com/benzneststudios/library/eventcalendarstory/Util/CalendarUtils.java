package com.benzneststudios.library.eventcalendarstory.Util;

import android.content.Context;


import com.benzneststudios.library.eventcalendarstory.R;

import java.util.Calendar;


/**
 * Created by benznest on 05-Jul-16.
 */
public class CalendarUtils {

    private static Calendar calendar = Calendar.getInstance();

    public static String getNameSmallDayOfWeek(Context context, int dayOfWeek) {
        String[] day = context.getResources().getStringArray(R.array.array_day_small);
        return day[dayOfWeek - 1];
    }

    public static String getNameMonth(Context context, int month) {
        String[] array = context.getResources().getStringArray(R.array.array_month);

        return array[month - 1];
    }

    public static int getDayOfWeek(int day, int month, int year) {
        Calendar c = Calendar.getInstance();

        MyUtils.log(day + " / " + month + " / " + year);

        c.set(year, month - 1, day); // month - 1 because MONTH in Calendar start at 0.

        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getFirstDayOfWeekInMonth(int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1); // month - 1 because MONTH in Calendar start at 0.
        MyUtils.log("c.DAY_OF_WEEK = " + c.DAY_OF_WEEK);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1; // month + 1 because MONTH in Calendar start at 0.
    }

    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static int getCountDay(int month, int year) {

        if (month == 2) {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) {
                        return 29;
                    }
                    return 28;
                }
                return 29;
            }
            return 28;
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }

        return 30;
    }

    public static int getCountDayMonthBefore(int month_current, int year_current) {
        if (month_current - 1 < 0) {
            return getCountDay(12, year_current - 1);
        }

        return getCountDay(month_current - 1, year_current);
    }

    public static int getDayOfMonthFromDateTime(String datetime) {
        String[] array = datetime.split(" ");
        String[] array_date = array[0].split("-");
        return Integer.parseInt(array_date[2]);
    }

    public static int getDayOfWeekFromDateTime(String datetime) {
        String[] array = datetime.split(" ");
        String[] array_date = array[0].split("-");
        int day = Integer.parseInt(array_date[2]);
        int month = Integer.parseInt(array_date[1]);
        int year = Integer.parseInt(array_date[0]);
        return getDayOfWeek(day, month, year);
    }

    public static String getTimeFromDateTime(String datetime) {
        String[] array = datetime.split(" ");
        String[] array_time = array[1].split(":");
        String minute = array_time[1];
        String hr = array_time[0];
        return hr + ":" + minute;
    }

    public static boolean isToday(String d, String m, String y) {
        try {

            int day = Integer.parseInt(d);
            int month = Integer.parseInt(m);
            int year = Integer.parseInt(y);

            if (day == calendar.get(Calendar.DAY_OF_MONTH) && (month - 1) == calendar.get(Calendar.MONTH) && year == calendar.get(Calendar.YEAR)) {
                return true;
            }
        }
        catch (Exception e){


        }

        return false;
    }


}
