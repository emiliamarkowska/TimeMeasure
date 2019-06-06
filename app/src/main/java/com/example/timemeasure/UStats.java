package com.example.timemeasure;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 3/2/15.
 */
public class UStats {
    public static DataBaseHelper db;
    public static Context contextHere;
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    private static final String TAG = UStats.class.getSimpleName();
    @SuppressWarnings("ResourceType")


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static List<UsageStats> getUsageStatsList(Context context){
        //initializing UsageStatsManager object
        UsageStatsManager usm = getUsageStatsManager(context);
        contextHere = context;
        //Setting date range
        Calendar calendar = Calendar.getInstance();

        //End time is equal to current date in milliseconds
        long endTime = calendar.getTimeInMillis();

        //Start time is equal to yesterday's date  in milliseconds
       calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE ),0,0,0);
        long startTime = calendar.getTimeInMillis();

        //Writing date range in logs
        Log.d(TAG, "Range start" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,startTime,endTime);
        return usageStatsList;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void printUsageStats(List<UsageStats> usageStatsList) throws PackageManager.NameNotFoundException {
        for (UsageStats u : usageStatsList){
            if(convertMilisecondsIntoMinutes((int)u.getTotalTimeInForeground()) > 0)
            {
                PackageInfo packageInfo = new PackageInfo();
                String appName = packageInfo.packageName;
                        //applicationInfo.loadLabel(contextHere.getPackageManager()).toString();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.now();
//                if(db.isEmpty() || db.isDateChanged())
//                {
                    db.addApplication(new ApplicationUsageData(u.getPackageName(), convertMilisecondsIntoMinutes((int)u.getTotalTimeInForeground()), dtf.format(localDate)));
//                }
//                else
//                {
//                    db.updateRecords((convertMilisecondsIntoMinutes((int)u.getTotalTimeInForeground())), dtf.format(localDate));
//                }

            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void printCurrentUsageStatus(Context context, DataBaseHelper db) throws PackageManager.NameNotFoundException {
        this.db = db;
        printUsageStats(getUsageStatsList(context));
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("ResourceType")
    private static UsageStatsManager getUsageStatsManager(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        return usm;
    }

    public static int convertMilisecondsIntoMinutes(int Miliseconds)
    {
        return Miliseconds/60000;
    }
}


