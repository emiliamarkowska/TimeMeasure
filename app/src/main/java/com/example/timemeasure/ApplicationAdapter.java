package com.example.timemeasure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationViewHolder> {

    Context context;
    List<ApplicationUsageData> applicationUsageDataList = new ArrayList<>();
    DataBaseHelper dataBaseHelper;

  //  List <ExtraActivityData> extraActivityDataList = new ArrayList<>();

    public ApplicationAdapter(Context context, List<ApplicationUsageData> applicationUsageDataList, DataBaseHelper dataBaseHelper)
    {
        this.context = context;
        this.applicationUsageDataList = applicationUsageDataList;
        this.dataBaseHelper = dataBaseHelper;
    }

/*    public ApplicationAdapter(Context context, List <ExtraActivityData> extraActivityDataList, DataBaseHelper dataBaseHelper)
    {
        this.context = context;
        this.extraActivityDataList = extraActivityDataList;
        this.dataBaseHelper =dataBaseHelper;
    }*/

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.application_item, viewGroup, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder applicationViewHolder, int i) {
        applicationViewHolder.appName.setText(applicationUsageDataList.get(i).getPackageName());
        if(applicationUsageDataList.get(i).getTimeInMiliseconds() < 60)
        applicationViewHolder.timeOfUsage.setText(String.valueOf(applicationUsageDataList.get(i).getTimeInMiliseconds()) + " min");
        else
        {
            int hours = (int)applicationUsageDataList.get(i).getTimeInMiliseconds() / 60;
            int minutes = (int)applicationUsageDataList.get(i).getTimeInMiliseconds() - hours * 60;
            applicationViewHolder.timeOfUsage.setText(String.valueOf(hours) + "h " + String.valueOf(minutes) +"min");
        }
        //applicationViewHolder.date.setText(applicationUsageDataList.get(i).getDate());

    }

    @Override
    public int getItemCount() {
        return applicationUsageDataList.size();
    }
}
