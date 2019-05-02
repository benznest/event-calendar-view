package com.benzneststudios.library.eventcalendar.model;

/**
 * Created by benznest on 15-Jul-16.
 */
public class DayCalendar {

    public static final int EMPTY_COLOR = -1;


    String event_id = null;
    String day;
    String month;
    String year;


    String dayOfWeek;


    boolean isEvent;

    int pointColor = EMPTY_COLOR;



    int circleColor = EMPTY_COLOR;

    public DayCalendar(String day,
                       boolean isEvent) {
        this.day = day;
        this.isEvent = isEvent;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean event) {
        isEvent = event;
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }


}
