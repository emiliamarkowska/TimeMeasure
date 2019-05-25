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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddExtraActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String answerMessage;
    public static DataBaseHelper db;
    private TextView timeTextView;
    private static long miliseconds = 300000;
    private int progress_value;


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //GET SPINNER POSITION
        answerMessage = parent.getItemAtPosition(position).toString();
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

        final TextView timeTextView = (TextView)view.findViewById(R.id.timeTextView);
        Button addButton = view.findViewById(R.id.addButton);
        Spinner categorySpinner = view.findViewById(R.id.catrgorySpinner);
        SeekBar timeSeekBar = view.findViewById(R.id.timeSeekBar);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.category_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
              /*  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.now();
                db.addApplication((new ApplicationUsageData(answerMessage,progress_value*miliseconds , dtf.format(localDate).toString())));*/
               // messageSendListener.OnMessageSend(answerMessage);
            }
        });

        return view;
    }



}
