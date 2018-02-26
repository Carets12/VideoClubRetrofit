package com.daniel.sierra.videoclubretrofit.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by daniel on 23/02/2018.
 */

public class Pelicula implements Serializable {

    @SerializedName("codigo")
    @Expose
    private int codigo;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("director")
    @Expose
    private String director;

    @SerializedName("precio")
    @Expose
    private float precio;

    @SerializedName("anio")
    @Expose
    private int anio;

    @SerializedName("stock")
    @Expose
    private int stock;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Pelicula() {
    }

    public Pelicula(int codigo, String foto, String titulo, String director, float precio, int anio, int stock, String descripcion) {
        this.codigo = codigo;
        this.foto = foto;
        this.titulo = titulo;
        this.director = director;
        this.precio = precio;
        this.anio = anio;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return  "codigo: " + codigo + ", foto: " + foto + ", titulo: " + titulo +
                ", director: " + director + ", preci: " + precio + ", anio: " + anio +
                ", stock: " + stock + ", descripcion: " + descripcion;
    }
}
