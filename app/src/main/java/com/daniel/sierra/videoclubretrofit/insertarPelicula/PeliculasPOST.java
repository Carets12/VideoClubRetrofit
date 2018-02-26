package com.daniel.sierra.videoclubretrofit.insertarPelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class PeliculasPOST extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.e_Codigo)
    EditText codigo;
    @BindView(R.id.e_Foto)
    EditText foto;
    @BindView(R.id.e_Titulo)
    EditText titulo;
    @BindView(R.id.e_Director)
    EditText director;
    @BindView(R.id.e_Precio)
    EditText precio;
    @BindView(R.id.e_Anio)
    EditText anio;
    @BindView(R.id.e_Stock)
    EditText stock;
    @BindView(R.id.e_Descripcion)
    EditText descripcion;

    @BindView(R.id.tv_response)
    TextView mResponseTv;

    @BindView(R.id.b_CrearPelicula)
    Button crearPelicula;

    private APIService mAPIService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pelicula);
        ButterKnife.bind(this);

        crearPelicula.setOnClickListener(this);
        mAPIService = ApiUtils.getAPIService();
    }

    @Override
    public void onClick(View view) {
         if(!comprobarCampos()){
             Pelicula pelicula = new Pelicula(Integer.parseInt(codigo.getText().toString()),foto.getText().toString(),
                     titulo.getText().toString(),director.getText().toString(),Float.parseFloat(precio.getText().toString()),
                     Integer.parseInt(anio.getText().toString()),Integer.parseInt(stock.getText().toString()),descripcion.getText().toString());
             enviarPelicula(pelicula);
             Log.v("Errores",pelicula.toString());
         }
    }

    private void enviarPelicula(final Pelicula pelicula) {
        mAPIService.guardarPost(pelicula).enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful()){
                    Log.i("UI enviarPeli", "post submitted to API." + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Película creada correctamente", Toast.LENGTH_SHORT).show();
                    Intent intentListar = new Intent(getApplicationContext(), PeliculasGET.class);
                    startActivity(intentListar);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.e("UI senPost" , "error en el servicio");
                Toast.makeText(getApplicationContext(),
                        "ERROR EN EL SERVICIO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean comprobarCampos() {
        boolean aux = false;

        if (codigo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio CÓDIGO", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (titulo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio TÍTULO", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (precio.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Obligatorio PRECIO", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (stock.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio STOCK", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (anio.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio AÑO", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        return aux;
    }
}
