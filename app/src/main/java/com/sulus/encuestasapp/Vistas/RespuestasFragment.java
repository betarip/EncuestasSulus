package com.sulus.encuestasapp.Vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sulus.encuestasapp.Clases.Encuesta;
import com.sulus.encuestasapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RespuestasFragment extends Fragment {

    TextView respuesta1, respuesta2, respuesta3, respuesta4, respuesta5;
    TextView[] textViewArray = new TextView[5];
    Button btnEnviarEncuesta;

    public RespuestasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuestas, container, false);
        /*
        textViewArray[0] = (TextView) view.findViewById(R.id.respuesta1);
        textViewArray[1] = (TextView) view.findViewById(R.id.respuesta2);
        textViewArray[2] = (TextView) view.findViewById(R.id.respuesta3);
        textViewArray[3] = (TextView) view.findViewById(R.id.respuesta4);
        textViewArray[4] = (TextView) view.findViewById(R.id.respuesta5);
        */
        btnEnviarEncuesta = (Button) view.findViewById(R.id.encuesta);
        btnEnviarEncuesta.setOnClickListener(A_enterOnClickListener);


        respuesta1 = (TextView) view.findViewById(R.id.respuesta1);
        respuesta2 = (TextView) view.findViewById(R.id.respuesta2);
        respuesta3 = (TextView) view.findViewById(R.id.respuesta3);
        respuesta4 = (TextView) view.findViewById(R.id.respuesta4);
        respuesta5 = (TextView) view.findViewById(R.id.respuesta5);

        String myTag = getTag();

        ((EncuestasView) getActivity()).setTabFragmentRespuestas(myTag);

        return view;
    }

    View.OnClickListener A_enterOnClickListener
            = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {



            String[] respuetas = Encuesta.getEncuestaNueva().getRespuestas();
            StringBuilder sb = new StringBuilder();
            for (int i=0; i< respuetas.length; i++){
                sb.append(respuetas[i]);
            }


            Toast.makeText(getActivity(),
                    sb.toString(),
                    Toast.LENGTH_LONG).show();

        }
    };


    public void b_updateText(int index, String t) {
        textViewArray[index].setText(t);
    }

    public void terminarEncuesta(View v){
        String[] respuetas = Encuesta.getEncuestaNueva().getRespuestas();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i< respuetas.length; i++){
            sb.append(respuetas[i]);
        }


        Toast.makeText(getActivity(),
                sb.toString(),
                Toast.LENGTH_LONG).show();
    }

    public void actualizarRespuesta(int index, String t) {
         switch(index){
             case 0:
                 respuesta1.setText(t);
                 break;

             case 1:
                 respuesta2.setText(t);
                 break;

             case 2:
                 respuesta3.setText(t);
                 break;

             case 3:
                 respuesta4.setText(t);
                 break;

             case 4:
                 respuesta5.setText(t);
                 break;
         }
    }
}
