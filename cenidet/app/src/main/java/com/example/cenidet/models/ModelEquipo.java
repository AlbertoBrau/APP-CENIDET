package com.example.cenidet.models;

import android.graphics.Bitmap;

public class ModelEquipo {

    private String equipo, marca, noS;

    public ModelEquipo(String equipo, String marca, String noS) {
        this.equipo = equipo;
        this.marca = marca;
        this.noS = noS;
    }

    public ModelEquipo() {
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNoS() {
        return noS;
    }

    public void setNoS(String noS) {
        this.noS = noS;
    }
}
