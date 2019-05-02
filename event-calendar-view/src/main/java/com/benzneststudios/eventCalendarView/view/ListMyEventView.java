package com.benzneststudios.eventCalendarView.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ListView;


import com.benzneststudios.eventCalendarView.R;
import com.benzneststudios.eventCalendarView.Util.MyUtils;
import com.benzneststudios.eventCalendarView.adapter.EventListViewAdapter;
import com.benzneststudios.eventCalendarView.model.Event;
import com.benzneststudios.eventCalendarView.model.EventMonth;

import java.util.ArrayList;

/**
 * Created by benznest on 27-Jul-16.
 */
public class ListMyEventView extends FrameLayout {

    public static final int THEME_DARK = 0;
    public static final int THEME_LIGHT = 1;

    public static final int NOT_CUSTOM_COLOR = 0;

    public static final int KEY_EVENT_ID = R.string.key_event_id;
    public static final int KEY_TITLE = R.string.key_title;
    public static final int KEY_DESCRIPTION = R.string.key_description;
    public static final int KEY_DAY = R.string.key_day;
    public static final int KEY_DAY_OF_WEEK = R.string.key_day_of_week;
    public static final int KEY_TIME = R.string.key_time;

    ListView listView;
    boolean displayColorDayOfWeek;
    OnClickListener onClickListener;

    int theme = THEME_DARK;

    int textTitleColor = NOT_CUSTOM_COLOR;
    int textDescriptionColor = NOT_CUSTOM_COLOR;
    int textDayColor = NOT_CUSTOM_COLOR;
    int textDayOfWeekColor = NOT_CUSTOM_COLOR;
    int textTimeColor = NOT_CUSTOM_COLOR;
    int textMonthColor = NOT_CUSTOM_COLOR;
    int backgroundEventColor = NOT_CUSTOM_COLOR;
    int backgroundMonthColor = NOT_CUSTOM_COLOR;


    public ListMyEventView(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public ListMyEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public ListMyEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ListMyEventView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.listview_event, this);
    }

    private void initInstance() {
        listView = (ListView) findViewById(R.id.listView_event);
    }

    public void setAdapter(EventListViewAdapter adapter) {

        if (theme == THEME_DARK) {
            adapter.setTextTitleColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            adapter.setTextDescriptionColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text_description));
            adapter.setTextDayColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            adapter.setTextDayOfWeekColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            adapter.setTextTimeColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            adapter.setTextMonthColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            adapter.setBackgroundEventColor(ContextCompat.getColor(getContext(), R.color.theme_dark_background));
            adapter.setBackgroundMonthColor(ContextCompat.getColor(getContext(), R.color.theme_dark_month_background));
        } else if (theme == THEME_LIGHT) {
            adapter.setTextTitleColor(ContextCompat.getColor(getContext(), R.color.theme_light_text));
            adapter.setTextDescriptionColor(ContextCompat.getColor(getContext(), R.color.theme_light_text_description));
            adapter.setTextDayColor(ContextCompat.getColor(getContext(), R.color.theme_light_text));
            adapter.setTextDayOfWeekColor(ContextCompat.getColor(getContext(), R.color.theme_light_text));
            adapter.setTextTimeColor(ContextCompat.getColor(getContext(), R.color.theme_light_text));
            adapter.setTextMonthColor(ContextCompat.getColor(getContext(), R.color.theme_light_text));
            adapter.setBackgroundEventColor(ContextCompat.getColor(getContext(), R.color.theme_light_background));
            adapter.setBackgroundMonthColor(ContextCompat.getColor(getContext(), R.color.theme_light_month_background));
        }

        adapter.setTextTitleColor(textTitleColor);
        adapter.setTextDescriptionColor(textDescriptionColor);
        adapter.setTextDayColor(textDayColor);
        adapter.setTextDayOfWeekColor(textDayOfWeekColor);
        adapter.setTextTimeColor(textTimeColor);
        adapter.setTextMonthColor(textMonthColor);
        adapter.setBackgroundEventColor(backgroundEventColor);
        adapter.setBackgroundMonthColor(backgroundMonthColor);

        listView.setAdapter(adapter);
    }

    public void setDisplayColorDayOfWeek(boolean display) {
        displayColorDayOfWeek = display;
    }

    public boolean isDisplayColorDayOfWeek() {
        return displayColorDayOfWeek;
    }

    public static ArrayList<EventMonth> getSampleData() {
        ArrayList<EventMonth> listEventMonth = new ArrayList<>();
        int event_id = 1;
        for (int i = 1; i <= 12; i++) {
            EventMonth eventMonth = new EventMonth();
            eventMonth.setMonth(i);
            eventMonth.setYear(2016);

            // add event.
            ArrayList<Event> listEvent = new ArrayList<>();
            for (int e = 1; e < 7; e++) {
                Event event = new Event();
                event.setEventId("" + event_id);
                event_id++;

                event.setDays(e + 1);
                event.setMonth(i);
                event.setPointColor(MyUtils.convertColor("#FF24CB15"));
                event.setCirCleColor(MyUtils.convertColor("#FFff9fec"));
                event.setTitle("Thumbnail label");
                event.setTime("18:00");
                event.setDescription("Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.");
                event.setDaysOfWeek(e);
                listEvent.add(event);
            }

            eventMonth.setListEvent(listEvent);
            listEventMonth.add(eventMonth);
        }
        return listEventMonth;
    }

    public void displaySampleData() {

        ArrayList<EventMonth> listEventMonth = getSampleData();
        EventListViewAdapter adapter = new EventListViewAdapter(listEventMonth, displayColorDayOfWeek);
        adapter.setOnClickListener(onClickListener);
        setAdapter(adapter);
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getTheme() {
        return theme;
    }

    public void setOnClickEventItem(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
        textMonthColor = res_color;
    }

    public void setBackgroundEventColor(int res_color) {
        backgroundEventColor = res_color;
    }

    public void setBackgroundMonthColor(int res_color) {
        backgroundMonthColor = res_color;
    }
}
