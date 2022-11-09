package com.app.voluntariosbe.models;

public class Ranking {
    private Integer id;
    private Integer id_voluntario;
    private Integer id_tarea;
    private Integer puntaje;
    private Integer flg_invitado;
    private Integer flg_participa;

    //Constructor
    public Ranking(Integer id, Integer id_voluntario, Integer id_tarea, Integer puntaje, Integer flg_invitado, Integer flg_participa) {
        this.id = id;
        this.id_voluntario = id_voluntario;
        this.id_tarea = id_tarea;
        this.puntaje = puntaje;
        this.flg_invitado = flg_invitado;
        this.flg_participa = flg_participa;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public Integer getId_voluntario() {
        return id_voluntario;
    }

    public Integer getId_tarea() {
        return id_tarea;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public Integer getFlg_invitado() {
        return flg_invitado;
    }

    public Integer getFlg_participa() {
        return flg_participa;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_voluntario(Integer id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public void setId_tarea(Integer id_tarea) {
        this.id_tarea = id_tarea;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public void setFlg_invitado(Integer flg_invitado) {
        this.flg_invitado = flg_invitado;
    }

    public void setFlg_participa(Integer flg_participa) {
        this.flg_participa = flg_participa;
    }
}
