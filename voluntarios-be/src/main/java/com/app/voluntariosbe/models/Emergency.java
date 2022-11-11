package com.app.voluntariosbe.models;

import java.util.Date;

public class Emergency {
    private Integer id;
    private String nombre;
    private String descrip;
    private Date finicio;
    private Date ffin;
    private Integer id_institucion;
    private double longitud;
    private double latitud;
    private Integer estado;

    //Constructor
    public Emergency(Integer id, String nombre, String descrip, Date finicio, Date ffin, Integer id_institucion, double longitud, double latitud, Integer estado) {
        this.id = id;
        this.nombre = nombre;
        this.descrip = descrip;
        this.finicio = finicio;
        this.ffin = ffin;
        this.id_institucion = id_institucion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.estado = estado;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public Date getFinicio() {
        return finicio;
    }

    public Date getFfin() {
        return ffin;
    }

    public Integer getId_institucion() {
        return id_institucion;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
    public Integer getEstado() {
        return estado;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    public void setId_institucion(Integer id_institucion) {
        this.id_institucion = id_institucion;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
