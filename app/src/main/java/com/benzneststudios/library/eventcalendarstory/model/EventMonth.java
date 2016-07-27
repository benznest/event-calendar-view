package com.benzneststudios.library.eventcalendarstory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by benznest on 01-Jul-16.
 */
public class EventMonth implements Parcelable {

    ArrayList<Event> listEvent;
    int month;
    int year;

    public EventMonth(){

    }


    public EventMonth(int month,int year){
        this.month = month;
        this.year = year;
    }

    public ArrayList<Event> getListEvent() {
        return listEvent;
    }

    public void setListEvent(ArrayList<Event> listEvent) {
        this.listEvent = listEvent;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.listEvent);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
    }

    protected EventMonth(Parcel in) {
        this.listEvent = new ArrayList<Event>();
        in.readList(this.listEvent, Event.class.getClassLoader());
        this.month = in.readInt();
        this.year = in.readInt();
    }

    public static final Parcelable.Creator<EventMonth> CREATOR = new Parcelable.Creator<EventMonth>() {
        @Override
        public EventMonth createFromParcel(Parcel source) {
            return new EventMonth(source);
        }

        @Override
        public EventMonth[] newArray(int size) {
            return new EventMonth[size];
        }
    };
}
