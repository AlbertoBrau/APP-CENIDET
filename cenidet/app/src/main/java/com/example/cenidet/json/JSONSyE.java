package com.example.cenidet.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JSONSyE {

    private int id_salida;
    private List<SalidaEquipos> equipos;

    public JSONSyE() {
    }

    public JSONSyE(int id_salida, List<SalidaEquipos> equipos) {
        this.id_salida = id_salida;
        this.equipos = equipos;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public List<SalidaEquipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<SalidaEquipos> equipos) {
        this.equipos = equipos;
    }

    public static SalidaEquipos parseJSONH(String response){
        Gson gson = new GsonBuilder().create();
        SalidaEquipos salida = gson.fromJson(response, SalidaEquipos.class);
        return salida;
    }
}
