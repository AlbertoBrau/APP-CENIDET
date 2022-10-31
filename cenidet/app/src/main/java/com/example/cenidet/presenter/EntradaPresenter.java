package com.example.cenidet.presenter;

import android.content.Context;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.json.EquipoEntrada;
import com.example.cenidet.modelo.CamaraEscaner;
import com.example.cenidet.modelo.EntradaAcciones;
import com.example.cenidet.modelo.EntradaModel;
import com.example.cenidet.modelo.LecturaCamara;

import java.util.List;

public class EntradaPresenter implements EntradaAcciones, LecturaCamara {

    private EntradaAcciones acciones;
    private CamaraEscaner camaraEscaner;
    private EntradaModel entradaModel;
    private LecturaCamara lecturaCamara;

    public EntradaPresenter(EntradaAcciones acciones, LecturaCamara lecturaCamara, SurfaceView surfaceView, Context context, AppCompatActivity appCompatActivity) {
        this.acciones = acciones;
        this.lecturaCamara = lecturaCamara;
        entradaModel = new EntradaModel(this);
        camaraEscaner = new CamaraEscaner(this::escaner, surfaceView, context, appCompatActivity);
    }

    public void reguistrarEntrada (List<EquipoEntrada> list, int id_salida){
        entradaModel.crearEntrada(list, id_salida);
    }

    public void camaraEscaner() {
        camaraEscaner.escaner();
    }

    public void abrirCamara() {
        camaraEscaner.iniciarCamara();
    }

    @Override
    public void respuestaCrearEntrada(String res) {
        acciones.respuestaCrearEntrada(res);
    }

    @Override
    public void escaner(Equipo equipo) {
        lecturaCamara.escaner(equipo);
    }
}
