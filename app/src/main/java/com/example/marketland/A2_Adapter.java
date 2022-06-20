package com.example.marketland;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class A2_Adapter extends FragmentPagerAdapter {
    int totaltabs;
    public A2_Adapter(FragmentManager fm, Context context, int total_tabs) {
        super(fm);this.totaltabs=total_tabs;
    }
    @NonNull @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new A2_Fragment_Login();
            case 1:
                return new A2_Fragment_Signup();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totaltabs;
    }
}
