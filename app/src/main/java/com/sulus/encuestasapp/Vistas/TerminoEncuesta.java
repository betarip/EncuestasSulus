package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

import java.util.Date;

public class TerminoEncuesta extends AppCompatActivity {

    private ImageView checkView;
    private ImageView crossView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termino_encuesta);

        checkView = (ImageView) findViewById(R.id.check);
        //crossView = (ImageView) findViewById(R.id.cross);

        ((Animatable) checkView.getDrawable()).start();

        /*
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/

    }

    public void regresoPanel(View view) {
        /*
        * Guardar informacion en el SQLite
        * */
        Encuesta.getEncuestaNueva().setFechaCaptura(new Date());
        Toast.makeText(this, Encuesta.getEncuestaNueva().toString(), Toast.LENGTH_LONG).show();
        Encuesta.cleanEncuestaNueva();

        Intent home = new Intent(this, ActividadControl.class);

        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);
    }
}
