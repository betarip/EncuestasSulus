package com.sulus.encuestasapp.Vistas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sulus.encuestasapp.R;

public class MainActivity extends ActividadBase {


    /**
     * Componentes para la pantalla
     */
    private Button buscarUsuario;
    private EditText edtUsuario, edtPass;

    private TextInputLayout tilUsuario, tilPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscarUsuario = (Button) findViewById(R.id.boton_ingresar);

        edtUsuario = (EditText) findViewById(R.id.edit_user);
        edtPass = (EditText) findViewById(R.id.edit_pass);

        tilUsuario = (TextInputLayout) findViewById(R.id.edit_user_layout);
        tilPass = (TextInputLayout) findViewById(R.id.edit_pass_layout);
    }


    /*
    *
    * Este metodo activa la funcion del boton detalles
    * */
    public void iniciarSesion(View view) {
        String usuario = edtUsuario.getText().toString();
        String pass = edtPass.getText().toString();
        if (usuarioValido() && passValido()) {
            if (buscarUsuario(usuario, pass)) {
                /*
                * Se queda guardado el inicio de sesión
                * */
                SharedPreferences pref = getSharedPreferences("Encuestador", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("usuario", usuario);
                editor.putString("pass", pass);
                editor.commit();


                Intent i = new Intent(this,
                        com.sulus.encuestasapp.Vistas.ActividadControl.class);

                startActivity(i);

            } else {
                cargarDialog("El usuario no se encontro");
            }
        }


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


    /*
    *
    * Funciones para validar los campos de usuario y posible contraseña
    *
    *
    * */

    private boolean usuarioValido() {
        String cadena = edtUsuario.getText().toString();
        if (!cadena.equals("")) {
            tilUsuario.setErrorEnabled(false);
            return true;
        } else {
            String mensaje = "Usuario invalido";
            tilUsuario.setErrorEnabled(true);
            tilUsuario.setError(mensaje);
            return false;
        }
    }

    private boolean passValido() {
        String cadena = edtPass.getText().toString();
        if (!cadena.equals("")) {
            tilPass.setErrorEnabled(false);
            return true;
        } else {
            String mensaje = "Contraseña invalida";
            tilPass.setErrorEnabled(true);
            tilPass.setError(mensaje);
            return false;
        }
    }

    public boolean buscarUsuario(String usuario, String pass) {


        return usuario.equals("prueba") && pass.equals("prueba1");
    }

    private void cargarDialog(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}
