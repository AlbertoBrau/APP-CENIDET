package com.example.cenidet.modelo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cenidet.activities.HomeActivity;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.utils.valores;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cenidet.utils.valores.MY_PERMISSIONS_REQUEST_CAMERA;

public class CamaraEscaner{

    private LecturaCamara lecturaCamara;

    private CameraSource cameraSource;

    private String token = "";
    private String tokenanterior = "";

    private SurfaceView cameraView = null;

    private Context context;

    private AppCompatActivity appCompatActivity;

    public CamaraEscaner(LecturaCamara lecturaCamara,SurfaceView cameraView, Context context, AppCompatActivity appCompatActivity) {
        this.lecturaCamara = lecturaCamara;
        this.cameraView = cameraView;
        this.context = context;
        this.appCompatActivity = appCompatActivity;
    }

    public void escaner() {
        BarcodeDetector barcodeDetector = new BarcodeDetector
                .Builder(context)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource
                .Builder(context, barcodeDetector)
                .setAutoFocusEnabled(true)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (appCompatActivity.shouldShowRequestPermissionRationale(
                                Manifest.permission.CAMERA)) ;
                        appCompatActivity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                                valores.MY_PERMISSIONS_REQUEST_CAMERA);
                    }

                    return;
                }else{
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0) {

                    token = barcodes.valueAt(0).displayValue.toString();

                    if (!token.equals(tokenanterior)) {

                        tokenanterior = token;
                        Log.i("token", token);

                        if (token.charAt(0) == '{'){
                            convertidorJSON(token);
                        }else{
                            service(token);
                        }

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(5000);
                                        tokenanterior = "";
                                    }
                                } catch (InterruptedException e) {
                                    Log.e("Error", "Waiting didnt work!!");
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }
            }
        });
    }

    public void iniciarCamara(){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (appCompatActivity.shouldShowRequestPermissionRationale(
                        Manifest.permission.CAMERA)) ;
                appCompatActivity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        valores.MY_PERMISSIONS_REQUEST_CAMERA);
            }

            return;
        }else{
            try {
                cameraSource.start(cameraView.getHolder());
            } catch (IOException e) {
                Log.e("CAMERA SOURCE", e.getMessage());
            }
        }
    }

    public void deterCamara(){
        cameraSource.stop();
    }

    private void service(String token) {
        int id = Integer.parseInt(token);
        Call<Equipo> m = valores.s.equipo(id);

        m.enqueue(new Callback<Equipo>() {
            @Override
            public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                Equipo e = response.body();
                if (e != null) {
                    lecturaCamara.escaner(e);
                }
            }

            @Override
            public void onFailure(Call<Equipo> call, Throwable t) {

            }
        });
    }

    private void convertidorJSON(String token){
        Gson gson = new Gson();
        Equipo e = gson.fromJson(token, Equipo.class);
        lecturaCamara.escaner(e);
    }
}
