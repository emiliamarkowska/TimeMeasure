package com.example.timemeasure;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Date;

public class ExtraActivityData {
    int ID;
    String category;
    int timeInMinutes;
    String date;
    String comment;

    public ExtraActivityData() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ExtraActivityData(String category, int timeInMinutes)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.ID = 0;
        this.category = category;
        this.timeInMinutes = timeInMinutes;
        this.date = dateFormat.format(date);
        this.comment = "";
    }

    public ExtraActivityData(int ID, String category, int timeInMinutes, String date, String comment) {
        this.ID = ID;
        this.category = category;
        this.timeInMinutes = timeInMinutes;
        this.date = date;
        this.comment = comment;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
