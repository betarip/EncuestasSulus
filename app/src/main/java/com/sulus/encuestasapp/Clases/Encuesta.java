package com.sulus.encuestasapp.Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by betaripv on 06/03/18.
 */

public class Encuesta {
    private String[] respuestas = new String[5];
    private String municipio;
    private String seccion;
    private String curp;
    private String nombre;
    private Date fechaCaptura;
    private Date fechaSubida;

    public static Encuesta encuestaNueva;
    /*

    Obtener Geolocalizacion

     */

    public Encuesta(){

    }

    public static Encuesta getEncuestaNueva() {
        return encuestaNueva;
    }

    public static void setEncuestaNueva(Encuesta nuevaEncuesta){encuestaNueva = nuevaEncuesta;}

    public static void cleanEncuestaNueva() {
        encuestaNueva = null;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
