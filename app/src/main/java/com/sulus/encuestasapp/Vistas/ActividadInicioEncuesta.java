package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.Clases.Municipio;
import com.sulus.encuestasapp.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ActividadInicioEncuesta extends AppCompatActivity {

    EditText edtApp1, edtApp2, edtNombre;
    Spinner spMunicipio, spSeccion;
    ArrayList<Municipio> listaMunicipios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_inicio_encuesta);
        edtApp1 = (EditText) findViewById(R.id.edit_app1);
        edtApp2 = (EditText) findViewById(R.id.edit_app2);
        edtNombre = (EditText) findViewById(R.id.edit_nombre);
        spMunicipio = (Spinner) findViewById(R.id.municipio_spinner);
        spSeccion = (Spinner) findViewById(R.id.seccion_spinner);
        InputStream inputStream= this.getResources().openRawResource(R.raw.ejemplomunicipio);

       listaMunicipios = Municipio.jsonToMunicipios(inputStream);



        ArrayAdapter<Municipio> adapter =
                new ArrayAdapter<Municipio>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, listaMunicipios);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spMunicipio.setAdapter(adapter);

        List<String> list = new ArrayList<String>();
        list.add("Selecciona un municipio");

        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, list);

        spSeccion.setAdapter(adapter2);

        spMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //stuff here to handle item selection
                // TODO Auto-generated method stub
                String sp1= String.valueOf(spMunicipio.getSelectedItem());
                Toast.makeText(getBaseContext(), sp1, Toast.LENGTH_SHORT).show();
                List<String> listSecciones = new ArrayList<String>();
                Municipio seleccionado = Municipio.getPorNombre(sp1,listaMunicipios);
                if(seleccionado != null) {
                    for (Integer seccion : seleccionado.getSecciones()) {
                        listSecciones.add(seccion.toString());
                    }
                }else{
                    listSecciones.add("Seelccione un municipio");
                }


                ArrayAdapter<String> adapter2 =
                        new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, listSecciones);
                spSeccion.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
