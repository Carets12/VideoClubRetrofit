package com.daniel.sierra.videoclubretrofit.listarPelicula;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daniel.sierra.videoclubretrofit.R;
import com.daniel.sierra.videoclubretrofit.modelos.Pelicula;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeliculasAdapter extends BaseAdapter {

    private Context context;
    private List<Pelicula> peliculas;

    public PeliculasAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    public void actualizarPeliculas(List<Pelicula> nuevaLista){
        this.peliculas = nuevaLista;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int i) {
        return peliculas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lista_peliculas_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Pelicula pelicula = (Pelicula) getItem(i);


        viewHolder.titulo.setText(pelicula.getTitulo());
        viewHolder.stock.setText(String.valueOf(pelicula.getStock()));
        viewHolder.codigo.setText(String.valueOf(pelicula.getCodigo()));
        Glide.with(context).load(pelicula.getFoto()).into(viewHolder.imagen);

        return view;
    }

    public static class ViewHolder {
        @BindView(R.id.tv_titulo)
        TextView titulo;
        @BindView(R.id.tv_stock)
        TextView stock;
        @BindView(R.id.tv_codigo)
        TextView codigo;
        @BindView(R.id.imagen)
        ImageView imagen;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
