package com.example.cenidet.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelPrincipal {

    private String codigo, nombre;
    private int imagen;
    private List<PricipalPrestamos> list;
    private int nCase;
    private int id_propietario;
    private String sexo;

    public ModelPrincipal(String codigo, String nombre, int id_propietario,String sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.id_propietario = id_propietario;
        this.sexo = sexo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public List<PricipalPrestamos> getList() {
        return list;
    }

    public void setList(List<PricipalPrestamos> list) {
        this.list = list;
    }

    public int getnCase() {
        return nCase;
    }

    public void setnCase(int nCase) {
        this.nCase = nCase;
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

    public void addPrestamo(PricipalPrestamos prestamos) {
        if (isNullList()) {
            list = new ArrayList<PricipalPrestamos>();
        }

        list.add(prestamos);
    }



    public int prestamosSize() {
        return list.size();
    }

    public boolean isNullList() {
        return (list == null) ? true : false;
    }
}
