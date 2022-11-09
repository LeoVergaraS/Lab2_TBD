package com.app.voluntariosbe.models;

import java.util.Date;

public class Volunteer {
    private Integer id;
    private String nombre;
    private Date fnacimiento;

    //Constructor
    public Volunteer(Integer id, String nombre, Date fnacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fnacimiento = fnacimiento;
    }

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
}
