package com.benzneststudios.library.eventcalendar.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.benzneststudios.library.eventcalendar.Util.CalendarUtils;
import com.benzneststudios.library.eventcalendar.model.EventMonth;
import com.benzneststudios.library.eventcalendar.view.CustomEventListMonth;
import com.benzneststudios.library.eventcalendar.view.ListMyEventView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by benznest on 01-Jul-16.
 */
public class EventListViewAdapter extends BaseAdapter {

    ArrayList<EventMonth> listEvent;
    boolean displayColorDayOfWeek;
    View.OnClickListener onClickListener;

    int textTitleColor;
    int textDescriptionColor;
    int textDayColor;
    int textDayOfWeekColor;
    int textTimeColor;
    int textMonthColor;
    int backgroundEventColor;
    int backgroundMonthColor;


    public EventListViewAdapter(ArrayList<EventMonth> listEvent) {
        this.listEvent = listEvent;
    }

    public EventListViewAdapter(ArrayList<EventMonth> listEvent, boolean displayColorDayOfWeek) {
        this.listEvent = listEvent;
        this.displayColorDayOfWeek = displayColorDayOfWeek;
    }

    public void reverseData(){
        Collections.reverse(listEvent);
    }

    @Override
    public int getCount() {
        return listEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomEventListMonth viewMonth;
        if (convertView == null) {
            viewMonth = new CustomEventListMonth(parent.getContext(), listEvent.get(position), displayColorDayOfWeek);
        } else {
            viewMonth = (CustomEventListMonth) convertView;
            viewMonth.setDisplayColorDayOfWeek(displayColorDayOfWeek);
            viewMonth.setEventMonth(listEvent.get(position));
        }

        // set Color.
        viewMonth.setTextTitleColor(textTitleColor);
        viewMonth.setTextDescriptionColor(textDescriptionColor);
        viewMonth.setTextDayColor(textDayColor);
        viewMonth.setTextDayOfWeekColor(textDayOfWeekColor);
        viewMonth.setTextTimeColor(textTimeColor);
        viewMonth.setTextMonthColor(textMonthColor);
        viewMonth.setBackgroundMonthColor(backgroundMonthColor);
        viewMonth.setBackgroundEventColor(backgroundEventColor);


        viewMonth.setOnClickEventItem(onClickListener);
        viewMonth.setupEventList(); // important.

        String month = CalendarUtils.getNameMonth(parent.getContext(), listEvent.get(position).getMonth());
        viewMonth.setMonthDisplay("" + month + "  " + listEvent.get(position).getYear());

        return viewMonth;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setTextTitleColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textTitleColor = res_color;
        }
    }

    public void setTextDescriptionColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textDescriptionColor = res_color;
        }
    }

    public void setTextDayColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textDayColor = res_color;
        }
    }

    public void setTextDayOfWeekColor(int res_color) {

        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textDayOfWeekColor = res_color;
        }
    }

    public void setTextTimeColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textTimeColor = res_color;
        }
    }

    public void setTextMonthColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textMonthColor = res_color;
        }
    }

    public void setBackgroundEventColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            backgroundEventColor = res_color;
        }
    }

    public void setBackgroundMonthColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            backgroundMonthColor = res_color;
        }
    }

}
