package com.example.timemeasure;


import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    private  DataBaseHelper dataBaseHelper;
    private BarChart fancyBarChart;
    String dateChosen;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_statistics, container, false);
        this.dataBaseHelper = ((MainActivity)getActivity()).getDbHelper();

        dateChosen = getTodaysDate();
        List<ApplicationUsageData> phoneUsageData = dataBaseHelper.getPhoneUsageFromDate(dateChosen);
        Collections.sort(phoneUsageData, Collections.reverseOrder());

        fancyBarChart = view.findViewById(R.id.barChart);

        List<BarEntry> barEntries = new ArrayList<>();
        List<String> barLabels = new ArrayList<>();
        int position = 0;
        for(ApplicationUsageData usageData: phoneUsageData)
        {
            position++;
            barEntries.add(new BarEntry(position, usageData.getTimeInMiliseconds()));
            barLabels.add(usageData.getPackageName());
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Applications");
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);

        fancyBarChart.animateY(1000);
        fancyBarChart.setData(barData);

        XAxis xAxis = fancyBarChart.getXAxis();
        xAxis.setLabelRotationAngle(-90);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new LabelFormatter(barLabels));
        xAxis.setLabelCount(barLabels.size());

        YAxis yAxisRight = fancyBarChart.getAxisRight();
        yAxisRight.setAxisMinimum(0);

        YAxis yAxisLeft = fancyBarChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setDrawLabels(false);

        fancyBarChart.getLegend().setEnabled(false);
        fancyBarChart.getDescription().setEnabled(false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTodaysDate()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date =  dateFormat.format(calendar.getTime());
        return date;
    }

    private class LabelFormatter extends ValueFormatter {
        private List<String> mLabels;

        public LabelFormatter(List<String> labels) {
            mLabels = labels;
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return mLabels.get((int) value - 1);
        }
    }

}

