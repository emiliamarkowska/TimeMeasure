package com.example.timemeasure;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
   // private OnFragmentInteractionListener mListener;

     private TextView timeTextView;
    /* private Button addButton;
    private Spinner categorySpinner;
    private SeekBar timeSeekBar;*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //ADD TO DATABASE
        ///answerMessage = parent.getItemAtPosition(position).toString();
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.category_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);
        timeSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        timeTextView.setText("Time: " + progress);
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
            @Override
            public void onClick(View v) {
                //Adding to database
                //TODO
               // messageSendListener.OnMessageSend(answerMessage);
            }
        });

        return view;
    }






}
