package com.example.cenidet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cenidet.R;
import com.example.cenidet.adapters.AdapterEquipo;
import com.example.cenidet.databinding.ActivityEquipoBinding;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.json.JSONSyE;
import com.example.cenidet.json.SalidaEquipos;
import com.example.cenidet.modelo.EquipoAcciones;
import com.example.cenidet.presenter.EquipoPresenter;
import com.example.cenidet.utils.valores;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipoActivity extends AppCompatActivity implements View.OnClickListener, EquipoAcciones {

    private ActivityEquipoBinding binding;

    private AdapterEquipo mAdapter;

    private int count = 0;
    private String id_salida;
    private int id_propietario;

    private EquipoPresenter equipoPresenter = new EquipoPresenter(this::respuestaReguistrarSalida);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        valores.eList = new ArrayList<Equipo>();

        adaptador();


        binding.fabCQR.setOnClickListener(this::onClick);
        binding.ivAceptar.setOnClickListener(this::onClick);
        binding.ivCancelar.setOnClickListener(this::onClick);

        getExtras();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.agregarValores();
    }

    private void adaptador() {
        mAdapter = new AdapterEquipo(EquipoActivity.this);
        binding.recycler.setAdapter(mAdapter);
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            id_salida = "";
            id_propietario = -1;
        } else {
            id_salida = bundle.getString("id_salida");
            id_propietario = bundle.getInt("propietario");
        }
    }

    public void agregarValores() {

        if (valores.eList.size() > count) {
            for (int i = count; i < valores.eList.size(); i++) {
                mAdapter.add(valores.eList.get(i));
            }
            count = valores.eList.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabCQR:
                Intent intent = new Intent(EquipoActivity.this, EscanerQRActivity.class);
                intent.putExtra("propietario", id_propietario);
                startActivity(intent);
                break;
            case R.id.ivAceptar:

                if (valores.eList.size() == 0){
                    Toast.makeText(EquipoActivity.this, "Debes agregar equipos antes de registrar la salida",Toast.LENGTH_LONG).show();
                }else{
                    equipoPresenter.reguistrarSalida(id_salida);
                }

                break;
            case R.id.ivCancelar:
                valores.eList = null;
                onBackPressed();
                break;
        }
    }

    public void intentDetalles(Equipo equipo){
        Intent intent = new Intent(EquipoActivity.this, DetalleActivity.class);
    }

    @Override
    public void respuestaReguistrarSalida(boolean tf) {
        if (tf){
            Toast.makeText(EquipoActivity.this, "Salida registrada con éxito",Toast.LENGTH_LONG).show();
            valores.eList = null;
            onBackPressed();
        } else{
            Toast.makeText(EquipoActivity.this, "No se pudo registrar salida",Toast.LENGTH_LONG).show();
        }
    }
}