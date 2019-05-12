package com.project.misae_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int num; // 탭 수

    public PagerAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                TabFragment1 tab1 = new TabFragment1();
                return tab1;

            case 1 :
                TabFragment2 tab2 = new TabFragment2();
                return tab2;

            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
