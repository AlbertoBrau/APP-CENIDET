package com.example.cenidet.presenter;

import android.content.Context;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.modelo.CamaraEscaner;
import com.example.cenidet.modelo.Home;
import com.example.cenidet.modelo.HomeModel;
import com.example.cenidet.modelo.HomeAcciones;
import com.example.cenidet.modelo.LecturaCamara;
import com.example.cenidet.models.ModelPrincipal;

import java.util.List;

public class HomePresenter implements HomeAcciones, LecturaCamara {

    private Home home;
    private HomeModel homeModel = new HomeModel(this);
    private CamaraEscaner camaraEscaner;

    public HomePresenter(Home home, SurfaceView surfaceView, Context context, AppCompatActivity appCompatActivity) {
        this.home = home;
        camaraEscaner = new CamaraEscaner(this::escaner, surfaceView, context, appCompatActivity);
    }

    public void listaSalidas() {
        homeModel.getSalidas(false);
    }

    public void listaEntradas() {
        homeModel.getEntradas(true);
    }

    public String getPrestamos(ModelPrincipal model) {
        return homeModel.convertirPrestamos(model);
    }

    public void camaraEscaner() {
        camaraEscaner.escaner();
    }

    public void abrirCamara() {
        camaraEscaner.iniciarCamara();
    }

    public void buscarTipoCodigo(String codigo, List<ModelPrincipal> list, List<ModelPrincipal> listE) {
        homeModel.getTipoBusqueda(codigo, list, listE);
    }

    public void cerrarCamara(){
        camaraEscaner.deterCamara();
    }

    public void obtenerEntrada (String nombre, int id){
        homeModel.obtenerEntradaService(nombre, id);
    }

    public List<ModelPrincipal> filtrar(List<ModelPrincipal> list, String txt){
        return homeModel.filtrarLista(list, txt);
    }

    @Override
    public void adaptador(List<ModelPrincipal> list, boolean tf) {
        home.adaptador(list, tf);
    }

    @Override
    public void tipoEntrada(String codigo) {
        home.entrada(codigo);
    }

    @Override
    public void tipoSalida(ModelPrincipal m) {
        home.salida(m);
    }

    @Override
    public void escaner(Equipo equipo) {
        home.intentEntradas("equipo", equipo.getNo_serie() + "");
    }

    @Override
    public void respuestaEntrada(Entradas entradas, boolean tf){
        home.respuestaEntrada(entradas, tf);
    }
}
