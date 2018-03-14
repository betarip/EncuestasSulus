package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private TextInputLayout tilNombre, tilApp1, tilApp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_inicio_encuesta);
        edtApp1 = (EditText) findViewById(R.id.edit_app1);

        edtApp2 = (EditText) findViewById(R.id.edit_app2);
        edtNombre = (EditText) findViewById(R.id.edit_nombre);
        edtNombre.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        edtApp1.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        edtApp2.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        tilNombre = (TextInputLayout) findViewById(R.id.edit_nombre_layout);
        tilApp1 = (TextInputLayout) findViewById(R.id.edit_app1_layout);
        tilApp2 = (TextInputLayout) findViewById(R.id.edit_app2_layout);



        spMunicipio = (Spinner) findViewById(R.id.municipio_spinner);
        spSeccion = (Spinner) findViewById(R.id.seccion_spinner);
        InputStream inputStream = this.getResources().openRawResource(R.raw.municipios);

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
                String sp1 = String.valueOf(spMunicipio.getSelectedItem());
                //Toast.makeText(getBaseContext(), sp1, Toast.LENGTH_SHORT).show();
                List<String> listSecciones = new ArrayList<String>();
                Municipio seleccionado = Municipio.getPorNombre(sp1, listaMunicipios);
                if (seleccionado != null) {
                    for (Integer seccion : seleccionado.getSecciones()) {
                        listSecciones.add(seccion.toString());
                    }
                } else {
                    listSecciones.add("Seleccione un municipio");
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


        /*
        * Validar Datos de la encuesta
        *
        * */
        if (nombreValido() && app1Valido() && app2Valido()) {
            Encuesta nueva = new Encuesta();
            //nueva.setMunicipio(edtMunicipio.getText().toString());
            //nueva.setSeccion(edtSeccion.getText().toString());

            nueva.setNombre(edtNombre.getText().toString().replace("Ñ", "N"));
            nueva.setApp1(edtNombre.getText().toString().replace("Ñ", "N"));
            nueva.setApp2(edtNombre.getText().toString().replace("Ñ", "N"));
            nueva.setMunicipio(String.valueOf(spMunicipio.getSelectedItem()));
            nueva.setSeccion(String.valueOf(spSeccion.getSelectedItem()));
            Encuesta.setEncuestaNueva(nueva);
            Intent i = new Intent(this,
                    com.sulus.encuestasapp.Vistas.EncuestasView.class);
            startActivity(i);
        }
    }


    /*
    * Validar Campos de los Campos de Texto
    * */

    private boolean nombreValido() {
        String cadena = edtNombre.getText().toString();
        if (!cadena.equals("")) {
            tilNombre.setErrorEnabled(false);
            return true;
        } else {
            String mensaje = "Se requiere un nombre";
            tilNombre.setErrorEnabled(true);
            tilNombre.setError(mensaje);
            return false;
        }
    }

    private boolean app1Valido() {
        String cadena = edtApp1.getText().toString();
        if (!cadena.equals("")) {
            tilApp1.setErrorEnabled(false);
            return true;
        } else {
            String mensaje = "Se requiere campp";
            tilApp1.setErrorEnabled(true);
            tilApp1.setError(mensaje);
            return false;
        }
    }

    private boolean app2Valido() {
        String cadena = edtApp2.getText().toString();
        if (!cadena.equals("")) {
            tilApp2.setErrorEnabled(false);
            return true;
        } else {
            String mensaje = "Se requiere campo";
            tilApp2.setErrorEnabled(true);
            tilApp2.setError(mensaje);
            return false;
        }
    }



}
