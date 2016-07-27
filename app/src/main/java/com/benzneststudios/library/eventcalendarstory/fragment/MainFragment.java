package com.benzneststudios.library.eventcalendarstory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.benzneststudios.library.eventcalendarstory.R;
import com.benzneststudios.library.eventcalendarstory.view.ListMyEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    ListMyEvent listMyEvent;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        listMyEvent = (ListMyEvent) rootView.findViewById(R.id.listView_my_event);
        listMyEvent.setDisplayColorDayOfWeek(true);
        listMyEvent.setTheme(ListMyEvent.THEME_LIGHT);

        listMyEvent.setOnClickEventItem(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Event id = " + v.getTag(ListMyEvent.KEY_EVENT_ID).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listMyEvent.setTextTitleColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
//        listMyEvent.setTextDescriptionColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
        listMyEvent.setTextDayColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
//        listMyEvent.setTextDayOfWeekColor  (ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
//        listMyEvent.setTextTimeColor       (ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
//        listMyEvent.setTextMonthColor      (ContextCompat.getColor(MainActivity.this, R.color.theme_dark_text));
//        listMyEvent.setBackgroundEventColor(ContextCompat.getColor(MainActivity.this, R.color.theme_light_background));
//        listMyEvent.setBackgroundMonthColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

        listMyEvent.displaySampleData();
    }
}
