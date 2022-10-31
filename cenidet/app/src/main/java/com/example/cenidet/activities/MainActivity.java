package com.example.cenidet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.cenidet.R;
import com.example.cenidet.databinding.ActivityMainBinding;
import com.example.cenidet.json.Login;
import com.example.cenidet.utils.valores;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Picasso.get().load(R.drawable.logo_cenidet).into(binding.cenidet);
        Picasso.get().load(R.drawable.logo_tecnm).into(binding.tecnm);

        binding.entrar.setOnClickListener(this);

        intentActivity();
    }

    @Override
    public void onClick(View v) {
        Call<Login> m = valores.s.login(new Login(binding.usuario.getEditText().getText().toString(), binding.pss.getEditText().getText().toString()));

        m.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null){
                    Login login = response.body();
                    System.out.println(response.body());
                    if (!login.getEmail().isEmpty()){
                        intentActivity();
                    }else{
                        Toast.makeText(MainActivity.this, "Usuario o contraseña no valido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña no valido", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void intentActivity (){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}