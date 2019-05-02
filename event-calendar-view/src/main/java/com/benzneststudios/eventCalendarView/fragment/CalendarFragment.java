package com.benzneststudios.eventCalendarView.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.benzneststudios.eventCalendarView.MyConstant;
import com.benzneststudios.eventCalendarView.R;
import com.benzneststudios.eventCalendarView.Util.CalendarUtils;
import com.benzneststudios.eventCalendarView.adapter.CalendarDateAdapter;
import com.benzneststudios.eventCalendarView.model.DayCalendar;
import com.benzneststudios.eventCalendarView.model.EventMonth;
import com.benzneststudios.eventCalendarView.view.ListMyEventView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {


    public static final int KEY_DAY = R.string.key_day;
    public static final int KEY_EVENT_ID = R.string.key_event_id;
    public static final int KEY_MONTH = R.string.key_month;
    public static final int KEY_YEAR = R.string.key_year;
    public static final int KEY_DAY_OF_WEEK = R.string.key_day_of_week;

    private static boolean flagOpeningCalendar;
    private LinearLayout linearLayoutCalendar;
    private LinearLayout linearLayoutDayHeader;
    private LinearLayout linearLayoutMonthHeader;

    private TextView[] tvDay = new TextView[7];

    public CalendarFragment() {
        // Required empty public constructor
    }

    private ImageView imgArrowLeft;
    private ImageView imgArrowRight;
    private TextView tvTitleMonthYear;

    private GridView gridCalendar;

    private int dayOfWeekFirstDayInMonth = 0;
    private int currentMonth = 0;
    private int currentYear = 0;

    private ArrayList<EventMonth> listEventMonth;

    public static final int THEME_DARK = 0;
    public static final int THEME_LIGHT = 1;

    private static int theme = THEME_DARK;

    private static int textDayColor = ListMyEventView.NOT_CUSTOM_COLOR;
    private static int textMonthColor = ListMyEventView.NOT_CUSTOM_COLOR;
    private static  int textDayHeader = ListMyEventView.NOT_CUSTOM_COLOR;
    private static int backgroundCalendarColor = ListMyEventView.NOT_CUSTOM_COLOR;
    private static int backgroundHeaderDay = ListMyEventView.NOT_CUSTOM_COLOR;
    private static int backgroundHeaderMonth = ListMyEventView.NOT_CUSTOM_COLOR;

    public static int circleColorToday = ListMyEventView.NOT_CUSTOM_COLOR;

    public static View.OnClickListener onClickListener;


    public static CalendarFragment newInstance(ArrayList<EventMonth> listEventMonth, int dayOfWeekFirstDayInMonth, int currentMonth, int currentYear) {
        CalendarFragment fragment = new CalendarFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(MyConstant.KEY_DAY_OF_WEEK_FIRST_DAY_IN_MONTH, dayOfWeekFirstDayInMonth);
        bundle.putInt(MyConstant.KEY_CURRENT_MONTH, currentMonth);
        bundle.putInt(MyConstant.KEY_CURRENT_YEAR, currentYear);
        bundle.putParcelableArrayList(MyConstant.KEY_LIST_CALENDAR, listEventMonth);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            dayOfWeekFirstDayInMonth = bundle.getInt(MyConstant.KEY_DAY_OF_WEEK_FIRST_DAY_IN_MONTH);
            currentMonth = bundle.getInt(MyConstant.KEY_CURRENT_MONTH);
            currentYear = bundle.getInt(MyConstant.KEY_CURRENT_YEAR);
            listEventMonth = bundle.getParcelableArrayList(MyConstant.KEY_LIST_CALENDAR);
        }

        intInstance(rootView);

        return rootView;
    }

    private void intInstance(View rootView) {
        linearLayoutCalendar = (LinearLayout) rootView.findViewById(R.id.linearLayout_calendar);
        linearLayoutDayHeader = (LinearLayout) rootView.findViewById(R.id.linearLayout_day_header);
        linearLayoutMonthHeader = (LinearLayout) rootView.findViewById(R.id.linearLayout_month_header);

        tvDay[0] = (TextView) rootView.findViewById(R.id.tv_day_1);
        tvDay[1] = (TextView) rootView.findViewById(R.id.tv_day_2);
        tvDay[2] = (TextView) rootView.findViewById(R.id.tv_day_3);
        tvDay[3] = (TextView) rootView.findViewById(R.id.tv_day_4);
        tvDay[4] = (TextView) rootView.findViewById(R.id.tv_day_5);
        tvDay[5] = (TextView) rootView.findViewById(R.id.tv_day_6);
        tvDay[6] = (TextView) rootView.findViewById(R.id.tv_day_7);

        imgArrowLeft = (ImageView) rootView.findViewById(R.id.img_arrow_left);
        imgArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonth();
            }
        });

        imgArrowRight = (ImageView) rootView.findViewById(R.id.img_arrow_right);
        imgArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonth();
            }
        });

        gridCalendar = (GridView) rootView.findViewById(R.id.calendar_grid);

        tvTitleMonthYear = (TextView) rootView.findViewById(R.id.tv_title_month_year);
        tvTitleMonthYear.setText(CalendarUtils.getNameMonth(getContext(), currentMonth) + " " + currentYear);

        ArrayList<String> dayInCalendar = new ArrayList<>();

        int countDayInBeforeMonth = CalendarUtils.getCountDayMonthBefore(currentMonth, currentYear);
        int countDayInCurrentMonth = CalendarUtils.getCountDay(currentMonth, currentYear);

        for (int i = 1; i < dayOfWeekFirstDayInMonth; i++) {
            dayInCalendar.add(0, "");
            countDayInBeforeMonth--;
        }

        for (int i = 1; i <= countDayInCurrentMonth; i++) {
            dayInCalendar.add("" + i);
        }

        ArrayList<DayCalendar> data = new ArrayList<>();
        EventMonth calendarMonth = getEventMonth();

        int count_event = 0;
        for (int i = 0; i < dayInCalendar.size(); i++) {
            DayCalendar day = null;
            String dayShow = dayInCalendar.get(i);
            if (calendarMonth != null) {
                if (count_event < calendarMonth.getListEvent().size()) {
                    int day_event = calendarMonth.getListEvent().get(count_event).getDays();
                    if (dayShow.equals("")) {
                        day = new DayCalendar("" + dayShow, false);
                    } else if (day_event == Integer.parseInt(dayShow)) {
                        day = new DayCalendar("" + dayShow, true);
                        day.setEvent_id(calendarMonth.getListEvent().get(count_event).getEventId());
                        day.setPointColor(calendarMonth.getListEvent().get(count_event).getPointColor());
                        day.setCircleColor(calendarMonth.getListEvent().get(count_event).getCirCleColor());
                        day.setDayOfWeek("" + calendarMonth.getListEvent().get(count_event).getDaysOfWeek());
                        day.setEvent(true);
                        count_event++;
                    } else {
                        day = new DayCalendar("" + dayShow, false);
                    }
                } else {
                    day = new DayCalendar("" + dayShow, false);
                }
            } else {
                day = new DayCalendar("" + dayShow, false);
            }

            day.setMonth(""+currentMonth);
            day.setYear(""+currentYear);
            data.add(day);
        }


        CalendarDateAdapter adapter = new CalendarDateAdapter(data);
        setAdapterCalendar(adapter);
    }

    private void setAdapterCalendar(CalendarDateAdapter adapter) {

        adapter.setOnClickDayCalendar(onClickListener);

        if (theme == THEME_DARK) {
            adapter.setTextDayColor(ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            setTextViewColor(tvTitleMonthYear, ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            setBackgroundLayout(linearLayoutCalendar, ContextCompat.getColor(getContext(), R.color.theme_dark_background));
            setBackgroundLayout(linearLayoutDayHeader, ContextCompat.getColor(getContext(), R.color.theme_dark_background));
            setBackgroundLayout(linearLayoutMonthHeader, ContextCompat.getColor(getContext(), R.color.theme_dark_background));
            for (TextView tv : tvDay) {
                setTextViewColor(tv, ContextCompat.getColor(getContext(), R.color.theme_dark_text));
            }
            imgArrowLeft.setImageResource(R.drawable.m_arrow_l);
            imgArrowRight.setImageResource(R.drawable.m_arrow_r);

        } else if (theme == THEME_LIGHT) {
            adapter.setTextDayColor(ContextCompat.getColor(getContext(), R.color.theme_light_text));
            setTextViewColor(tvTitleMonthYear, ContextCompat.getColor(getContext(), R.color.theme_light_text));
            setBackgroundLayout(linearLayoutCalendar, ContextCompat.getColor(getContext(), R.color.theme_light_background));
            setBackgroundLayout(linearLayoutDayHeader, ContextCompat.getColor(getContext(), R.color.theme_light_background));
            setBackgroundLayout(linearLayoutMonthHeader, ContextCompat.getColor(getContext(), R.color.theme_light_background));
            for (TextView tv : tvDay) {
                setTextViewColor(tv, ContextCompat.getColor(getContext(), R.color.theme_light_text));
            }
            imgArrowLeft.setImageResource(R.drawable.m_arrow_l_black);
            imgArrowRight.setImageResource(R.drawable.m_arrow_r_black);
        }

        adapter.setTextDayColor(textDayColor);
        setTextViewColor(tvTitleMonthYear, textMonthColor);
        setBackgroundLayout(linearLayoutCalendar, backgroundCalendarColor);
        setBackgroundLayout(linearLayoutDayHeader, backgroundHeaderDay);
        setBackgroundLayout(linearLayoutMonthHeader, backgroundHeaderMonth);

        for (TextView tv : tvDay) {
            setTextViewColor(tv, textDayHeader);
        }

        gridCalendar.setAdapter(adapter);
    }

    private EventMonth getEventMonth() {
        for (EventMonth calendar : listEventMonth) {

            int month = calendar.getMonth();
            int year = calendar.getYear();

            if (month == currentMonth && year == currentYear) {
                return calendar;
            }
        }
        return null;
    }


    private void nextMonth() {

        if (currentMonth + 1 > 12) {
            currentMonth = 1;
            currentYear++;
        } else {
            currentMonth++;
        }

        dayOfWeekFirstDayInMonth = CalendarUtils.getFirstDayOfWeekInMonth(currentMonth, currentYear);

        getActivity().getSupportFragmentManager().beginTransaction()
                //.setCustomAnimations(R.anim.in_right,R.anim.out_left)
                .remove(getActivity().getSupportFragmentManager().findFragmentByTag(MyConstant.TAG_FRAGMENT_CALENDAR_MONTH))
                .commit();

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.in_left, R.anim.out_right)
                .add(R.id.content_container, CalendarFragment.newInstance(listEventMonth, dayOfWeekFirstDayInMonth, currentMonth, currentYear), MyConstant.TAG_FRAGMENT_CALENDAR_MONTH)
                .commit();
    }

    private void previousMonth() {
        if (currentMonth - 1 < 1) {
            currentMonth = 12;
            currentYear--;
        } else {
            currentMonth--;
        }

        dayOfWeekFirstDayInMonth = CalendarUtils.getFirstDayOfWeekInMonth(currentMonth, currentYear);

        getActivity().getSupportFragmentManager().beginTransaction()
                //.setCustomAnimations(R.anim.out_left,R.anim.in_right)
                .remove(getActivity().getSupportFragmentManager().findFragmentByTag(MyConstant.TAG_FRAGMENT_CALENDAR_MONTH))
                .commit();

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.in_right, R.anim.out_left)
                .add(R.id.content_container, CalendarFragment.newInstance(listEventMonth, dayOfWeekFirstDayInMonth, currentMonth, currentYear), MyConstant.TAG_FRAGMENT_CALENDAR_MONTH)
                .commit();
    }

    public static void toggleCalendar(AppCompatActivity activity_context, int res_container, ArrayList<EventMonth> listEventMonth) {

        int currentMonth = CalendarUtils.getCurrentMonth();
        int currentYear = CalendarUtils.getCurrentYear();

        toggleCalendar(activity_context, res_container, listEventMonth, currentMonth, currentYear);
    }

    public static void toggleCalendar(AppCompatActivity activity_context, int res_container, ArrayList<EventMonth> listEventMonth, int currentMonth, int currentYear) {

        if (flagOpeningCalendar) {
            close(activity_context);
        } else {
            show(activity_context, res_container, listEventMonth, currentMonth, currentYear);
        }
    }

    public static void show(AppCompatActivity activity_context, int res_container, ArrayList<EventMonth> listEventMonth) {
        int currentMonth = CalendarUtils.getCurrentMonth();
        int currentYear = CalendarUtils.getCurrentYear();
        int dayOfWeekFirstDayInMonth = CalendarUtils.getFirstDayOfWeekInMonth(currentMonth, currentYear);
        activity_context.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.in_top, R.anim.out_top)
                .add(res_container, CalendarFragment.newInstance(listEventMonth, dayOfWeekFirstDayInMonth, currentMonth, currentYear), MyConstant.TAG_FRAGMENT_CALENDAR_MONTH)
                .commit();
        flagOpeningCalendar = true;
    }

    public static void show(AppCompatActivity activity_context, int res_container, ArrayList<EventMonth> listEventMonth, int currentMonth, int currentYear) {
        int dayOfWeekFirstDayInMonth = CalendarUtils.getFirstDayOfWeekInMonth(currentMonth, currentYear);
        activity_context.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.in_top, R.anim.out_top)
                .add(res_container, CalendarFragment.newInstance(listEventMonth, dayOfWeekFirstDayInMonth, currentMonth, currentYear), MyConstant.TAG_FRAGMENT_CALENDAR_MONTH)
                .commit();
        flagOpeningCalendar = true;
    }

    public static void close(AppCompatActivity activity_context) {
        activity_context.getSupportFragmentManager().beginTransaction()
                //.setCustomAnimations(R.anim.in_top,R.anim.out_top)
                .remove(activity_context.getSupportFragmentManager().findFragmentByTag(MyConstant.TAG_FRAGMENT_CALENDAR_MONTH))
                .commit();
        flagOpeningCalendar = false;
    }

    public static boolean isDisplayCalendar() {
        return flagOpeningCalendar;
    }

    public static int getTheme() {
        return theme;
    }

    public static void setTheme(int theme) {
        CalendarFragment.theme = theme;
    }

    public static void setTextDayColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textDayColor = res_color;
        }
    }

    public static void setTextMonthColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textMonthColor = res_color;
        }
    }

    public static void setTextDayHeaderColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textDayHeader = res_color;
        }
    }

    public static void setTextViewColor(TextView tv, int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tv.setTextColor(res_color);
        }
    }

    public static void setBackgroundCalendar(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            backgroundCalendarColor = res_color;
        }

    }

    public static void setBackgroundHeaderDayCalendar(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            backgroundHeaderDay = res_color;
        }
    }

    private void setBackgroundLayout(LinearLayout layout, int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            layout.setBackgroundColor(res_color);
        }
    }

    public static void setBackgroundHeaderMonthCalendar(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            backgroundHeaderMonth = res_color;
        }
    }

    public static void setCircleTodayHighlightColor(int res_color) {
        if (res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            circleColorToday = res_color;
        }
    }

    public static void setOnClickDayCalendar(View.OnClickListener onClickListener) {
        CalendarFragment.onClickListener = onClickListener;
    }

}
