package com.example.cenidet.utils;

import android.app.Application;

import com.example.cenidet.json.Equipo;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class valores extends Application {

    public static String correo;
    public static List<Equipo> eList;

    public static final String user = "scese.cenidet@gmail.com";
    public static  final String pss = "scese.cenidet";

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://11c4-2806-104e-1b-9217-f136-21a6-dada-b404.ngrok.io/cenidet/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static service s = retrofit.create(service.class);

}
