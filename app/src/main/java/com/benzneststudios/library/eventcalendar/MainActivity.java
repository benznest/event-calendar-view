package com.benzneststudios.library.eventcalendar;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.benzneststudios.library.eventcalendar.fragment.CalendarFragment;
import com.benzneststudios.library.eventcalendar.fragment.MainFragment;
import com.benzneststudios.library.eventcalendar.model.EventMonth;
import com.benzneststudios.library.eventcalendar.view.ListMyEventView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // add fragment.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, MainFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_calendar:
                toggleCalendar();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleCalendar() {
        final ArrayList<EventMonth> listEventMonth = ListMyEventView.getSampleData();
        CalendarFragment.setTheme(CalendarFragment.THEME_LIGHT);
        CalendarFragment.setBackgroundHeaderMonthCalendar(ContextCompat.getColor(MainActivity.this, R.color.colorThursday));
        CalendarFragment.setBackgroundHeaderDayCalendar(ContextCompat.getColor(MainActivity.this, R.color.colorFriday));
        CalendarFragment.setTextMonthColor(ContextCompat.getColor(MainActivity.this, R.color.white));
        CalendarFragment.setCircleTodayHighlightColor(ContextCompat.getColor(MainActivity.this, R.color.colorFriday));
        CalendarFragment.setTextDayHeaderColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));


        CalendarFragment.setOnClickDayCalendar(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEventMonth.add(new EventMonth((int) v.getTag(CalendarFragment.KEY_MONTH), (int) v.getTag(CalendarFragment.KEY_YEAR)));
                CalendarFragment.close(MainActivity.this);
                CalendarFragment.toggleCalendar(MainActivity.this,
                        R.id.content_container,
                        listEventMonth);
                Toast.makeText(MainActivity.this, "EVENT_ID = " + v.getTag(CalendarFragment.KEY_EVENT_ID), Toast.LENGTH_SHORT).show();
            }
        });

        CalendarFragment.toggleCalendar(MainActivity.this, R.id.content_container, listEventMonth);
    }


}
