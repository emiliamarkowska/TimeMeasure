package com.example.timemeasure;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddExtraActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String answerMessage;
    private DataBaseHelper dataBaseHelper;
    private TextView timeTextView;
    private int progress_value;
    private  TextView spinnerTextView;


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //GET SPINNER POSITION
        answerMessage = parent.getItemAtPosition(position).toString();
        spinnerTextView.setText(answerMessage);

    }

    public interface OnMessageSendListener
    {
        public void OnMessageSend(String message);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_extra_activity, container, false);
        this.dataBaseHelper = ((MainActivity)getActivity()).getDbHelper();
        final TextView timeTextView = (TextView)view.findViewById(R.id.timeTextView);
        final Button addButton = view.findViewById(R.id.addButton);
        Spinner categorySpinner = view.findViewById(R.id.categorySpinner);
        SeekBar timeSeekBar = view.findViewById(R.id.timeSeekBar);
        final Fragment fragment = this;

        spinnerTextView = view.findViewById(R.id.spinnerTextView);
        spinnerTextView.setText("Sport");

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.category_spinner, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);



        //SEEK BAR LISTENER
        timeSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        int value = 5 * progress;
                        if(value<60) {
                            timeTextView.setText("Time: " + value + " min");
                        }
                        else
                        {
                            timeTextView.setText("Time: " + value/60 + " h " + (value- 60*(value/60)) + " min");
                        }
                        progress_value = progress;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //Adding to database
                dataBaseHelper.addExtraActitivityData(new ExtraActivityData(answerMessage, progress_value*5));
                getFragmentManager().popBackStack();
                Toast.makeText(getActivity(),answerMessage + " " + progress_value*5 + " min",Toast.LENGTH_LONG).show();
             //  getActivity().onBackPressed();

            }
        });

        return view;
    }




}
