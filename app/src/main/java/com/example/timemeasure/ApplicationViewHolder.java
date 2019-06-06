package com.example.timemeasure;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ApplicationViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout linearLayout;
    public TextView appName;
    public TextView timeOfUsage;
   // public TextView date;

    public ApplicationViewHolder(@NonNull View itemView) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.linearLayout);
        appName = itemView.findViewById(R.id.appNameTextView);
        timeOfUsage = itemView.findViewById(R.id.usageTimeTextView);
      //  date = itemView.findViewById(R.id.dateTextView);
    }
}
