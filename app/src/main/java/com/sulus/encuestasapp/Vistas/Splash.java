package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sulus.encuestasapp.R;

public class Splash extends AppCompatActivity {

    public static final String TAG = Splash.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(Splash.this,
                        com.sulus.encuestasapp.Vistas.MainActivity.class);

                startActivity(i);
                finish();
            }
        }, secondsDelayed * 1000);


    }
}
