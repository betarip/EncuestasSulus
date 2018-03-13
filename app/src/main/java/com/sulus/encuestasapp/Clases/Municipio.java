package com.sulus.encuestasapp.Clases;

import android.content.Context;
import android.content.res.Resources;

import com.sulus.encuestasapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by betaripv on 11/03/18.
 */

public class Municipio {
    private Integer id;
    private String nombre;
    private ArrayList<Integer> secciones;

    private static ArrayList<Municipio> listaDeMunicipios;

    Municipio(Integer id, String nombre, ArrayList<Integer> secciones) {
        this.id = id;
        this.nombre = nombre;
        this.secciones = secciones;
    }

    Municipio() {
        this.id = 0;
        this.nombre = "";
        this.secciones = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getSecciones() {
        return secciones;
    }

    public void setSecciones(ArrayList<Integer> secciones) {
        this.secciones = secciones;
    }

    public void llenarListaDeMunicipios(Context current) {
        Resources res = current.getResources();
        InputStream is = res.openRawResource(R.raw.municipios);

        Scanner scanner = new Scanner(is);

        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        JSONObject reader = null;

        try {
            reader = new JSONObject(builder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
