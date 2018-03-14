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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectionTexto extends Fragment implements LocationListener {

    private static final String ARG_PARAM1 = "pregunta";
    private static final String ARG_PARAM2 = "indice";

    Button btnFinalizar;
    EditText edtRespuesta;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Integer mParam2;

    private Encuesta actual = Encuesta.getEncuestaNueva();

    int respuesta;
    LocationManager locationManager;
    String provider;
    Double longitud, latitud;
    public static final int REQUEST_LOCATION = 1;


    public SelectionTexto() {
        // Required empty public constructor
    }

    public static SelectionTexto newInstance(String param1, Integer param2) {
        SelectionTexto fragment = new SelectionTexto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);// Creating an empty criteria object
        Criteria criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null && !provider.equals("")) {

            if (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                getActivity().requestPermissions(
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION);
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 20000, 1, this);
            if(location!=null)
                onLocationChanged(location);
            else
                Toast.makeText(getActivity().getBaseContext(), "No se puede obtener la localizacion", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getActivity().getBaseContext(), "No existe proovedor", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_selection_texto, container, false);
        TextView tvLabel = (TextView) myFragmentView.findViewById(R.id.pregunta);
        tvLabel.setText(mParam2 + " - " + mParam1);

        btnFinalizar = (Button) myFragmentView.findViewById(R.id.finalizar);
        btnFinalizar.setOnClickListener(btn_enterOnClickListener);

        edtRespuesta = (EditText) myFragmentView.findViewById(R.id.edit_respue);
        RadioGroup radioGroup = (RadioGroup) myFragmentView.findViewById(R.id.preguntasSele);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.radio_res1:
                        respuesta = 1;
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtRespuesta.setText("");
                        break;
                    case R.id.radio_res2:
                        respuesta = 2;
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        edtRespuesta.setText("");
                        break;
                    case R.id.radio_res3:
                        respuesta = 3;
                        edtRespuesta.requestFocus();
                        break;
                }
            }
        });
        return myFragmentView;
    }


    View.OnClickListener btn_enterOnClickListener
            = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            String respuestaFinal="";
            switch (respuesta){
                case 1:
                    respuestaFinal = "BECA";
                    break;
                case 2:
                    respuestaFinal = "CRÉDITO";
                    break;
                case 3:
                    respuestaFinal = edtRespuesta.getText().toString();
                    break;
            }


            String textPassToB = respuestaFinal;
            String[] respuestas = actual.getRespuestas();
            respuestas[mParam2-1]=textPassToB;
            Encuesta guardada = Encuesta.getEncuestaNueva();
            guardada.setLat(latitud);
            guardada.setLongi(longitud);
            guardada.setRespuestas(respuestas);


            boolean banderaRes= true;
            for(int i = 0; i < respuestas.length ;i++){
                if(respuestas[i] == null){
                    banderaRes = false;
                    break;
                }
            }


            if(banderaRes) {
                Toast.makeText(getActivity(),
                        "Finalizar encuesta",
                        Toast.LENGTH_LONG).show();

                /*GUARDAR ENCUESTA Y SALIR*/

                Intent home = new Intent(getActivity(), TerminoEncuesta.class);

                home.addCategory(Intent.CATEGORY_HOME);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);

            }else{
                Toast.makeText(getActivity(),
                        "Faltan responder algunas preguntas",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onLocationChanged(Location location) {
        // Setting Current Longitude
        longitud = (location.getLongitude());

        // Setting Current Latitude
        latitud= (location.getLatitude() );
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
