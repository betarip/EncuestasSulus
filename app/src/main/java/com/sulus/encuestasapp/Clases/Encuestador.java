package com.sulus.encuestasapp.Clases;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by betaripv on 06/03/18.
 */

public class Encuestador {

    public static ArrayList<Encuesta> listaEncuestas;
    private String id;
    private String pass;
    private Date acceso;

    public Encuestador(String id, String pass, Date acceso) {
        this.id = id;
        this.pass = pass;
        this.acceso = acceso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public Date getAcceso() {
        return acceso;
    }

    public void setAcceso(Date acceso) {
        this.acceso = acceso;
    }
}
