package com.example.timemeasure;


import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

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
    CalendarFragment calendarFragment;
    int firstTime;
    public ActivitiesFragment() {
        // Required empty public constructor
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        getFromDate = view.findViewById(R.id.selectFromDateButton);
        appListCL = view.findViewById(R.id.mainLayout);
        applicationRecyclerView = view.findViewById(R.id.extraActivitiesRV);
        this.dataBaseHelper = ((MainActivity)getActivity()).getDbHelper();
        uStats = new UStats();
       try{
           Bundle bundle = getArguments();
           String datePicked = bundle.getString("datePicked");
           getFromDate.setText(datePicked);
       } catch (NullPointerException e){}



        getFromDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddExtraActivityFragment addExtraActivityFragment = new AddExtraActivityFragment();
                CalendarFragment calendarFragment = new CalendarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, calendarFragment );
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

                try {
                    uStats.printCurrentUsageStatus(getContext(), dataBaseHelper);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                applicationRecyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
                applicationAdapter = new ApplicationAdapter((MainActivity)getActivity(), dataBaseHelper.getApplicationUsageDataList(),dataBaseHelper);
                applicationRecyclerView.setAdapter(applicationAdapter);


        return view;
    }

    public void updateDate(String date)
    {
        getFromDate.setText(date);
    }



}
