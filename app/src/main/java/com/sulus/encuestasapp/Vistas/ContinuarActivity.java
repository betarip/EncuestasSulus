package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

public class ContinuarActivity extends AppCompatActivity {

    Button continuar;
    int respuesta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_continuar);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.preguntasSituacion);
        continuar = (Button) findViewById(R.id.continuar);
        continuar.setEnabled(false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.radio_res1:
                        respuesta = 1;

                        break;
                    case R.id.radio_res2:
                        respuesta = 2;

                }
                continuar.setEnabled(true);

            }
        });


    }

    public void siguienteEvaluacion(View vista) {
        Encuesta encuesta = Encuesta.getEncuestaNueva();
        encuesta.setContestada(respuesta);
        Intent i = null;
        if (respuesta == 1) {
            //Continuar
            i = new Intent(this, EncuestasView.class);
        } else {
            //Salir
            i = new Intent(this, TerminoEncuesta.class);

            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        startActivity(i);

    }
}
