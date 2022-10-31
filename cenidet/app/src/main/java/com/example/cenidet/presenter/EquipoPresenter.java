package com.example.cenidet.presenter;

import com.example.cenidet.modelo.EquipoAcciones;
import com.example.cenidet.modelo.EquipoModel;

public class EquipoPresenter implements EquipoAcciones {

    private EquipoModel equipoModel = new EquipoModel(this::respuestaReguistrarSalida);
    private EquipoAcciones equipo;

    public EquipoPresenter(EquipoAcciones equipo) {
        this.equipo = equipo;
    }

    public void reguistrarSalida(String id_salida){
        equipoModel.crearSalida(id_salida);
    }

    @Override
    public void respuestaReguistrarSalida(boolean tf) {
        equipo.respuestaReguistrarSalida(tf);
    }
}
