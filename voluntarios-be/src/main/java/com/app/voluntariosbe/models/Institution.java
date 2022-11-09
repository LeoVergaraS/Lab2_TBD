package com.app.voluntariosbe.models;

public class Institution {
    private Integer id;
    private String nombre;
    private String descrip;

    //Constructor
    public Institution(Integer id, String nombre, String descrip) {
        this.id = id;
        this.nombre = nombre;
        this.descrip = descrip;
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
}
