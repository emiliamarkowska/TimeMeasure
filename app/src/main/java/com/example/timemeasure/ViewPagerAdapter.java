package com.example.timemeasure;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        AchievementsFragment achievementsFragment = new AchievementsFragment();
        ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        StatisticsFragment statisticsFragment = new StatisticsFragment();

        if(position == 0) {return activitiesFragment;}
        if(position == 1) {return statisticsFragment;}
        else return achievementsFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {return "Activities";}
        if(position == 1) {return "Statistics";}
        else return "Achievements";
    }
}
