package com.sulus.encuestasapp.Vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sulus.encuestasapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RespuestasFragment extends Fragment {

    TextView respuesta1, respuesta2, respuesta3, respuesta4, respuesta5;
    TextView[] textViewArray = new TextView[5];

    public RespuestasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuestas, container, false);
        textViewArray[0] = (TextView) view.findViewById(R.id.respuesta1);
        textViewArray[1] = (TextView) view.findViewById(R.id.respuesta2);
        textViewArray[2] = (TextView) view.findViewById(R.id.respuesta3);
        textViewArray[3] = (TextView) view.findViewById(R.id.respuesta4);
        textViewArray[4] = (TextView) view.findViewById(R.id.respuesta5);

        String myTag = getTag();

        ((EncuestasView) getActivity()).setTabFragmentB(0, myTag);

        return view;
    }


    public void b_updateText(int index, String t) {
        textViewArray[index].setText(t);
    }
}
