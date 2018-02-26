package com.daniel.sierra.videoclubretrofit.buscarPelicula;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daniel.sierra.videoclubretrofit.R;
import com.daniel.sierra.videoclubretrofit.modelos.Pelicula;
import com.daniel.sierra.videoclubretrofit.modelos.remote.APIService;
import com.daniel.sierra.videoclubretrofit.modelos.remote.ApiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniel on 23/02/2018.
 */

public class PeliculaGETBuscar extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.eb_Codigo)
    EditText buscarCodigo;
    @BindView(R.id.buttonBuscar)
    Button buscar;
    @BindView(R.id.tv_response_get)
    TextView mostrar;

    TextView tvTitulo, codigo, director, precio, stock, anio, descripcion;
    Button btnActualizar, btnBorrar;
    ImageView foto;

    int codigoC;
    private Pelicula p;
    private APIService mAPIService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_pelicula);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();
        buscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        codigoC = Integer.parseInt(buscarCodigo.getText().toString());
        enviarCodigo(codigoC);
    }

    public void enviarCodigo(int codigo){
        mAPIService.listarPelicula(codigo).enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful()){
                    motrarDetallePeli(response.body().get(0));
                    if (response.body().size() == 0)
                        Toast.makeText(getApplicationContext(),
                                "NO EXISTE LA PELICULA", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Log.e("UI senPost" , "error en el servicio");
                Toast.makeText(getApplicationContext(),
                        "ERROR EN EL SERVICIO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void motrarDetallePeli(Pelicula peli){
        if(peli != null){
            p = peli;
            setContentView(R.layout.detalle_pelicula);

            btnActualizar = findViewById(R.id.btn_actualizar);
            btnBorrar = findViewById(R.id.btn_borrar);

            tvTitulo = findViewById(R.id.detalle_titulo);
            codigo = findViewById(R.id.detalle_codigo);
            director = findViewById(R.id.detalle_director);
            precio = findViewById(R.id.detalle_precio);
            stock = findViewById(R.id.detalle_stock);
            anio = findViewById(R.id.detalle_anio);
            descripcion = findViewById(R.id.detalle_descripcion);
            foto = findViewById(R.id.detalle_imagen);

            tvTitulo.setText(String.valueOf(peli.getTitulo()));
            codigo.setText(String.valueOf(peli.getCodigo()));
            director.setText(String.valueOf(peli.getDirector()));
            precio.setText(String.valueOf(peli.getPrecio()));
            stock.setText(String.valueOf(peli.getStock()));
            anio.setText(String.valueOf(peli.getAnio()));
            descripcion.setText(String.valueOf(peli.getDescripcion()));
            Glide.with(getApplicationContext()).load(peli.getFoto()).into(foto);

            btnActualizar.setVisibility(View.GONE);
            btnBorrar.setVisibility(View.GONE);

        }
    }
}
