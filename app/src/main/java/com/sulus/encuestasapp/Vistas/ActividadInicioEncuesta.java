package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sulus.encuestasapp.R;

public class ActividadInicioEncuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_inicio_encuesta);
    }

    public void iniciarEncuesta(View view) {
        Intent i = new Intent(this,
                com.sulus.encuestasapp.Vistas.EncuestasView.class);
        startActivity(i);
    }
}
