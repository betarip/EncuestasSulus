package com.sulus.encuestasapp.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.sulus.encuestasapp.R;

public class MainActivity extends ActividadBase {

    private Button buscarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscarUsuario = (Button) findViewById(R.id.boton_ingresar);
    }


    /*
    *
    * Este metodo activa la funcion del boton detalles
    * */
    public void iniciarSesion(View view) {
        Intent i = new Intent(this,
                com.sulus.encuestasapp.Vistas.ActividadControl.class);

        startActivity(i);
    }


    /*
   *
   * Función para que no salga el SplashScreen
   *
   * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home);
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

}
