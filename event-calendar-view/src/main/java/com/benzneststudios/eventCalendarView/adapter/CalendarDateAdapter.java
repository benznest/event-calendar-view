package com.benzneststudios.eventCalendarView.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.benzneststudios.eventCalendarView.Util.CalendarUtils;
import com.benzneststudios.eventCalendarView.fragment.CalendarFragment;
import com.benzneststudios.eventCalendarView.model.DayCalendar;
import com.benzneststudios.eventCalendarView.view.CustomDayInCalendar;
import com.benzneststudios.eventCalendarView.view.ListMyEventView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by benznest on 07-Jul-16.
 */
public class CalendarDateAdapter extends BaseAdapter {

    ArrayList<DayCalendar> data;
    Calendar calendar = Calendar.getInstance();

    int textDayColor;

    public CalendarDateAdapter(ArrayList<DayCalendar> data) {
        this.data = data;
    }

    View.OnClickListener onClickListener;

    @Override
    public int getCount() {
        return data.size();
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
        CustomDayInCalendar day;
        if (convertView == null) {
            day = new CustomDayInCalendar(parent.getContext());
        }
        else {
            day = (CustomDayInCalendar) convertView;
        }

        day.setEvent_id(data.get(position).getEvent_id());
        day.setMonth(Integer.parseInt(data.get(position).getMonth()));
        day.setYear(Integer.parseInt(data.get(position).getYear()));

        if(data.get(position).getDayOfWeek() != null) {
            day.setDayOfWeek(Integer.parseInt(data.get(position).getDayOfWeek()));
        }

        day.setDayText(data.get(position).getDay());
        if (data.get(position).isEvent()) {
            int point_color = data.get(position).getPointColor();
            if(point_color != -1){
                day.setSmallHighLight(point_color);
            }

            int circle_color = data.get(position).getCircleColor();
            if(circle_color != -1){
                day.setEnableHighlight(true);
                day.setCircleHighLight(circle_color);
            }
        }
        else {
            day.setViewSmallHighLightVisiblility(View.INVISIBLE);
        }

        day.setOnClickDayCalendar(onClickListener);
        day.setTextDayColor(textDayColor);

        String d = data.get(position).getDay();
        String m = data.get(position).getMonth();
        String y = data.get(position).getYear();


        if (CalendarUtils.isToday(d,m,y)) {
            day.setEnableHighlight(true);
            day.setCircleHighLight(CalendarFragment.circleColorToday);
        }

        return day;
    }

    public  void setTextDayColor(int res_color){
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            textDayColor = res_color;
        }
    }

    public void setOnClickDayCalendar(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

}
