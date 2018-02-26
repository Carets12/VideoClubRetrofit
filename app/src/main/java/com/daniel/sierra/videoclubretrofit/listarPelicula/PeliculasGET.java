package com.daniel.sierra.videoclubretrofit.listarPelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daniel.sierra.videoclubretrofit.R;
import com.daniel.sierra.videoclubretrofit.detallePelicula.PeliculaDetalle;
import com.daniel.sierra.videoclubretrofit.modelos.Pelicula;
import com.daniel.sierra.videoclubretrofit.modelos.remote.APIService;
import com.daniel.sierra.videoclubretrofit.modelos.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniel on 23/02/2018.
 */

public class PeliculasGET extends AppCompatActivity{

    @BindView(R.id.lv_peliculas)
    ListView listViewPeliculas;

    private PeliculasAdapter peliculasAdapter;
    private APIService mAPIService;

    public static List<Pelicula> peliculas;
    public static final String CODIGO = "CODIGO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_peliculas);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();

        insertarAdaptador();
        cargarPeliculas();

    }

    private void insertarAdaptador() {
        //Crear adaptador
        peliculasAdapter = new PeliculasAdapter(getApplicationContext(), new ArrayList<Pelicula>());
        listViewPeliculas.setAdapter(peliculasAdapter);
        //Evento click en item de la lista
        listViewPeliculas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se lanza la Activity de detalles de la pelicula
                Log.e("Errores", "Creando itent");
                Intent intent = new Intent(getBaseContext(), PeliculaDetalle.class);
                intent.putExtra(CODIGO, ((Pelicula) peliculasAdapter.getItem(i)).getCodigo());
                startActivity(intent);
                finish();
            }
        });

    }

    public void cargarPeliculas(){
        mAPIService.obtenerPeliculas().enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.body().size() == 0)
                    Toast.makeText(getApplicationContext(),
                            "NO EXISTE LA PELICULA", Toast.LENGTH_SHORT).show();
                else
                   // peliculas = response.body();
                   // insertarAdaptador();
                    mostrarPeliculas(response.body());
                    Log.v("Error al Cargar",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Toast.makeText(PeliculasGET.this, "Error al conectar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mostrarPeliculas(List<Pelicula> peliculas){
        peliculasAdapter.actualizarPeliculas(peliculas);
        Log.v("Errores::", "Se ha actualizado el adaptador.");
    }

}
