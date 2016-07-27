package com.benzneststudios.library.eventcalendarstory.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benznest on 01-Jul-16.
 */
public class Event implements Parcelable {


    String eventId;
    int days;

    int daysOfWeek;
    int month;
    String title;
    String description;
    String time;
    int pointColor;
    int cirCleColor;

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }


    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eventId);
        dest.writeInt(this.days);
        dest.writeInt(this.daysOfWeek);
        dest.writeInt(this.month);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.time);
        dest.writeInt(this.pointColor);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.eventId = in.readString();
        this.days = in.readInt();
        this.daysOfWeek = in.readInt();
        this.month = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.time = in.readString();
        this.pointColor = in.readInt();
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getCirCleColor() {
        return cirCleColor;
    }

    public void setCirCleColor(int cirCleColor) {
        this.cirCleColor = cirCleColor;
    }

    public static Creator<Event> getCREATOR() {
        return CREATOR;
    }
}
