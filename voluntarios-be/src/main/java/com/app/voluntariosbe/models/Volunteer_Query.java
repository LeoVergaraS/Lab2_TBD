package com.app.voluntariosbe.models;

public class Volunteer_Query {
    private Integer id;
    private String nombre;
    private Integer flg_participa;

    //Constructor
/*     public Volunteer_Query(Integer id, String nombre, Integer flg_participa) {
        this.id = id;
        this.nombre = nombre;
        this.flg_participa = flg_participa;
    } */

    //Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getFlg_participa() {
        return flg_participa;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFlg_participa(Integer flg_participa) {
        this.flg_participa = flg_participa;
    }
}
