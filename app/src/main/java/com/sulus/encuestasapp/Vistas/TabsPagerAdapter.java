package com.sulus.encuestasapp.Vistas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ivan on 07/03/2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEMS = 5;


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
                return SelectionFragment.newInstance("¿Pregunta                        " +
                        "nueva porpe que es larga ye es la de prueba?", 1);
            case 1:
                return SelectionFragment.newInstance("¿Pregunta                        " +
                        "nueva porpe que es larga ye es la de prueba?", 2);
            case 2:
                return SelectionFragment.newInstance("¿Pregunta                        " +
                        "nueva porpe que es larga ye es la de prueba?", 3);
            case 3:
                return SelectionFragment.newInstance("¿Pregunta                        " +
                        "nueva porpe que es larga ye es la de prueba?", 4);
            case 4:
                return SelectionFragment.newInstance("¿Pregunta                        " +
                        "nueva porpe que es larga ye es la de prueba?", 5);

            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if(position != 5)
            return "Pregunta " + (position+1);
        else{
            return "Respuesta";
        }
    }
}
