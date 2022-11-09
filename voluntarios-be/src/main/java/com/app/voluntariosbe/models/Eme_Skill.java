package com.app.voluntariosbe.models;

public class Eme_Skill {
    private Integer id;
    private Integer id_emergencia;
    private Integer id_habilidad;

    //Constructor
    public Eme_Skill(Integer id, Integer id_emergencia, Integer id_habilidad) {
        this.id = id;
        this.id_emergencia = id_emergencia;
        this.id_habilidad = id_habilidad;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public Integer getId_emergencia() {
        return id_emergencia;
    }

    public Integer getId_habilidad() {
        return id_habilidad;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_emergencia(Integer id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public void setId_habilidad(Integer id_habilidad) {
        this.id_habilidad = id_habilidad;
    }
}
