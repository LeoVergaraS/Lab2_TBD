package com.app.voluntariosbe.models;

public class Task_Skill {
    private Integer id;
    private Integer id_emehab;
    private Integer id_tarea;

    //Constructor
    public Task_Skill(Integer id, Integer id_emehab, Integer id_tarea) {
        this.id = id;
        this.id_emehab = id_emehab;
        this.id_tarea = id_tarea;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public Integer getId_emehab() {
        return id_emehab;
    }

    public Integer getId_tarea() {
        return id_tarea;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_emehab(Integer id_emehab) {
        this.id_emehab = id_emehab;
    }

    public void setId_tarea(Integer id_tarea) {
        this.id_tarea = id_tarea;
    }
}
