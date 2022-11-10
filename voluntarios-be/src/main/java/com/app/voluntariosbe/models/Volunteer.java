package com.app.voluntariosbe.models;

import java.util.Date;
import org.postgis.PGgeometry;

public class Volunteer {
    private Integer id;
    private String nombre;
    private Date fnacimiento;
    private PGgeometry geom;

    //Constructor
    public Volunteer(Integer id, String nombre, Date fnacimiento, PGgeometry geom) {
        this.id = id;
        this.nombre = nombre;
        this.fnacimiento = fnacimiento;
        this.geom = geom;
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

    public PGgeometry getgeom() {
        return geom;
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

    public void setgeom(PGgeometry geom) {
        this.geom = geom;
    }
}
