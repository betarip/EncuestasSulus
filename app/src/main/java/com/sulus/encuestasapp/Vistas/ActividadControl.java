package com.sulus.encuestasapp.Vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sulus.encuestasapp.R;
import com.sulus.encuestasapp.Utilerias.DatabaseHandler;

public class ActividadControl extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    private Button btnSubirEncuestas, btnIniciarEncuestas, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_control);


        btnIniciarEncuestas = (Button) findViewById(R.id.boton_comenzar);
        btnSubirEncuestas = (Button) findViewById(R.id.boton_subir);
        btnCerrarSesion = (Button) findViewById(R.id.boton_cerrar);

        Resources res = getResources();
        String text = String.format(res.getString(R.string.porSubir), db.getEncuestasCount());

        btnSubirEncuestas.setText(text);
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
        SharedPreferences pref = getSharedPreferences("Encuestador", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        hacerAviso("Sesion Finalizada");
        editor.clear();
        db.onUpgrade(db.getWritableDatabase(), 0, 1);
        Intent i = new Intent(this,
                com.sulus.encuestasapp.Vistas.MainActivity.class);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(i);


    }

    public void hacerAviso(String cadena) {
        Toast toast = Toast.makeText(this, cadena,
                Toast.LENGTH_SHORT);
    }


}
