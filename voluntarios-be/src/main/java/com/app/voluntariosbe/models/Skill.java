package com.app.voluntariosbe.models;

public class Skill {
    private Integer id;
    private String descrip;

    //Constructor
    public Skill(Integer id, String descrip) {
        this.id = id;
        this.descrip = descrip;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public String getDescrip() {
        return descrip;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
