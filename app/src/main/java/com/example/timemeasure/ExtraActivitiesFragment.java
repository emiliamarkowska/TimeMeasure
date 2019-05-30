package com.example.timemeasure;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraActivitiesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String dateAnswerSpinner;
    private  DataBaseHelper dataBaseHelper;
    private  ExtaActivitiesAdapter extaActivitiesAdapter;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //GET SPINNER POSITION
        dateAnswerSpinner = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extra_activities, container, false);

        Button addButton = view.findViewById(R.id.addButton);
        Spinner dateSpinner = view.findViewById(R.id.dateSpinner);
        RecyclerView extraActivitiesRV = view.findViewById(R.id.extraActivitiesRV);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.date_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);
        dateSpinner.setOnItemSelectedListener(this);

        this.dataBaseHelper = ((MainActivity)getActivity()).getDbHelper();
        extraActivitiesRV.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
        extaActivitiesAdapter = new ExtaActivitiesAdapter((MainActivity)getActivity(), dataBaseHelper.getExtraActivityDataList(), dataBaseHelper);
        extraActivitiesRV.setAdapter(extaActivitiesAdapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExtraActivityFragment addExtraActivityFragment = new AddExtraActivityFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout, addExtraActivityFragment ); // giving fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        return view;
    }

}
