package com.daniel.sierra.videoclubretrofit.detallePelicula;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daniel.sierra.videoclubretrofit.R;
import com.daniel.sierra.videoclubretrofit.actualizarPelicula.PeliculaPUT;
import com.daniel.sierra.videoclubretrofit.listarPelicula.PeliculasGET;
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

public class PeliculaDetalle extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.detalle_titulo)
    TextView tvTitulo;
    @BindView(R.id.detalle_codigo)
    TextView codigo;
    @BindView(R.id.detalle_director)
    TextView director;
    @BindView(R.id.detalle_precio)
    TextView precio;
    @BindView(R.id.detalle_stock)
    TextView stock;
    @BindView(R.id.detalle_anio)
    TextView anio;
    @BindView(R.id.detalle_descripcion)
    TextView descripcion;
    @BindView(R.id.detalle_imagen)
    ImageView foto;

    @BindView(R.id.btn_borrar)
    Button borrar;
    @BindView(R.id.btn_actualizar)
    Button actualizar;

    private APIService mAPIService;
    private int codigoPeli;
    private Pelicula p;
    public static final String CODIGO = "CODIGO";
    private static final String PELI = "PELI";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_pelicula);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();

        borrar.setOnClickListener(this);
        actualizar.setOnClickListener(this);

        codigoPeli = getIntent().getExtras().getInt(CODIGO);
        enviarCodigo(codigoPeli);

        Log.v("Error"," "+codigoPeli);
//        Log.v("Error", p.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_borrar:
                AlertDialog.Builder alerta = new AlertDialog.Builder(this); // Necesita el contexto de la activity no vale el de AppContexto
                alerta.setTitle("Borrar.")
                        .setMessage("¿Está seguro de borrar la película?")
                        .setCancelable(true)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrarPelicula(codigoPeli);
                                startActivity(new Intent(getApplicationContext(), PeliculasGET.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Borrado cancelado.", Toast.LENGTH_SHORT).show();
                            }
                        });
                alerta.show();
                break;

            case R.id.btn_actualizar:
                Intent intent = new Intent(getApplicationContext(),PeliculaPUT.class);
                intent.putExtra(PELI, p);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void mostrarDetallePelicula(Pelicula peli){
        if(peli != null){
            p = peli;
            tvTitulo.setText(String.valueOf(peli.getTitulo()));
            codigo.setText(String.valueOf(peli.getCodigo()));
            director.setText(String.valueOf(peli.getDirector()));
            precio.setText(String.valueOf(peli.getPrecio()));
            stock.setText(String.valueOf(peli.getStock()));
            anio.setText(String.valueOf(peli.getAnio()));
            descripcion.setText(String.valueOf(peli.getDescripcion()));
            Glide.with(this).load(peli.getFoto()).into(foto);

        }
    }

    public void enviarCodigo(int codigo){
        mAPIService.listarPelicula(codigo).enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful()){
                  mostrarDetallePelicula(response.body().get(0));
                    if (response.body().size() == 0){
                        Toast.makeText(getApplicationContext(),
                                "NO EXISTE LA PELICULA", Toast.LENGTH_SHORT).show();
                    }
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

    public void borrarPelicula(int codigo) {
        mAPIService.borrarPelicula(codigo).enqueue(new Callback<Pelicula>() {

            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Toast.makeText(getApplicationContext(), "Borrado correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
