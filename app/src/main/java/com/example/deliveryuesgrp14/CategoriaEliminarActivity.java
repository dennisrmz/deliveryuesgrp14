package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaEliminarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText codCategoria;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//categoriaEliminar.php";
    private final String urlLocal = "http://192.168.1.3/ServicePDM/categoriaEliminar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_eliminar);
        helper = new ControlBDG14(this);
        codCategoria = (EditText) findViewById(R.id.codCategoria);
    }
    public void eliminarCategoria(View v){

        Integer regEliminadas;
        Categoria categoria = new Categoria();
        int codCate = Integer.parseInt(codCategoria.getText().toString());
        categoria.setCodCategoria(codCate);
        helper.abrir();
        regEliminadas=helper.eliminarCategoria(categoria);
        helper.cerrar();
        if(regEliminadas > 0){
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_categoria="+codCate;
            urlW = urlWeb+"?cod_categoria="+codCate;
            String categoriaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String categoriaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  "registros eliminados: " + regEliminadas, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error en la eliminacion", Toast.LENGTH_SHORT).show();
        }

    }
}