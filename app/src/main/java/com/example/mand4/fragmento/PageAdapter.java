package com.example.mand4.fragmento;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mand4 on 14/10/2017.
 */

public class PageAdapter extends FragmentPagerAdapter{

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragmento1();
            case 1:
                return new Fragmento3();
            case 2:
                return new Fragmento2();
            default:
                return null;
        }
    }

@Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Fragmento 1";
            case 1:
                return "Fragmento 2";
            case 2:
                return "Fragmento 3";
            default:
                return null;
        }
    }
}
