package com.benzneststudios.eventCalendarView;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.benzneststudios.eventCalendarView.fragment.CalendarFragment;
import com.benzneststudios.eventCalendarView.fragment.MainFragment;
import com.benzneststudios.eventCalendarView.model.EventMonth;
import com.benzneststudios.eventCalendarView.view.ListMyEventView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean flagOpeningCalendar;

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
        int i = item.getItemId();
        if (i == R.id.menu_calendar) {
            toggleCalendar();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleCalendar() {
        ArrayList<EventMonth> listEventMonth = ListMyEventView.getSampleData();
        CalendarFragment.setTheme(CalendarFragment.THEME_LIGHT);

        CalendarFragment.setBackgroundHeaderMonthCalendar(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
        CalendarFragment.setBackgroundHeaderDayCalendar(ContextCompat.getColor(MainActivity.this , R.color.colorAccent_second));

        CalendarFragment.setTextMonthColor(ContextCompat.getColor(MainActivity.this , R.color.white));

        CalendarFragment.setOnClickDayCalendar(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"EVENT_ID = "+v.getTag(CalendarFragment.KEY_EVENT_ID),Toast.LENGTH_SHORT).show();
            }
        });


        CalendarFragment.toggleCalendar(MainActivity.this , R.id.content_container , listEventMonth);


    }


}
