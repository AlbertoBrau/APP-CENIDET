package com.example.cenidet.modelo;

import android.util.Log;
import android.widget.Toast;

import com.example.cenidet.activities.EntradaActivity;
import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.EquipoEntrada;
import com.example.cenidet.json.JSONSyE;
import com.example.cenidet.json.SalidaEquipos;
import com.example.cenidet.utils.valores;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradaModel {

    private EntradaAcciones acciones;
    private Entradas entradas;

    public EntradaModel(EntradaAcciones acciones) {
        this.acciones = acciones;
    }

    public void crearEntrada(List<EquipoEntrada> list, int id_salida) {
        JSONSyE s = new JSONSyE(id_salida, new ArrayList<SalidaEquipos>());
        for (EquipoEntrada e : list) {
            if (e.isEntregado()){
                s.getEquipos().add(new SalidaEquipos(e.getNo_serie()));
            }
        }

        Call<String> m = valores.s.entradaCreate(s);
        m.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                if (s.toLowerCase().equals("true")){
                    acciones.respuestaCrearEntrada("true");
                } else{
                    acciones.respuestaCrearEntrada("false");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
