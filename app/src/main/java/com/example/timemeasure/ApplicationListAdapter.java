package com.example.timemeasure;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ApplicationListAdapter extends ArrayAdapter<ApplicationUsageData> {
    private Context context;

    public ApplicationListAdapter(Context context, int resource, ArrayList<ApplicationUsageData> objects) {
        super(context, resource, objects);
        this.context = context;
    }
}
