package com.example.timemeasure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ExtaActivitiesAdapter extends RecyclerView.Adapter<ApplicationViewHolder>  {

        Context context;

        DataBaseHelper dataBaseHelper;

        List <ExtraActivityData> extraActivityDataList = new ArrayList<>();


    public ExtaActivitiesAdapter(Context context, List <ExtraActivityData> extraActivityDataList, DataBaseHelper dataBaseHelper)
    {
        this.context = context;
        this.extraActivityDataList = extraActivityDataList;
        this.dataBaseHelper =dataBaseHelper;
    }

        @NonNull
        @Override
        public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.application_item, viewGroup, false);
            return new ApplicationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ApplicationViewHolder applicationViewHolder, int i) {
            applicationViewHolder.appName.setText(extraActivityDataList.get(i).getCategory());
            applicationViewHolder.timeOfUsage.setText(String.valueOf(extraActivityDataList.get(i).getTimeInMinutes()));
        //    applicationViewHolder.date.setText(extraActivityDataList.get(i).getDate());

        }

        @Override
        public int getItemCount() {
            return extraActivityDataList.size();
        }

}
