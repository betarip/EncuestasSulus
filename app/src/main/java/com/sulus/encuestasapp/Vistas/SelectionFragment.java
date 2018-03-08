package com.sulus.encuestasapp.Vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sulus.encuestasapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pregunta";
    private static final String ARG_PARAM2 = "indice";

    Button A_enter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Integer mParam2;

    String respuesta;

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
        A_enter = (Button) myFragmentView.findViewById(R.id.siguiente);
        A_enter.setOnClickListener(A_enterOnClickListener);
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
            }
        });

        return myFragmentView;

    }

    View.OnClickListener A_enterOnClickListener
            = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {

            String textPassToB = respuesta;

            String TabOfFragmentB = ((EncuestasView) getActivity()).getTabFragmentB(mParam2);

            RespuestasFragment fragmentB = (RespuestasFragment) getActivity()
                    .getSupportFragmentManager()
                    .findFragmentByTag(TabOfFragmentB);

            fragmentB.b_updateText(mParam2, respuesta);

            Toast.makeText(getActivity(),
                    "text sent to Fragment B:\n " + TabOfFragmentB,
                    Toast.LENGTH_LONG).show();
            ((EncuestasView) getActivity()).siguientePregunta();
        }
    };


}
