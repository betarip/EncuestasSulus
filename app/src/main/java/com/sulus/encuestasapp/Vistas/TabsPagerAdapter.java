package com.sulus.encuestasapp.Vistas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ivan on 07/03/2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEMS = 6;


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  NUM_ITEMS ;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FirstFragment.newInstance(0, "Pregunta # 1");
            case 1:
                return FirstFragment.newInstance(1, "Pregunta # 2");
            case 2:
                return FirstFragment.newInstance(2, "Pregunta # 3");
            case 3:
                return FirstFragment.newInstance(3, "Pregunta # 4");
            case 4:
                return FirstFragment.newInstance(4, "Pregunta # 5");
            case 5:
                return FirstFragment.newInstance(5, "Respuestas");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Pregunta " + position;
    }
}
