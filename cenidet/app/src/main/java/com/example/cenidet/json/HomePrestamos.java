package com.example.cenidet.json;

public class HomePrestamos {

    private int id_salida;
    private String nombre_persona;
    private String rol;

    public HomePrestamos() {
    }

    public HomePrestamos(int id_salida, String nombre_persona, String rol) {
        this.id_salida = id_salida;
        this.nombre_persona = nombre_persona;
        this.rol = rol;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
