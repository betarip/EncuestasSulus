package com.sulus.encuestasapp.Vistas;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sulus.encuestasapp.R;

/**
 * Created by ivan on 07/03/2018.
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_ITEMS = 5;
    private Context _context;



    public TabsPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        _context = c;
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
                return SelectionFragment.newInstance(_context.getResources().getString(R.string.pregunta1), 1);
            case 1:
                return SelectionFragment.newInstance(_context.getResources().getString(R.string.pregunta2), 2);
            case 2:
                return SelectionFragment.newInstance(_context.getResources().getString(R.string.pregunta3), 3);
            case 3:
                return SelectionFragment.newInstance(_context.getResources().getString(R.string.pregunta4), 4);
            case 4:
                return SelectionTexto.newInstance(_context.getResources().getString(R.string.pregunta5), 5);

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
