package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

public class ActividadInicioEncuesta extends AppCompatActivity {

    EditText edtMunicipio, edtSeccion, edtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_inicio_encuesta);
        edtMunicipio = (EditText) findViewById(R.id.edit_muni);
        edtSeccion = (EditText) findViewById(R.id.edit_sec);
        edtNombre = (EditText) findViewById(R.id.edit_nombre);
    }

    public void iniciarEncuesta(View view) {
        Encuesta nueva = new Encuesta();
        nueva.setMunicipio(edtMunicipio.getText().toString());
        nueva.setSeccion(edtSeccion.getText().toString());
        nueva.setNombre(edtNombre.getText().toString());

        Encuesta.setEncuestaNueva(nueva);
        Intent i = new Intent(this,
                com.sulus.encuestasapp.Vistas.EncuestasView.class);
        startActivity(i);
    }
}
