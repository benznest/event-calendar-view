package com.benzneststudios.library.eventcalendar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benzneststudios.library.eventcalendar.R;
import com.benzneststudios.library.eventcalendar.Util.MyUtils;


/**
 * Created by benznest on 05-Jul-16.
 */
public class CustomRowEventCalendar extends FrameLayout {


    String eventId;

    LinearLayout linearLayoutRowEvent;

    TextView tvDayOfMonth;
    TextView tvDayOfWeek;
    TextView tvTitle;
    TextView tvDescription;
    TextView tvTime;



    public CustomRowEventCalendar(Context context) {
        super(context);
        intInflate();
        initInstance();
    }

    public CustomRowEventCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        intInflate();
        initInstance();
    }

    public CustomRowEventCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intInflate();
        initInstance();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomRowEventCalendar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intInflate();
        initInstance();
    }

    private void initInstance() {
        linearLayoutRowEvent = (LinearLayout) findViewById(R.id.linearLayout_row_event);
        tvDayOfMonth = (TextView) findViewById(R.id.tv_day_of_month);
        tvDayOfWeek = (TextView) findViewById(R.id.tv_day_of_week);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvTime = (TextView) findViewById(R.id.tv_time);

    }

    private void intInflate() {
        inflate(getContext(), R.layout.row_event_calendar, this);
    }

    public void setDay(int day) {
        tvDayOfMonth.setText("" + day);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setDayOfWeek(String day) {
        tvDayOfWeek.setText("" + day);
    }

    public void setDescription(String description) {
        tvDescription.setText(description);
    }

    public void setTime(String time) {
        tvTime.setText(time);
    }

    public void setColorDayOfWeek(String color) {
        tvDayOfWeek.setTextColor(MyUtils.convertColor(color));
    }

    public void setColorDayOfWeek(int res_color) {
        tvDayOfWeek.setTextColor(res_color);
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


    public void setOnClickEventItem(OnClickListener onClickListener) {
        linearLayoutRowEvent.setTag(ListMyEventView.KEY_EVENT_ID, eventId);
        linearLayoutRowEvent.setTag(ListMyEventView.KEY_TITLE, tvTitle.getText().toString());
        linearLayoutRowEvent.setTag(ListMyEventView.KEY_DESCRIPTION, tvDescription.getText().toString());
        linearLayoutRowEvent.setTag(ListMyEventView.KEY_DAY, tvDayOfMonth.getText().toString());
        linearLayoutRowEvent.setTag(ListMyEventView.KEY_DAY_OF_WEEK, tvDayOfWeek.getText().toString());
        linearLayoutRowEvent.setTag(ListMyEventView.KEY_TIME, tvTime.getText().toString());
        linearLayoutRowEvent.setOnClickListener(onClickListener);
    }

    public void setTextTitleColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvTitle.setTextColor(res_color);
        }
    }

    public void setTextDescriptionColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvDescription.setTextColor(res_color);
        }
    }

    public void setTextDayColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvDayOfMonth.setTextColor(res_color);
        }
    }

    public void setTextDayOfWeekColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvDayOfWeek.setTextColor(res_color);
        }
    }

    public void setTextTimeColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvTime.setTextColor(res_color);
        }
    }

    public void setBackgroundEventColor(int res_color) {
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            linearLayoutRowEvent.setBackgroundColor(res_color);
        }
    }

}
