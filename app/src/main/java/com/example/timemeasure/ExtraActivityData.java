package com.example.timemeasure;

public class ExtraActivityData {
    int ID;
    String category;
    int timeInMinutes;
    String date;
    String comment;

    public ExtraActivityData() {}

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
