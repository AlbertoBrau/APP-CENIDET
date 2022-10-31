package com.example.cenidet.json;

public class HomeSalidas {

    private int id;
    private String propietario;
    private int id_propietario;
    private String sexo;

    public HomeSalidas() {

    }

    public HomeSalidas(int id, String propietario, int id_propietario, String sexo) {
        this.id = id;
        this.propietario = propietario;
        this.id_propietario = id_propietario;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}