package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cynoteck.petofyOPHR.fragments.ClinicVisitFragment;
import com.cynoteck.petofyOPHR.fragments.OnlineVisitFragment;

public class MyAdapter  extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ClinicVisitFragment clinicVisitFragment = new ClinicVisitFragment();
                return clinicVisitFragment;
            case 1:
                OnlineVisitFragment onlineVisitFragment = new OnlineVisitFragment();
                return onlineVisitFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
