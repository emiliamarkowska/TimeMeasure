package com.example.timemeasure;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    String dateChosen;
    OnDatePickedListener onDatePickedListener;

    public CalendarFragment()
    {

    }

    public interface OnDatePickedListener
    {
        public void onDatePicked(String datePicked);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.calendar_fragment, container, false);
       calendarView = (CalendarView)view.findViewById(R.id.calendarView1);
       final Fragment fragment = this;
       calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
               dateChosen = year + "/" + month + "/" + dayOfMonth;
               onDatePickedListener.onDatePicked(dateChosen);
           }
       });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;

        try{
            onDatePickedListener = (OnDatePickedListener)activity;
        } catch (ClassCastException cle){
            throw new ClassCastException(activity.toString() + " must implement OnDatePickedListener");
        }

    }
}
