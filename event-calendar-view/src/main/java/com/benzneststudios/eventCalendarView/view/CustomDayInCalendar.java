package com.benzneststudios.eventCalendarView.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.benzneststudios.eventCalendarView.R;
import com.benzneststudios.eventCalendarView.fragment.CalendarFragment;


/**
 * Created by benznest on 07-Jul-16.
 */
public class CustomDayInCalendar extends FrameLayout {

    int month;
    int year;
    int dayOfWeek;

    String event_id;

    TextView tvDay;
    ImageView ImgHighLight;
    ImageView viewSmallHighLight;

    public CustomDayInCalendar(Context context) {
        super(context);
        initInflate();
        intInstance();
    }

    public CustomDayInCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        intInstance();
    }

    public CustomDayInCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        intInstance();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomDayInCalendar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        intInstance();
    }

    private void intInstance() {
        tvDay = (TextView) findViewById(R.id.tv_day);
        ImgHighLight = (ImageView) findViewById(R.id.img_highlight);
        viewSmallHighLight = (ImageView)findViewById(R.id.view_small_highlight);

        ImgHighLight.setImageDrawable(null);
        viewSmallHighLight.setBackgroundColor(Color.TRANSPARENT);

    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_day_in_calendar,this);

    }

    public void setDayText(String day){
        tvDay.setText(day);
    }

    public void setEnableHighlight(boolean highlighr){
        if(highlighr) {
            Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.shape_circle_day);
            ImgHighLight.setImageDrawable(drawable);
        }
        else{
            ImgHighLight.setImageDrawable(null);
        }
    }

    public void setSmallHighLight(int res_color){
        GradientDrawable drawable = (GradientDrawable) viewSmallHighLight.getDrawable();
        drawable.setColor(res_color);
    }

    public void setCircleHighLight(int res_color){
        GradientDrawable drawable = (GradientDrawable) ImgHighLight.getDrawable();
        drawable.setColor(res_color);
    }

    public void setViewSmallHighLightVisiblility(int visiblility){
        viewSmallHighLight.setVisibility(visiblility);
    }

    public  void setTextDayColor(int res_color){
        if(res_color != ListMyEventView.NOT_CUSTOM_COLOR) {
            tvDay.setTextColor(res_color);
        }
    }

    public void setOnClickDayCalendar(OnClickListener onClickListener){
        this.setTag(CalendarFragment.KEY_DAY , tvDay.getText().toString());
        this.setTag(CalendarFragment.KEY_EVENT_ID , event_id);
        this.setTag(CalendarFragment.KEY_MONTH , month);
        this.setTag(CalendarFragment.KEY_YEAR, year);
        this.setTag(CalendarFragment.KEY_DAY_OF_WEEK , dayOfWeek);

        this.setOnClickListener(onClickListener);
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

}
