package com.sulus.encuestasapp.Vistas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

import java.util.List;


public class SelectionFragment extends Fragment {

    private static final String ARG_PARAM1 = "pregunta";
    private static final String ARG_PARAM2 = "indice";

    Button A_enter, btnFinalizar;
    String respuesta;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private Integer mParam2;
    private Encuesta actual =Encuesta.getEncuestaNueva();
    View.OnClickListener btn_enterOnClickListener
            = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            String[] respuestas = actual.getRespuestas();
            boolean bandera = true;
            for (int i = 0; i < respuestas.length; i++) {
                if (respuestas[i] == null) {
                    bandera = false;
                    break;
                }
            }


            if (bandera) {
                Toast.makeText(getActivity(),
                        "Finalizar encuesta",
                        Toast.LENGTH_LONG).show();

                /*GUARDAR ENCUESTA Y SALIR*/

                Intent home = new Intent(getActivity(), TerminoEncuesta.class);

                home.addCategory(Intent.CATEGORY_HOME);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);

                /*
                Intent home = new Intent(getActivity(), ActividadControl.class);

                home.addCategory(Intent.CATEGORY_HOME);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);
                */
            }
        }
    };

    public SelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectionFragment newInstance(String param1, Integer param2) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_selection, container, false);
        TextView tvLabel = (TextView) myFragmentView.findViewById(R.id.pregunta);
        tvLabel.setText(mParam2 + " - " + mParam1);
        /*
        A_enter = (Button) myFragmentView.findViewById(R.id.siguiente);
        btnFinalizar = (Button) myFragmentView.findViewById(R.id.finalizar);
        btnFinalizar.setOnClickListener(btn_enterOnClickListener);
*/
        RadioGroup radioGroup = (RadioGroup) myFragmentView.findViewById(R.id.preguntasSele);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected


                switch (checkedId) {
                    case R.id.radio_neutro:
                        respuesta = "1";
                        break;
                    case R.id.radio_feliz:
                        respuesta = "0";
                        break;
                    case R.id.radio_enojado:
                        respuesta = "2";
                        break;
                }

                String textPassToB = respuesta;
                String[] respuestas = actual.getRespuestas();
                respuestas[mParam2-1]=textPassToB;
                Encuesta.getEncuestaNueva().setRespuestas(respuestas);
                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        ((EncuestasView) getActivity()).siguientePregunta();
                    }
                }, secondsDelayed * 300);

            }
        });

        return myFragmentView;

    }

}
