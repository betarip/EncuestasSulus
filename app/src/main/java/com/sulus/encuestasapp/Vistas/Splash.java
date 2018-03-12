package com.sulus.encuestasapp.Vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sulus.encuestasapp.R;

public class Splash extends AppCompatActivity {

    public static final String TAG = Splash.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i;
                SharedPreferences pref = getSharedPreferences("Encuestador", Context.MODE_PRIVATE);
                if (pref.contains("usuario") && pref.contains("pass")) {
                    i = new Intent(Splash.this,
                            com.sulus.encuestasapp.Vistas.ActividadControl.class);
                } else {
                    i = new Intent(Splash.this,
                            com.sulus.encuestasapp.Vistas.MainActivity.class);
                }




                startActivity(i);
                finish();
            }
        }, secondsDelayed * 1000);


    }
}
