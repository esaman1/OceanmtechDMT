package com.oceanmtech.dmt.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.oceanmtech.dmt.Fragment.BussinessPostFragment;
import com.oceanmtech.dmt.Fragment.Create_Quotes_Fragment;
import com.oceanmtech.dmt.Fragment.GreetingsFragment;
import com.oceanmtech.dmt.Fragment.HomeFragment;
import com.oceanmtech.dmt.Fragment.SettingFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    int count;

    public FragmentAdapter(FragmentManager fm, int tabcount) {
        super(fm);

        count = tabcount;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {

            case 0:
                return new HomeFragment();
            case 1:
                return new BussinessPostFragment();
            case 2:
                return new GreetingsFragment();
            case 3:
                return new Create_Quotes_Fragment();
            case 4:
                return new SettingFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return count;
    }
}