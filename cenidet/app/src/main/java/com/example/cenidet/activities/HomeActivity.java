package com.example.cenidet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cenidet.R;
import com.example.cenidet.adapters.AdapterPrincipal;
import com.example.cenidet.adapters.AdapterPrincipalEntradas;
import com.example.cenidet.databinding.ActivityHomeBinding;
import com.example.cenidet.json.Entradas;
import com.example.cenidet.models.ModelPrincipal;
import com.example.cenidet.presenter.HomePresenter;
import com.example.cenidet.utils.OnClickListenerPrincipal;
import com.example.cenidet.utils.OnClickListenerPrincipalEntradas;
import com.google.android.gms.vision.CameraSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnClickListenerPrincipal, BottomNavigationView.OnNavigationItemSelectedListener, OnClickListenerPrincipalEntradas, View.OnClickListener, com.example.cenidet.modelo.Home, TextWatcher {

    private ActivityHomeBinding binding;

    private List<ModelPrincipal> list;
    private List<ModelPrincipal> listE;

    private AdapterPrincipal mAdapter;
    private AdapterPrincipalEntradas mAdapterE;

    private boolean escanertf = false;

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        homePresenter = new HomePresenter(this, binding.cameraView, this, this);

        homePresenter.listaEntradas();
        homePresenter.listaSalidas();

        binding.buttonNavegation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        homePresenter.camaraEscaner();

        binding.SRSalida.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.listaSalidas();
                binding.SRSalida.setRefreshing(false);
            }
        });

        binding.SRSalida.setColorSchemeResources(R.color.colorAccent);

        binding.SREntrada.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.listaEntradas();
                binding.SREntrada.setRefreshing(false);
            }
        });

        binding.SREntrada.setColorSchemeResources(R.color.colorAccent);

        binding.buscar.setOnClickListener(this::onClick);

        binding.codigo.getEditText().addTextChangedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.listaEntradas();
        homePresenter.listaSalidas();
    }

    private void adaptador() {
        mAdapter = new AdapterPrincipal(list, this);
        binding.recycler.setAdapter(mAdapter);
    }

    private void adaptadorE() {
        mAdapterE = new AdapterPrincipalEntradas(listE, this);
        binding.recyclerEntradas.setAdapter(mAdapterE);
    }

    @Override
    public void onClick(ModelPrincipal model) {
        startIntent(model);
    }

    private void startIntent(ModelPrincipal model) {
        if (model.getnCase() != 4) {
            Intent intent = new Intent(this, EquipoActivity.class);
            intent.putExtra("id_salida", model.getCodigo());
            intent.putExtra("propietario", model.getId_propietario());
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, EstuActivity.class);
            intent.putExtra("id_salida", model.getCodigo());
            intent.putExtra("propietario", model.getId_propietario());
            intent.putExtra("alumnos", homePresenter.getPrestamos(model));
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salida:
                binding.SREntrada.setVisibility(View.INVISIBLE);

                binding.SRSalida.setVisibility(View.VISIBLE);

                elementosVisibles();

                escanertf = false;
                break;
            case R.id.entrada:
                binding.SREntrada.setVisibility(View.VISIBLE);
                binding.SRSalida.setVisibility(View.INVISIBLE);

                elementosInvisibles();

                escanertf = false;
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onClickEntradas(ModelPrincipal model) {
        homePresenter.obtenerEntrada("id_salida", Integer.parseInt(model.getCodigo()));
    }

    @Override
    public void onClick(View v) {
        buscarClick();

    }

    private void buscarClick() {
        String codigo = binding.codigo.getEditText().getText().toString();

        homePresenter.buscarTipoCodigo(codigo, list, listE);
    }

    private void elementosVisibles() {
        binding.txt1.setVisibility(View.VISIBLE);
        binding.txt2.setVisibility(View.VISIBLE);
        binding.codigo.setVisibility(View.VISIBLE);
        binding.linear.setVisibility(View.VISIBLE);

        binding.materialCardView.setVisibility(View.INVISIBLE);
    }

    private void elementosInvisibles() {
        binding.txt1.setVisibility(View.INVISIBLE);
        binding.txt2.setVisibility(View.INVISIBLE);
        binding.codigo.setVisibility(View.INVISIBLE);
        binding.linear.setVisibility(View.INVISIBLE);

        binding.materialCardView.setVisibility(View.VISIBLE);
    }

    public void intent1(String nombre, String id_salida) {
        int id = Integer.parseInt(id_salida);

        Intent i = new Intent(HomeActivity.this, EntradaActivity.class);
        i.putExtra("nombre", nombre);
        i.putExtra("id", id);

        startActivity(i);
    }

    @Override
    public void adaptador(List<ModelPrincipal> list, boolean tf) {
        if (tf) {
            this.listE = list;
            if (mAdapterE == null) {
                this.adaptadorE();
            } else {
                mAdapterE.eliminaTodo();
                for (ModelPrincipal m : this.listE) {
                    mAdapterE.add(m);
                }
            }

        } else {
            this.list = list;
            if (mAdapter == null) {
                this.adaptador();
            } else {
                mAdapter.eliminaTodo();
                for (ModelPrincipal m : this.list) {
                    mAdapter.add(m);
                }
            }
        }
    }

    @Override
    public void intentEntradas(String nombre, String id_salida) {
        homePresenter.obtenerEntrada(nombre, Integer.parseInt(id_salida));
    }

    @Override
    public void entrada(String codigo) {
        homePresenter.obtenerEntrada("id_salida", Integer.parseInt(codigo));
    }

    @Override
    public void salida(ModelPrincipal m) {
        startIntent(m);
    }

    @Override
    public void respuestaEntrada(Entradas entradas, boolean tf) {
        if (!tf) {
            Intent i = new Intent(HomeActivity.this, EntradaActivity.class);
            i.putExtra("entradas", entradas);
            startActivity(i);
        } else {
            Toast.makeText(this, "Salida no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mAdapter.filtrarLista(homePresenter.filtrar(list, s.toString()));
    }
}
