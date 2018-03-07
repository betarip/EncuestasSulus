package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sulus.encuestasapp.R;

public class ActividadControl extends AppCompatActivity {

    private Button btnSubirEncuestas, btnIniciarEncuestas, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_control);
        btnIniciarEncuestas = (Button) findViewById(R.id.boton_comenzar);
        btnSubirEncuestas = (Button) findViewById(R.id.boton_comenzar);
        btnCerrarSesion = (Button) findViewById(R.id.boton_cerrar);
    }

    public void subirEncuestas(View view) {
        hacerAviso("Subir encuestas");
    }

    public void configurarEncuesta(View view) {
        Intent i = new Intent(this,
                com.sulus.encuestasapp.Vistas.ActividadInicioEncuesta.class);

        startActivity(i);
    }

    public void cerrarSesion(View view) {

    }

    public void hacerAviso(String cadena) {
        Toast toast = Toast.makeText(getApplicationContext(), cadena,
                Toast.LENGTH_SHORT);
    }

}
