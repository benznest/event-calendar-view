package com.benzneststudios.eventCalendarView.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.benzneststudios.eventCalendarView.R;
import com.benzneststudios.eventCalendarView.Util.CalendarUtils;
import com.benzneststudios.eventCalendarView.Util.MyUtils;
import com.benzneststudios.eventCalendarView.model.Event;
import com.benzneststudios.eventCalendarView.model.EventMonth;

import java.util.ArrayList;

/**
 * Created by benznest on 01-Jul-16.
 */
public class CustomEventListMonth extends FrameLayout {

    TextView tvMonth;
    LinearLayout linearLayoutMonth;
    LinearLayout linearLayoutEvent;

    EventMonth eventMonth;
    boolean displayColorDayOfWeek;

    OnClickListener onClickListener;

    int textTitleColor;
    int textDescriptionColor;


    int textDayColor;
    int textDayOfWeekColor;
    int textTimeColor;
    int backgroundEventColor;


    public CustomEventListMonth(Context context, EventMonth eventMonth, boolean displayColorDayOfWeek) {
        super(context);

        this.eventMonth = eventMonth;
        this.displayColorDayOfWeek = displayColorDayOfWeek;

        initInflate();
        initInstance();
    }

    public CustomEventListMonth(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public CustomEventListMonth(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public CustomEventListMonth(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEventListMonth(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void setDisplayColorDayOfWeek(boolean displayColorDayOfWeek) {
        this.displayColorDayOfWeek = displayColorDayOfWeek;
    }


    public void setEventMonth(EventMonth eventMonth) {
        this.eventMonth = eventMonth;
    }

    public void setOnClickEventItem(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_event_month, this);
    }

    private void initInstance() {
        tvMonth = (TextView) findViewById(R.id.tv_month);
        linearLayoutEvent = (LinearLayout) findViewById(R.id.linearLayout_event);
        linearLayoutMonth = (LinearLayout) findViewById(R.id.linearLayout_month);
    }

    public void setupEventList() {
        initEventDay(eventMonth.getListEvent());
    }

    private void initEventDay(ArrayList<Event> listEvent) {
        linearLayoutEvent.removeAllViews();
        for (Event e : listEvent) {
            CustomRowEventCalendar row = new CustomRowEventCalendar(getContext());

            row.setEventId(e.getEventId());
            row.setDay(e.getDays());
            row.setOnClickEventItem(onClickListener);

            String dayName = CalendarUtils.getNameSmallDayOfWeek(getContext(), e.getDaysOfWeek());
            row.setDayOfWeek(dayName);

            row.setTitle(e.getTitle());
            row.setDescription(e.getDescription());
            row.setTime(e.getTime());

            if (displayColorDayOfWeek) {
                row.setColorDayOfWeek(MyUtils.getColorDayOfWeek(getContext(), e.getDaysOfWeek()));
            }

            row.setTextTitleColor(textTitleColor);
            row.setTextDescriptionColor(textDescriptionColor);
            row.setTextDayColor(textDayColor);
            row.setTextDayOfWeekColor(textDayOfWeekColor);
            row.setTextTimeColor(textTimeColor);
            row.setBackgroundEventColor(backgroundEventColor);

            linearLayoutEvent.addView(row);
        }
    }

    public void setMonthDisplay(String month_year) {
        tvMonth.setText(month_year);
    }


    public void setTextTitleColor(int res_color) {
        textTitleColor = res_color;
    }

    public void setTextDescriptionColor(int res_color) {
        textDescriptionColor = res_color;
    }

    public void setTextDayColor(int res_color) {
        textDayColor = res_color;
    }

    public void setTextDayOfWeekColor(int res_color) {
        textDayOfWeekColor = res_color;
    }

    public void setTextTimeColor(int res_color) {
        textTimeColor = res_color;
    }

    public void setTextMonthColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvMonth.setTextColor(res_color);
        }
    }

    public void setBackgroundMonthColor(int res_color){
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            linearLayoutMonth.setBackgroundColor(res_color);
        }
    }

    public void setBackgroundEventColor(int res_color) {
        backgroundEventColor = res_color;
    }

}
