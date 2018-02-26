package com.daniel.sierra.videoclubretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniel.sierra.videoclubretrofit.buscarPelicula.PeliculaGETBuscar;
import com.daniel.sierra.videoclubretrofit.insertarPelicula.PeliculasPOST;
import com.daniel.sierra.videoclubretrofit.listarPelicula.PeliculasGET;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.fButtonInsertar)
    Button bInsertar;
    @BindView(R.id.fButtonListar)
    Button bListar;
    @BindView(R.id.fButtonBuscar)
    Button bBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bInsertar.setOnClickListener(this);
        bListar.setOnClickListener(this);
        bBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fButtonInsertar:
                Intent intentInsertar = new Intent(this, PeliculasPOST.class);
                startActivity(intentInsertar);
                break;

            case R.id.fButtonListar:
                Intent intentListar = new Intent(this, PeliculasGET.class);
                startActivity(intentListar);
                break;

            case R.id.fButtonBuscar:
                Intent intenetBuscar = new Intent(this, PeliculaGETBuscar.class);
                startActivity(intenetBuscar);
                break;
        }
    }
}
