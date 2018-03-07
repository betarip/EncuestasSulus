package com.sulus.encuestasapp.Vistas;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.sulus.encuestasapp.R;

public class EncuestasView extends AppCompatActivity {

    private ViewPager viewPager;
    TabsPagerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestas_view);
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        myAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_neutro:
                if (checked)
                    // Pirates are the best

                    break;
            case R.id.radio_feliz:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_enojado:
                if (checked)
                    // Ninjas rule
                    break;
        }

        viewPager.setCurrentItem(getItem(+1),true);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


}
