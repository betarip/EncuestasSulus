package com.sulus.encuestasapp.Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by betaripv on 06/03/18.
 */

public class Encuesta {

    public static Encuesta encuestaNueva;
    private String[] respuestas = new String[5];
    private String municipio;
    private String seccion;
    private String telfono;
    private String correo;
    private String nombre;
    private String app1;
    private String app2;
    private int identificador;
    private Date fechaCaptura;
    private Date fechaSubida;
    /*

    Obtener Geolocalizacion

     */
    private double lat, longi;

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

    public String getTelfono() {
        return telfono;
    }

    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp1() {
        return app1;
    }

    public void setApp1(String app1) {
        this.app1 = app1;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getApp2() {
        return app2;
    }

    public void setApp2(String app2) {
        this.app2 = app2;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }
}
