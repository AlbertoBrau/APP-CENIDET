package com.example.cenidet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cenidet.R;
import com.example.cenidet.adapters.AdapterAlumno;
import com.example.cenidet.adapters.AdapterPrincipal;
import com.example.cenidet.databinding.ActivityEstuBinding;
import com.example.cenidet.models.ModelAlumno;

import java.util.ArrayList;
import java.util.List;

public class EstuActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEstuBinding binding;
    private String id_salida, prestamos;
    private int id_propietario;

    private List<ModelAlumno> list;
    private AdapterAlumno mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityEstuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.getExtras();
        this.crearList();
        this.adaptador();

        binding.ivCancelar.setOnClickListener(this::onClick);
        binding.ivAceptar.setOnClickListener(this::onClick);
    }

    private void getExtras (){
        Bundle bundle = getIntent().getExtras();
        if (bundle ==  null){
            id_salida = "";
            id_propietario = -1;
            prestamos =  "";
        }else{
            id_salida = bundle.getString("id_salida");
            id_propietario = bundle.getInt("propietario");
            prestamos = bundle.getString("alumnos");
        }
    }

    private void adaptador(){
        mAdapter = new AdapterAlumno(list);
        binding.recycler.setAdapter(mAdapter);
    }

    private void crearList(){
        list = new ArrayList<ModelAlumno>();
        String arr[] = prestamos.split(",");
        for (String s: arr){
            list.add(new ModelAlumno(s));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivCancelar:
                onBackPressed();
                break;
            case R.id.ivAceptar:
                this.validar();
                break;
        }
    }

    private void validar(){
        if (mAdapter.isFullList()){
            Intent intent = new Intent(this, EquipoActivity.class);
            intent.putExtra("id_salida", id_salida);
            intent.putExtra("propietario", id_propietario);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Todos los alumnos deben ser validados", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();;
    }
}