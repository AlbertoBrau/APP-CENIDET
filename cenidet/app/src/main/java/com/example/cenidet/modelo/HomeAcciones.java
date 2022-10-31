package com.example.cenidet.modelo;

import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.models.ModelPrincipal;

import java.util.List;

public interface HomeAcciones {

    void adaptador(List<ModelPrincipal> list, boolean tf);

    void tipoEntrada(String codigo);

    void tipoSalida(ModelPrincipal m);

    void respuestaEntrada(Entradas entradas, boolean tf);

}
