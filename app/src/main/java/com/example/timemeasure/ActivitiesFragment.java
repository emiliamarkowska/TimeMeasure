package com.example.timemeasure;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    private  DataBaseHelper dataBaseHelper;
    private  ApplicationAdapter applicationAdapter;
    public  RecyclerView applicationRecyclerView;
    ConstraintLayout appListCL;
    Button getFromDate;
    UStats uStats;
    TextView dateTextView;
    CalendarFragment calendarFragment;
    String dateChosen;
    public ActivitiesFragment() {
        // Required empty public constructor
    }



    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        getFromDate = view.findViewById(R.id.selectFromDateButton);
        appListCL = view.findViewById(R.id.mainLayout);
        applicationRecyclerView = view.findViewById(R.id.extraActivitiesRV);
        dateTextView = view.findViewById(R.id.dateTextView);
        this.dataBaseHelper = ((MainActivity)getActivity()).getDbHelper();
        uStats = new UStats();
        dateChosen = getTodaysDate();
        dateTextView.setText(convertDate(dateChosen));

       try{
           Bundle bundle = getArguments();
           String datePicked = bundle.getString("datePicked");
           dateTextView.setText(convertDate(datePicked));
           dateChosen = datePicked;
       } catch (NullPointerException e){}



        getFromDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddExtraActivityFragment addExtraActivityFragment = new AddExtraActivityFragment();
                CalendarFragment calendarFragment = new CalendarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, calendarFragment );
                transaction.addToBackStack(null);// if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

                try {
                    uStats.printCurrentUsageStatus(getContext(), dataBaseHelper);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                applicationRecyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
                applicationAdapter = new ApplicationAdapter((MainActivity)getActivity(), dataBaseHelper.getPhoneUsageFromDate(dateChosen) ,dataBaseHelper);
                applicationRecyclerView.setAdapter(applicationAdapter);


        return view;
    }

    public void updateDate(String date)
    {
        getFromDate.setText(date);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTodaysDate()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date =  dateFormat.format(calendar.getTime());
        return date;
    }

    public String convertDate(String date)
    {
        //2018/06/10
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);

        String monthTem = "";

        if(month.equals("01")) monthTem = "January";
        if(month.equals("02")) monthTem = "February";
        if(month.equals("03")) monthTem = "March";
        if(month.equals("04")) monthTem = "April";
        if(month.equals("05")) monthTem = "May";
        if(month.equals("06")) monthTem = "June";
        if(month.equals("07")) monthTem = "July";
        if(month.equals("08")) monthTem = "August";
        if(month.equals("09")) monthTem = "September";
        if(month.equals("10")) monthTem = "October";
        if(month.equals("11")) monthTem = "November";
        if(month.equals("12")) monthTem = "December";

        String dayTemp = "";
        String firstChar = String.valueOf(day.charAt(0));
        if(firstChar.equals("0"))
            dayTemp = day.charAt(1) + "";
        else dayTemp = day;
        Log.e("day: ", dayTemp);
        Log.e("month: ", month);

        String fullConvertedDate = dayTemp + " " + monthTem + " " + year;
        return  fullConvertedDate;

    }



}
