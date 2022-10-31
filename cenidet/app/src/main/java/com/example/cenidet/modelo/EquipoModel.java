package com.example.cenidet.modelo;

import android.widget.Toast;

import com.example.cenidet.activities.EquipoActivity;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.json.JSONSyE;
import com.example.cenidet.json.SalidaEquipos;
import com.example.cenidet.utils.valores;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipoModel {

    private EquipoAcciones equipo;

    public EquipoModel(EquipoAcciones equipo) {
        this.equipo = equipo;
    }

    public void crearSalida(String id_salida) {
        JSONSyE s = new JSONSyE(Integer.parseInt(id_salida), new ArrayList<SalidaEquipos>());
        for (Equipo e : valores.eList) {
            s.getEquipos().add(new SalidaEquipos(e.getNo_serie()));
        }

        Call<String> m = valores.s.salidaCreate(s);
        m.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                if (s.toLowerCase().equals("true")){
                    equipo.respuestaReguistrarSalida(true);
                } else{
                    equipo.respuestaReguistrarSalida(false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
