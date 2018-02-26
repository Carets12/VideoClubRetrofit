package com.daniel.sierra.videoclubretrofit.modelos.remote;



import com.daniel.sierra.videoclubretrofit.modelos.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by daniel on 23/02/2018.
 */

public interface APIService {

    @POST("peliculas")
    Call<Pelicula> guardarPost(@Body Pelicula pelicula);

    @PUT("peliculas/{codigo}")
    Call<Pelicula> modificarPelicula(@Path("codigo") int codigo, @Body Pelicula pelicula);

    @GET("peliculas")
    Call<List<Pelicula>> obtenerPeliculas();

    @GET("peliculas/{codigo}")
    Call<List<Pelicula>> listarPelicula(@Path("codigo") int codigo);

    @DELETE("peliculas/{codigo}")
    Call<Pelicula> borrarPelicula(@Path("codigo") int codigo);

}

