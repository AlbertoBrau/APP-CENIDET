package com.example.cenidet.modelo;

import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.models.ModelPrincipal;

import java.util.List;

public interface Home {

    void adaptador(List<ModelPrincipal> list, boolean tf);

    void intentEntradas(String nombre, String id_salida);

    void entrada(String codigo);

    void salida(ModelPrincipal m);

    void respuestaEntrada(Entradas entradas, boolean tf);

}
