package com.example.cenidet.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;

public class Entradas implements Serializable {

    private int id_salida;
    private List<EquipoEntrada> equipos;

    public Entradas(int id_salida, List<EquipoEntrada> equipos) {
        this.id_salida = id_salida;
        this.equipos = equipos;
    }

    public Entradas() {
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public List<EquipoEntrada> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoEntrada> equipos) {
        this.equipos = equipos;
    }

    public static EquipoEntrada parseJSON (String response){
        Gson gson = new GsonBuilder().create();
        EquipoEntrada equipo = gson.fromJson(response, EquipoEntrada.class);
        return equipo;
    }
}
