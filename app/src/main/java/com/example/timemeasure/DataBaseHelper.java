package com.example.timemeasure;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATABASE_EVENTS_AND_EXTRA_ACTIVITY";
    public static final int DATABASE_VERSION = 1;
    public static final String EVENTS_TABLE_NAME = "EVENTS_TABLE";
    public static final String EVENT_ID = "event_id";
    public static final String EVENT_PACKAGE_NAME = "package_name";
    public static final String EVENT_TIME_OF_USING_IN_MILISECONDS= "time_of_using_in_miliseconds";
    public static final String EVENT_DATE = "date";

    MainActivity activity;

    public DataBaseHelper(Context context, MainActivity activity) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        this.activity = activity;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_EVENTS_TABLE = "CREATE TABLE " +
                EVENTS_TABLE_NAME + "( "  +
                EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EVENT_PACKAGE_NAME + " VARCHAR NOT NULL, " +
                EVENT_TIME_OF_USING_IN_MILISECONDS + " INTEGER NOT NULL," +
                EVENT_DATE + " DATE NOT NULL" +
                ");";
                db.execSQL(SQL_CREATE_EVENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);

    }

    public List<ApplicationUsageData> getApplicationUsageData()
    {
        List<ApplicationUsageData> applications = new ArrayList<ApplicationUsageData>();

        String selectQuery = "SELECT * FROM " + EVENTS_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ApplicationUsageData application = new ApplicationUsageData();
                application.setId(cursor.getInt(0));
                application.setPackageName(cursor.getString(1));
                application.setTimeInMiliseconds(cursor.getInt(2));
                application.setDate(cursor.getString(3));
                applications.add(application);
            } while (cursor.moveToNext());
        }

        return applications;
    }

    public ApplicationUsageData getAppWithIndex(int index)
    {
        return getApplicationUsageData().get(index);
    }
    public void addApplication(ApplicationUsageData application)
    {
        ContentValues cvApps = new ContentValues();
        cvApps.put(EVENT_PACKAGE_NAME, application.getPackageName());
        cvApps.put(EVENT_TIME_OF_USING_IN_MILISECONDS, application.getTimeInMiliseconds());
        cvApps.put(EVENT_DATE, application.getDate());
        SQLiteDatabase db = this.getReadableDatabase();
        db.insert(EVENTS_TABLE_NAME, null, cvApps);

    }

    public String executeCommand()
    {
        return getAppWithIndex(1).getPackageName();
    }
}
