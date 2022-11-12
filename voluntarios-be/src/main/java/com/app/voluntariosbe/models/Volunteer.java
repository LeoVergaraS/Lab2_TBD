package com.app.voluntariosbe.models;

import java.util.Date;

public class Volunteer {
    private Integer id;
    private String nombre;
    private Date fnacimiento;
    private String rut;
    private double longitud;
    private double latitud;

    //Constructor
/*     public Volunteer(Integer id, String nombre, Date fnacimiento, double longitud, double latitud) {
        this.id = id;
        this.nombre = nombre;
        this.fnacimiento = fnacimiento;
        this.longitud = longitud;
        this.latitud = latitud;
    } */

    //Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getRut() {
        return rut;
    }
    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
