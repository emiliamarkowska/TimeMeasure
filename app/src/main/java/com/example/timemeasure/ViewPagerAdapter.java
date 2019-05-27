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
        AddExtraActivityFragment addExtraActivityFragment = new AddExtraActivityFragment();

        if(position == 0) {return activitiesFragment;}
        if(position == 1) {return statisticsFragment;}
        if(position == 2) {return  addExtraActivityFragment;}
        else return achievementsFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {return "Activities";}
        if(position == 1) {return "Statistics";}
        if(position == 2) {return "Add";}
        else return "Achievements";
    }
}
