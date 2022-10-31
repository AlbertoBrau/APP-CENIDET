package com.example.cenidet.json;

import java.io.Serializable;

public class Equipo implements Serializable {

    private int id, no_serie, id_propietario;
    private String nombre, modelo, etiquetas, foto;

    public Equipo() {
    }

    public Equipo(int id, int no_serie, int id_propietario, String nombre, String modelo, String etiquetas, String foto) {
        this.id = id;
        this.no_serie = no_serie;
        this.id_propietario = id_propietario;
        this.nombre = nombre;
        this.modelo = modelo;
        this.etiquetas = etiquetas;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_serie() {
        return no_serie;
    }

    public void setNo_serie(int no_serie) {
        this.no_serie = no_serie;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
