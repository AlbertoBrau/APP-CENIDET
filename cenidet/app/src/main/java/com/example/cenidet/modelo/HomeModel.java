package com.example.cenidet.modelo;

import android.util.Log;

import com.example.cenidet.R;
import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.HomeJSON;
import com.example.cenidet.json.HomePrestamos;
import com.example.cenidet.json.HomeSalidas;
import com.example.cenidet.models.ModelPrincipal;
import com.example.cenidet.models.PricipalPrestamos;
import com.example.cenidet.utils.valores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements Serializable {

    private List<ModelPrincipal> list;
    private Call<HomeJSON> m;

    private Entradas entradas;

    private HomeAcciones home;

    public HomeModel(HomeAcciones home) {
        this.home = home;
    }

    public void getSalidas(boolean tf) {
        Call<HomeJSON> m = valores.s.home();
        obtenerLista(m, tf);
    }

    public void getEntradas(boolean tf) {
        Call<HomeJSON> m = valores.s.homeEntradas();
        obtenerLista(m, tf);
    }

    public void getTipoBusqueda (String codigo, List<ModelPrincipal> list,List<ModelPrincipal> listE){
        boolean tf = true;

        String tipo = "";

        for (ModelPrincipal m : listE) {
            if (codigo.equals(m.getCodigo())) {
                tf = false;
                home.tipoEntrada(codigo);
                break;
            }
        }

        if (tf) {
            for (ModelPrincipal m : list) {
                if (codigo.equals(m.getCodigo())) {
                    home.tipoSalida(m);
                    break;
                }
            }
        }
    }

    public String convertirPrestamos(ModelPrincipal model) {
        String aux = "";

        for (PricipalPrestamos p : model.getList()) {
            if (aux.equals("")) {
                aux = p.getNombre();
            } else {
                aux = "," + p.getNombre();
            }
        }

        return aux;
    }

    private void obtenerLista(Call<HomeJSON> m, boolean tf) {

        m.enqueue(new Callback<HomeJSON>() {
            @Override
            public void onResponse(Call<HomeJSON> call, Response<HomeJSON> response) {

                HomeJSON homeJSON = response.body();
                if (!homeJSON.getSalidas().isEmpty()) {
                    list = transformar(homeJSON);
                    home.adaptador(list, tf);
                }
            }

            @Override
            public void onFailure(Call<HomeJSON> call, Throwable t) {
                System.out.println("Error");
            }
        });
    }

    private List<ModelPrincipal> transformar(HomeJSON homeJSON) {
        List<ModelPrincipal> listAux = new ArrayList<ModelPrincipal>();

        for (HomeSalidas salidas : homeJSON.getSalidas()) {
            listAux.add(new ModelPrincipal(salidas.getId() + "", salidas.getPropietario(), salidas.getId_propietario(),salidas.getSexo()));
        }

        for (HomePrestamos prestamos : homeJSON.getPrestamos()) {
            int i = buscarSalida(prestamos.getId_salida(), listAux);
            if (i != -1) {
                listAux.get(i).addPrestamo(new PricipalPrestamos(prestamos.getNombre_persona(), prestamos.getRol()));
            }
        }

        for (int i = 0; i < listAux.size(); i++) {
            if (listAux.get(i).isNullList()) {
                this.asignarImagen_Case(i, 1, R.drawable.ic_primero, listAux);
            } else {
                if (listAux.get(i).prestamosSize() == 1) {
                    if (listAux.get(i).getList().get(0).getRol().equals("docente")) {
                        this.asignarImagen_Case(i, 2, R.drawable.ic_segundo, listAux);
                    } else {
                        this.asignarImagen_Case(i, 3, R.drawable.ic_tercero, listAux);
                    }
                } else {
                    this.asignarImagen_Case(i, 4, R.drawable.ic_cuarto, listAux);
                }
            }
        }
        return listAux;
    }

    private void asignarImagen_Case(int i, int nCase, int img, List<ModelPrincipal> listAux) {
        listAux.get(i).setnCase(nCase);
        listAux.get(i).setImagen(img);
    }

    private int buscarSalida(int id, List<ModelPrincipal> listAux) {
        int ind = -1;
        for (int i = 0; i < listAux.size(); i++) {
            if (listAux.get(i).getCodigo().equals(id + "")) {
                ind = i;
                break;
            }
        }
        return ind;
    }

    public void obtenerEntradaService(String nombre, int id){

        Call<Entradas> m =  null;

        if (nombre.equals("id_salida")){
            m = valores.s.EquipoEntradaIS(id);
        }else if (nombre.equals("equipo")){
            m = valores.s.EquipoEntradaNS(id);
        }

        m.enqueue(new Callback<Entradas>() {
            @Override
            public void onResponse(Call<Entradas> call, Response<Entradas> response) {
                entradas = response.body();
                if (entradas .getEquipos().size() == 0){
                    home.respuestaEntrada(entradas, true);
                }else{
                    home.respuestaEntrada(entradas, false);
                }

            }

            @Override
            public void onFailure(Call<Entradas> call, Throwable t) {
                Log.e("error", "Error en el servicio de entrada");
            }
        });
    }

    public List<ModelPrincipal> filtrarLista(List<ModelPrincipal> list, String txt){
        List<ModelPrincipal> Flist = new ArrayList<ModelPrincipal>();

        for (ModelPrincipal model : list) {

            if (model.getCodigo().contains(txt.toString())) {
                Flist.add(model);
            }

        }

        return Flist;
    }

}
