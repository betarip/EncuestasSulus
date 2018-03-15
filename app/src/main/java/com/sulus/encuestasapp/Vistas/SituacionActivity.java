package com.sulus.encuestasapp.Vistas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

public class SituacionActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String provider, respuesta;
    Double longitud, latitud;
    boolean continuarEntrevista;
    EditText edtOtro;
    Button continuar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacion);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);// Creating an empty criteria object
        Criteria criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null && !provider.equals("")) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 20000, 1, this);
            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "No se puede obtener la localizacion", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getBaseContext(), "No existe proovedor", Toast.LENGTH_SHORT).show();
        }
        edtOtro = (EditText) findViewById(R.id.edit_respueSituacion);
        continuar = (Button) findViewById(R.id.continuar);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.preguntasSituacion);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                respuesta = "";
                switch (checkedId) {
                    case R.id.radio_res1:
                        continuarEntrevista = true;
                        respuesta = getBaseContext().getResources().getString(R.string.RespuestaSituacion1);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtOtro.setText("");
                        break;
                    case R.id.radio_res2:
                        continuarEntrevista = false;
                        respuesta = getBaseContext().getResources().getString(R.string.RespuestaSituacion2);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtOtro.setText("");
                        break;
                    case R.id.radio_res3:
                        continuarEntrevista = false;
                        respuesta = getBaseContext().getResources().getString(R.string.RespuestaSituacion3);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtOtro.setText("");
                        break;
                    case R.id.radio_res4:
                        continuarEntrevista = false;
                        respuesta = getBaseContext().getResources().getString(R.string.RespuestaSituacion4);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtOtro.setText("");
                        break;
                    case R.id.radio_res5:
                        continuarEntrevista = false;
                        respuesta = getBaseContext().getResources().getString(R.string.RespuestaSituacion5);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtOtro.setText("");
                        break;
                    case R.id.radio_res6:
                        respuesta = getBaseContext().getResources().getString(R.string.RespuestaSituacion6);
                        continuarEntrevista = false;
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtOtro.setText("");
                        break;
                    case R.id.radio_res7:
                        continuarEntrevista = false;
                        edtOtro.requestFocus();
                        break;
                }
            }
        });


    }

    public void siguienteEvaluacion(View v) {
        Intent i = null;
        if (continuarEntrevista) {
            i = new Intent(this,
                    com.sulus.encuestasapp.Vistas.ContinuarActivity.class);
        } else {
            /**
             * Finalizar entrevista
             */
            if (respuesta.equals("")) {
                respuesta = edtOtro.getText().toString();
            }
            Encuesta guardada = Encuesta.getEncuestaNueva();
            guardada.setSituacion(respuesta);
            guardada.setLat(latitud);
            guardada.setLongi(longitud);
            i = new Intent(this, TerminoEncuesta.class);

            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        }

        startActivity(i);
    }

    @Override
    public void onLocationChanged(Location location) {
        // Setting Current Longitude
        longitud = (location.getLongitude());

        // Setting Current Latitude
        latitud = (location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
