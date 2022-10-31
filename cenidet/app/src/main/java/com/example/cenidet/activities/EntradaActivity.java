package com.example.cenidet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cenidet.R;
import com.example.cenidet.adapters.AdapterEquipoEntrada;
import com.example.cenidet.databinding.ActivityEntradaBinding;
import com.example.cenidet.json.Entradas;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.json.EquipoEntrada;
import com.example.cenidet.json.JSONSyE;
import com.example.cenidet.json.SalidaEquipos;
import com.example.cenidet.modelo.EntradaAcciones;
import com.example.cenidet.modelo.LecturaCamara;
import com.example.cenidet.presenter.EntradaPresenter;
import com.example.cenidet.utils.valores;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradaActivity extends AppCompatActivity implements View.OnClickListener, EntradaAcciones, LecturaCamara {

    private ActivityEntradaBinding binding;

    private int id;
    private String nombre;

    private AdapterEquipoEntrada mAdapter;

    private Entradas entradas;

    private EntradaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntradaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        presenter = new EntradaPresenter(this, this, binding.cameraView, this, this);

        presenter.camaraEscaner();

        //presenter.listaEntradas(nombre, id);

        entradas = (Entradas) getIntent().getSerializableExtra("entradas");

        adaptador();

        binding.aceptar.setOnClickListener(this::onClick);
        binding.cancelar.setOnClickListener(this::onClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void adaptador(){
        mAdapter = new AdapterEquipoEntrada(entradas.getEquipos());
        binding.recycler.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aceptar:

                if (mAdapter.isSeleccionado()){
                    presenter.reguistrarEntrada(mAdapter.getmList(), entradas.getId_salida());
                }else{
                    Toast.makeText(EntradaActivity.this, "Es necesario registrar un equipo ",Toast.LENGTH_LONG).show();
                }

                onBackPressed();
                break;
            case R.id.cancelar:
                onBackPressed();
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();;
    }

    @Override
    public void respuestaCrearEntrada(String res) {
        if (res.toLowerCase().equals("true")){
            Toast.makeText(EntradaActivity.this, "Entrada registrada con éxito",Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(EntradaActivity.this, "No se pudo registrar entrada",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void escaner(Equipo equipo) {
        boolean tf = mAdapter.seleccionar(equipo.getNo_serie());

        if (tf != true){
            Toast.makeText(EntradaActivity.this, "Equipo no encontrado",Toast.LENGTH_LONG).show();
        }
    }
}