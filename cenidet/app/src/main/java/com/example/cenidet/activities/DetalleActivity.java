package com.example.cenidet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cenidet.R;
import com.example.cenidet.adapters.AdapterEscaner;
import com.example.cenidet.databinding.ActivityDetalleBinding;
import com.example.cenidet.databinding.ActivityEscanerQRBinding;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.models.ModelJSON;
import com.example.cenidet.utils.DecodificarJSON;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    private ActivityDetalleBinding binding;

    private AdapterEscaner mAdapter;

    private List<ModelJSON> mList;

    private Equipo equipo;

    private final DecodificarJSON d = new DecodificarJSON();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        equipo = (Equipo) getIntent().getSerializableExtra("equipo");

        Picasso.get().load(equipo.getFoto()).into(binding.imgPhoto);

        binding.tvEquipo.setText("Equipo:" + equipo.getNombre());

        binding.tvEquipo.setText("Equipo:" + equipo.getNombre());
        binding.tvMarca.setText("Modelo: " + equipo.getModelo());
        binding.tvNS.setText("Número de serie: " + equipo.getNo_serie());
        mList = new ArrayList<ModelJSON>();
        adaptador();

        for (ModelJSON m : d.decodifica(equipo.getEtiquetas())){
            mAdapter.add(m);
        }
    }

    private void adaptador() {
        mAdapter = new AdapterEscaner(mList);
        binding.recycler.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();;
    }
}