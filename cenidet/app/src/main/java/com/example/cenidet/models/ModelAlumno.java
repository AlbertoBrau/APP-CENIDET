package com.example.cenidet.models;

public class ModelAlumno {

    private String nombre;
    private boolean validar;

    public ModelAlumno(String nombre) {
        this.nombre = nombre;
        this.validar = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }
}
