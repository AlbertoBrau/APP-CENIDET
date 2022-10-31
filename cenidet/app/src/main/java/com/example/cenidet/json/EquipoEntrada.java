package com.example.cenidet.json;

import java.io.Serializable;

public class EquipoEntrada implements Serializable {

    private int no_serie;
    private String nombre, foto, modelo;
    private boolean entregado = false;

    public EquipoEntrada(int no_serie, String nombre, String foto, String modelo) {
        this.no_serie = no_serie;
        this.nombre = nombre;
        this.foto = foto;
        this.modelo = modelo;
    }

    public EquipoEntrada() {
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNo_serie() {
        return no_serie;
    }

    public void setNo_serie(int no_serie) {
        this.no_serie = no_serie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }
}
