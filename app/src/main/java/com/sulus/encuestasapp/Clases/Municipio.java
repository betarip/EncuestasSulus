package com.sulus.encuestasapp.Clases;

import android.content.Context;

import com.sulus.encuestasapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ivan on 12/03/2018.
 */

public class Municipio {

    private int id ;
    private String nombre;
    private ArrayList<Integer> secciones;

    public static ArrayList<Municipio> municipios;

    static {
        municipios = new ArrayList<>();


    }

    public Municipio(int id, String nombre, ArrayList<Integer> secciones) {
        this.id = id;
        this.nombre = nombre;
        this.secciones = secciones;
    }

    public Municipio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public static ArrayList<Municipio> getMunicipios() {
        return municipios;
    }

    public static void obtenerMunicipios(Context contexto){
        InputStream inputStream= contexto.getResources().openRawResource(R.raw.ejemplomunicipio);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            line = reader.readLine();
            sb.append(line);
            while (line != null) {
                line = reader.readLine();
                sb.append(line);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonMunicipios = jsonObject.getJSONArray("municipios");
            Municipio mun = new Municipio();
            for(int i=0; i<jsonMunicipios.length(); i++){
                JSONObject jsonMun = jsonMunicipios.getJSONObject(i);
                mun.setId(jsonMun.getInt("identificador"));
                mun.setNombre(jsonMun.getString("municipio"));
                JSONArray jsonSecciones = jsonMun.getJSONArray("secciones");
                ArrayList<Integer> listSecciones = new ArrayList<>();
                for (int j = 0 ; j < jsonSecciones.length(); j++){
                    listSecciones.set(j,jsonSecciones.getInt(j));
                }
                mun.setSecciones(listSecciones);
                municipios.add(mun);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public static void setMunicipios(ArrayList<Municipio> municipios) {
        Municipio.municipios = municipios;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
