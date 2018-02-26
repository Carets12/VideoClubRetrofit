package com.daniel.sierra.videoclubretrofit.actualizarPelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.sierra.videoclubretrofit.R;
import com.daniel.sierra.videoclubretrofit.listarPelicula.PeliculasGET;
import com.daniel.sierra.videoclubretrofit.modelos.Pelicula;
import com.daniel.sierra.videoclubretrofit.modelos.remote.APIService;
import com.daniel.sierra.videoclubretrofit.modelos.remote.ApiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniel on 23/02/2018.
 */

public class PeliculaPUT extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_titulo)
    TextView t_Titulo;
    @BindView(R.id.Et_Codigo)
    TextInputLayout tvCodigo;
    @BindView(R.id.e_Foto)
    EditText tvFoto;
    @BindView(R.id.e_Titulo)
    EditText tvTitulo;
    @BindView(R.id.e_Director)
    EditText tvDirector;
    @BindView(R.id.e_Precio)
    EditText tvPrecio;
    @BindView(R.id.e_Anio)
    EditText tvAnio;
    @BindView(R.id.e_Stock)
    EditText tvStock;
    @BindView(R.id.e_Descripcion)
    EditText tvDescripcion;

    @BindView(R.id.b_CrearPelicula)
    Button btnActualizar;

    @BindView(R.id.tv_response)
    TextView mResponseTv;

    private APIService mAPIService;

    private String foto, titulo, director, precio, anio, stock , descripcion;

    private Pelicula pelicula;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pelicula);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();

        pelicula = (Pelicula) getIntent().getSerializableExtra("PELI");

        btnActualizar.setText("Actualizar");
        btnActualizar.setOnClickListener(this);

        tvCodigo.setVisibility(View.GONE);
        t_Titulo.setText("Actualizar Pelicula");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_CrearPelicula:
                comprobarCampos();
                actualizarPelicula(new Pelicula(pelicula.getCodigo(), foto, titulo, director, Float.parseFloat(precio), Integer.parseInt(anio), Integer.parseInt(stock), descripcion));
                startActivity(new Intent(getApplicationContext(), PeliculasGET.class));
                finish();
        }
    }

    public void actualizarPelicula(Pelicula pelicula) {
        mAPIService.modificarPelicula(pelicula.getCodigo(), pelicula).enqueue(new Callback<Pelicula>(){
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Toast.makeText(getApplicationContext(), "Pelicula actualizada correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void comprobarCampos() {

        if (tvFoto.getText().toString().isEmpty()) {
            foto = pelicula.getFoto();
        } else {
            foto = tvFoto.getText().toString();
        }

        if (tvTitulo.getText().toString().isEmpty()) {
            titulo = pelicula.getTitulo();
        } else {
            titulo = tvTitulo.getText().toString();
        }

        if (tvDirector.getText().toString().isEmpty()) {
            director = pelicula.getDirector();
        } else {
            director = tvDirector.getText().toString();
        }

        if (tvPrecio.getText().toString().isEmpty()) {
            precio = String.valueOf(pelicula.getPrecio());
        } else {
            precio = tvPrecio.getText().toString();
        }

        if (tvAnio.getText().toString().isEmpty()) {
            anio = String.valueOf(pelicula.getAnio());
        } else {
            anio = tvAnio.getText().toString();
        }

        if (tvStock.getText().toString().isEmpty()) {
            stock = String.valueOf(pelicula.getStock());
        } else {
            stock = tvStock.getText().toString();
        }

        if (tvDescripcion.getText().toString().isEmpty()) {
            descripcion = pelicula.getDescripcion();
        } else {
            descripcion = tvDescripcion.getText().toString();
        }
    }
}
