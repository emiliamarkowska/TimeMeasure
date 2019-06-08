package com.example.timemeasure;

public class ApplicationUsageData {
    int id;
    String packageName;
    long timeInMiliseconds;
    String date;

    ApplicationUsageData(){}
    ApplicationUsageData(String packageName, long timeInMiliseconds, String date)
    {
        this.packageName = packageName;
        this.timeInMiliseconds = convertMilisecondsIntoMinutes(timeInMiliseconds);
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getTimeInMiliseconds() {
        return timeInMiliseconds;
    }

    public void setTimeInMiliseconds(long timeInMiliseconds) {
        this.timeInMiliseconds = convertMilisecondsIntoMinutes(timeInMiliseconds);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static long convertMilisecondsIntoMinutes(long Miliseconds)
    {
        return Miliseconds/60000;
    }
}
