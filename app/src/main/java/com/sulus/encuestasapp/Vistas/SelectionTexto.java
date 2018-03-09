package com.sulus.encuestasapp.Vistas;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class SelectionTexto extends Fragment {

    private static final String ARG_PARAM1 = "pregunta";
    private static final String ARG_PARAM2 = "indice";

    Button  btnFinalizar;
    EditText edtRespuesta;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Integer mParam2;

    private Encuesta actual =Encuesta.getEncuestaNueva();

    String respuesta;


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
                        respuesta = "1";
                        break;
                    case R.id.radio_res2:
                        respuesta = "0";
                        break;
                    case R.id.radio_res3:
                        respuesta = edtRespuesta.getText().toString();
                        break;
                }

                String textPassToB = respuesta;
                String[] respuestas = actual.getRespuestas();
                respuestas[mParam2-1]=textPassToB;
                Encuesta.getEncuestaNueva().setRespuestas(respuestas);

                ((EncuestasView) getActivity()).siguientePregunta();


            }
        });

        return myFragmentView;
    }


    View.OnClickListener btn_enterOnClickListener
            = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            String[] respuestas = actual.getRespuestas();
            boolean bandera = true;
            for(int i = 0; i < respuestas.length ;i++){
                if(respuestas[i] == null){
                    bandera = false;
                    break;
                }
            }


            if(bandera) {
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
            }else{
                Toast.makeText(getActivity(),
                        "Faltan responder algunas preguntas",
                        Toast.LENGTH_LONG).show();
            }
        }
    };
}
