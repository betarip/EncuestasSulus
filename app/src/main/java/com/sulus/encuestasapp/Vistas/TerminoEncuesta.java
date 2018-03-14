package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.sulus.encuestasapp.R;

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

        Intent home = new Intent(this, ActividadControl.class);

        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);
    }
}
