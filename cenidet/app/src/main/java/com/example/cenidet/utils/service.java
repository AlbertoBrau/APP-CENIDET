package com.example.cenidet.utils;

import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.json.HomeJSON;
import com.example.cenidet.json.Login;
import com.example.cenidet.json.JSONSyE;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface service {


    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @POST("login")
    Call<Login> login(@Body Login json);

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @GET("salidas/activas")
    Call<HomeJSON> home();

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @GET("salidas/recolectadas")
    Call<HomeJSON> homeEntradas();

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @GET("equipo/{id}")
    Call<Equipo> equipo(@Path("id") int equipoId);

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @POST("salidas/create")
    Call<String> salidaCreate(@Body JSONSyE json);

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @POST("entrada/create")
    Call<String> entradaCreate(@Body JSONSyE json);

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @GET("equipos/no_serie/{id}")
    Call<Entradas> EquipoEntradaNS(@Path("id") int No_Serie);

    @Headers({
            "user: " + valores.user,
            "password: " + valores.pss
    })
    @GET("equipos/id_salida/{id}")
    Call<Entradas> EquipoEntradaIS(@Path("id") int idSalida);

}
