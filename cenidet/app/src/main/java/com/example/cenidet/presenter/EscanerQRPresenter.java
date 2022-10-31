package com.example.cenidet.presenter;

import android.content.Context;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cenidet.json.Equipo;
import com.example.cenidet.modelo.CamaraEscaner;
import com.example.cenidet.modelo.LecturaCamara;

public class EscanerQRPresenter implements LecturaCamara {

    private CamaraEscaner camaraEscaner;
    private LecturaCamara lecturaCamara;

    public EscanerQRPresenter(LecturaCamara lecturaCamara, SurfaceView surfaceView, Context context, AppCompatActivity appCompatActivity) {
        this.lecturaCamara = lecturaCamara;
        camaraEscaner = new CamaraEscaner(this::escaner, surfaceView, context, appCompatActivity);
    }

    public void camaraEscaner() {
        camaraEscaner.escaner();
    }

    public void abrirCamara() {
        camaraEscaner.iniciarCamara();
    }

    public void cerrarCamara(){
        camaraEscaner.deterCamara();
    }

    @Override
    public void escaner(Equipo equipo) {
        lecturaCamara.escaner(equipo);
    }
}
