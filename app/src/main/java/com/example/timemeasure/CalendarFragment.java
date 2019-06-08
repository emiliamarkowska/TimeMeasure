package com.example.timemeasure;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class CalendarFragment extends android.support.v4.app.Fragment {
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
               String dayOfDate = dayOfMonth + "";
               String monthOfDate = (month + 1) + "";
               String yearOfDate = year + "";
               if(dayOfMonth < 10)
                   dayOfDate = "0" + dayOfDate;
               if(month < 10)
                   monthOfDate = "0" + monthOfDate;
               dateChosen = yearOfDate + "/" + monthOfDate+ "/" + dayOfDate;
               //onDatePickedListener.onDatePicked(dateChosen);
               Bundle bundle = new Bundle();
               bundle.putString("datePicked", dateChosen);

               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

               ActivitiesFragment activitiesFragment = new ActivitiesFragment();
               activitiesFragment.setArguments(bundle);
               fragmentTransaction.replace(R.id.mainLayout, activitiesFragment);
               fragmentTransaction.commit();

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
