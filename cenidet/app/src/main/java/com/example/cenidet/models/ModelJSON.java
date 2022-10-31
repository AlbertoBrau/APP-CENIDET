package com.example.cenidet.models;

public class ModelJSON {

    private String titulo, valor ;

    public ModelJSON() {
    }

    public ModelJSON(String titulo, String valor) {
        this.titulo = titulo;
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
