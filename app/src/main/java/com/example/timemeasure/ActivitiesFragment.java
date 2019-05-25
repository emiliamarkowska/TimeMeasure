package com.example.timemeasure;


import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    private  DataBaseHelper dataBaseHelper;
    private  ApplicationAdapter applicationAdapter;
    public  RecyclerView applicationRecyclerView;
    ConstraintLayout appListCL;
    Button getStats;
    UStats uStats;
    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        appListCL = view.findViewById(R.id.appListCl);
        applicationRecyclerView = view.findViewById(R.id.appsRV);
        this.dataBaseHelper = ((MainActivity)getActivity()).getDbHelper();

        getStats = view.findViewById(R.id.stats_btn);
        uStats = new UStats();
        getStats.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                uStats.printCurrentUsageStatus(getContext(), dataBaseHelper);
                applicationRecyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
                applicationAdapter = new ApplicationAdapter((MainActivity)getActivity(), dataBaseHelper.getApplicationUsageDataList(),dataBaseHelper);
                applicationRecyclerView.setAdapter(applicationAdapter);
            }
        });
        return view;
    }



}
