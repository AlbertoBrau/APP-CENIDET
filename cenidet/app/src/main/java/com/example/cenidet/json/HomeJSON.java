package com.example.cenidet.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class HomeJSON {

    private List<HomeSalidas> salidas;
    private List<HomePrestamos> prestamos;

    public HomeJSON() {
    }

    public HomeJSON(List<HomeSalidas> salidas, List<HomePrestamos> prestamos) {
        this.salidas = salidas;
        this.prestamos = prestamos;
    }

    public List<HomeSalidas> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<HomeSalidas> salidas) {
        this.salidas = salidas;
    }

    public List<HomePrestamos> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<HomePrestamos> prestamos) {
        this.prestamos = prestamos;
    }

    public static HomeSalidas parseJSONH(String response){
        Gson gson = new GsonBuilder().create();
        HomeSalidas salida = gson.fromJson(response, HomeSalidas.class);
        return salida;
    }

    public static HomePrestamos parseJSONP(String response){
        Gson gson = new GsonBuilder().create();
        HomePrestamos prestamos = gson.fromJson(response, HomePrestamos.class);
        return prestamos;
    }

}
