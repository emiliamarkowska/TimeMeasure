package com.example.timemeasure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    // DATABASE NAME AND VERSION
    public static final String DATABASE_NAME = "DATABASEEVENTSANDEXTRAACTIVITY1";
    public static final int DATABASE_VERSION = 1;

    //TABLE OF EVENTS AND ITS COLUMNS' NAMES
    public static final String EVENTS_TABLE_NAME = "EVENTS_TABLE";
    public static final String EVENT_ID = "event_id";
    public static final String EVENT_PACKAGE_NAME = "event_package_name";
    public static final String EVENT_TIME_OF_USING_IN_MILISECONDS = "event_time_of_using_in_miliseconds";
    public static final String EVENT_DATE = "event_date";

    //TABLE OF EXTRA ACTIVITIES AND ITS COLUMNS' NAMES
    public static final String EXTRA_ACTIVITY_TABLE_NAME1 = "EXTRA_ACTIVITY_TABLE_NAME1";
    public static final String EXTRA_ACTIVITY_ID = "extra_activity_id";
    public static final String EXTRA_ACTIVITY_CATEGORY1 = "extra_activity_category1";
    public static final String EXTRA_ACTIVITY_TIME_OF_USING_IN_MINUTES = "extra_activity_time_of_using_in_minutes";
    public static final String EXTRA_ACTIVITY_DATE = "extra_activity_date";
    public static final String EXTRA_ACTIVITY_COMMENT = "extra_activity_comment";

    MainActivity activity;

    public DataBaseHelper(Context context, MainActivity activity) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        this.activity = activity;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATING EVENTS TABLE
        final String SQL_CREATE_EVENTS_TABLE = "CREATE TABLE " +
                EVENTS_TABLE_NAME + "( "  +
                EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EVENT_PACKAGE_NAME + " TEXT NOT NULL, " +
                EVENT_TIME_OF_USING_IN_MILISECONDS + " INTEGER NOT NULL," +
                EVENT_DATE + " DATE NOT NULL" +
                ");";
                db.execSQL(SQL_CREATE_EVENTS_TABLE);

        //CREATING EXTRA ACTIVITIES TABLE
        final String SQL_CREATE_EXTRA_ACTIVITIES_TABLE = "CREATE TABLE " +
                EXTRA_ACTIVITY_TABLE_NAME1 + "( " +
                EXTRA_ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EXTRA_ACTIVITY_CATEGORY1 + " TEXT NOT NULL, " +
                EXTRA_ACTIVITY_TIME_OF_USING_IN_MINUTES + " INTEGER NOT NULL, " +
                EXTRA_ACTIVITY_DATE + " DATE NOT NULL, " +
                EXTRA_ACTIVITY_COMMENT + " TEXT" +
                ");";
                db.execSQL(SQL_CREATE_EXTRA_ACTIVITIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXTRA_ACTIVITY_TABLE_NAME1);
        onCreate(db);

    }

    public List<ApplicationUsageData> getApplicationUsageDataList()
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

        cursor.close();
        return applications;
    }

    public ApplicationUsageData getAppWithIndex(int index)
    {
        return getApplicationUsageDataList().get(index);
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

    public List<ExtraActivityData> getExtraActivityDataList()
    {
        List<ExtraActivityData> extraactivities = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + EXTRA_ACTIVITY_TABLE_NAME1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ExtraActivityData extraActivity = new ExtraActivityData();
                extraActivity.setID(cursor.getInt(0));
                extraActivity.setCategory(cursor.getString(1));
                extraActivity.setTimeInMinutes(cursor.getInt(2));
                extraActivity.setDate(cursor.getString(3));
                if(cursor.getString(4).isEmpty())
                    extraActivity.setComment("");
                else
                    extraActivity.setComment(cursor.getString(4));
                    extraactivities.add(extraActivity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return extraactivities;
    }

    public void addExtraActitivityData(ExtraActivityData extraActivityData)
    {
        try {
            ContentValues cvExtraActivities = new ContentValues();
            cvExtraActivities.put(EXTRA_ACTIVITY_CATEGORY1, extraActivityData.getCategory());
            cvExtraActivities.put(EXTRA_ACTIVITY_TIME_OF_USING_IN_MINUTES, extraActivityData.getTimeInMinutes());
            cvExtraActivities.put(EXTRA_ACTIVITY_DATE, extraActivityData.getDate());
            cvExtraActivities.put(EXTRA_ACTIVITY_COMMENT, extraActivityData.getComment());
            SQLiteDatabase db = this.getReadableDatabase();
            db.insert(EXTRA_ACTIVITY_TABLE_NAME1, null, cvExtraActivities);
            db.close();
        } catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public List<ApplicationUsageData> getPhoneUsageFromDate(String date)
    {
        List<ApplicationUsageData> applicationUsageDataList = new ArrayList<>();
        String selectQuery ="SELECT * FROM " + EVENTS_TABLE_NAME + " WHERE "
                + EXTRA_ACTIVITY_DATE + "='" + date +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                ApplicationUsageData applicationUsageData = new ApplicationUsageData();
                applicationUsageData.setId(cursor.getInt(0));
                applicationUsageData.setPackageName(cursor.getString(1));
                applicationUsageData.setTimeInMiliseconds(cursor.getInt(2));
                applicationUsageData.setDate(cursor.getString(3));
                applicationUsageDataList.add(applicationUsageData);
            } while (cursor.moveToNext());
        }
        return applicationUsageDataList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isDateChanged()
    {
        String selectQuery = "SELECT * FROM " + EVENTS_TABLE_NAME + " ORDER BY " +
                EVENT_ID + " DESC LIMIT 1;";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String lastDate = cursor.getString(3);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        if(lastDate.equals(dtf.format(localDate))) return false;
        else return true;
    }

    public void updateRecords(int time, String date)
    {
        String updateQuery = "UPDATE " + EVENTS_TABLE_NAME +
                " SET " +  EVENT_TIME_OF_USING_IN_MILISECONDS + "='" + time +
                "' WHERE " + EVENT_DATE + "='" + date + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateQuery);
    }

    public boolean checkIfExistsGivenRecord(String packageName, String date)
    {
        boolean ifExists = false;
        String checkIf = "SELECT * FROM " + EVENTS_TABLE_NAME +
                " WHERE " + EVENT_PACKAGE_NAME + "='" + packageName + "' AND "
                + EVENT_DATE + "='" + date +"';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(checkIf, null);
        if(cursor.getCount() > 0) ifExists = true;
        else ifExists = false;
        return ifExists;
    }

    public boolean isEmpty()
    {
        boolean isEmpt = true;
        String selectQuery = "SELECT * FROM " + EVENTS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0 ) isEmpt = false;
        return isEmpt;
    }
}
