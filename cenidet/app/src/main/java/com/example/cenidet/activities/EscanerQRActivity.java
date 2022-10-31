package com.example.cenidet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.cenidet.R;
import com.example.cenidet.adapters.AdapterEscaner;
import com.example.cenidet.databinding.ActivityEscanerQRBinding;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.modelo.LecturaCamara;
import com.example.cenidet.models.ModelJSON;
import com.example.cenidet.presenter.EscanerQRPresenter;
import com.example.cenidet.utils.DecodificarJSON;
import com.example.cenidet.utils.valores;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscanerQRActivity extends AppCompatActivity implements View.OnClickListener, LecturaCamara {

    private ActivityEscanerQRBinding binding;

    private Equipo equipo;

    private int id_propietario;

    private AdapterEscaner mAdapter;

    private List<ModelJSON> mList;

    private final DecodificarJSON d = new DecodificarJSON();

    private EscanerQRPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEscanerQRBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        presenter = new EscanerQRPresenter(this::escaner, binding.cameraView, this, this);

        getExtras();

        Picasso.get().load(R.drawable.no_image).into(binding.EquipoIV);

        mList = new ArrayList<ModelJSON>();

        adaptador();

        binding.agregar.setOnClickListener(this::onClick);
        binding.finalizar.setOnClickListener(this::onClick);

        presenter.camaraEscaner();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void adaptador() {
        mAdapter = new AdapterEscaner(mList);
        binding.recycler.setAdapter(mAdapter);
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            id_propietario = -1;
        } else {
            id_propietario = bundle.getInt("propietario");
        }
    }

    public void mostrarDatos() {

        if (id_propietario == equipo.getId_propietario()) {
            Picasso.get().load(equipo.getFoto()).error(R.drawable.no_image).into(binding.EquipoIV);

            binding.tvEquipo.setText("Equipo:" + equipo.getNombre());
            binding.tvMarca.setText("Modelo: " + equipo.getModelo());
            binding.tvNS.setText("Número de serie: " + equipo.getNo_serie());

            mAdapter.eliminaTodo();

            for (ModelJSON m : d.decodifica(equipo.getEtiquetas())){
                mAdapter.add(m);
            }

        } else {
            Toast.makeText(this, "Este equipo no pertenece al que solicito la salida", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.no_image).into(binding.EquipoIV);

            binding.tvEquipo.setText("Equipo: -------");
            binding.tvMarca.setText("Modelo: -------");
            binding.tvNS.setText("Número de serie: -------");
            mAdapter.eliminaTodo();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agregar:
                if (!valores.eList.contains(equipo) && validaElemento()){
                    valores.eList.add(equipo);
                }
                break;
            case R.id.finalizar:
                onBackPressed();
                break;
        }
    }

    public boolean validaElemento(){
        for (Equipo e : valores.eList){
            if (e.getNo_serie() == equipo.getNo_serie()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void escaner(Equipo equipo) {
        this.equipo = equipo;
        mostrarDatos();
    }

}