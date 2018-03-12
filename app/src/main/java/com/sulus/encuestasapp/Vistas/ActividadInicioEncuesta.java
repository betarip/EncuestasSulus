package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.Clases.Municipio;
import com.sulus.encuestasapp.R;

import java.util.ArrayList;

public class ActividadInicioEncuesta extends AppCompatActivity {

    EditText edtApp1, edtApp2, edtNombre;
    Spinner spMunicipio, spSeccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_inicio_encuesta);
        edtApp1 = (EditText) findViewById(R.id.edit_app1);
        edtApp2 = (EditText) findViewById(R.id.edit_app2);
        edtNombre = (EditText) findViewById(R.id.edit_nombre);
        spMunicipio = (Spinner) findViewById(R.id.municipio_spinner);
        spSeccion = (Spinner) findViewById(R.id.seccion_spinner);
        Municipio.obtenerMunicipios(this);



        ArrayAdapter<Municipio> adapter =
                new ArrayAdapter<Municipio>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, Municipio.getMunicipios());
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spMunicipio.setAdapter(adapter);

    }

    public void iniciarEncuesta(View view) {
        Encuesta nueva = new Encuesta();
        //nueva.setMunicipio(edtMunicipio.getText().toString());
        //nueva.setSeccion(edtSeccion.getText().toString());
        nueva.setNombre(edtNombre.getText().toString());

        Encuesta.setEncuestaNueva(nueva);
        Intent i = new Intent(this,
                com.sulus.encuestasapp.Vistas.EncuestasView.class);
        startActivity(i);
    }
}
