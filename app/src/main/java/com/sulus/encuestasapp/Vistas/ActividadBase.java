package com.sulus.encuestasapp.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by betaripv on 06/03/18.
 */

public class ActividadBase extends AppCompatActivity {

    public static final String TAG = ActividadBase.class.getSimpleName();

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
