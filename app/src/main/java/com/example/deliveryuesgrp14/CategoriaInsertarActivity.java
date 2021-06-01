package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaInsertarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText codCategoria;
    EditText editNombre;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//categoriaInsertar.php";
    private final String urlLocal = "http://192.168.1.3/ServicePDM/categoriaInsertar.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_inserta);

        helper = new ControlBDG14(this);
        codCategoria = (EditText) findViewById(R.id.codCategoria);
        editNombre = (EditText) findViewById(R.id.editNombre);

    }

    public void insertarCategoria(View v) {
        int codCat = Integer.parseInt(codCategoria.getText().toString());
        String nombre=editNombre.getText().toString();
        String regInsertados;
        Categoria categoria=new Categoria();
        categoria.setCodCategoria(codCat);
        categoria.setNombreCategoria(nombre);

        helper.abrir();
        regInsertados=helper.insertarCategoria(categoria);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro categoria, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_categoria="+codCat+"&nombre_categoria="+nombre;
            urlW = urlWeb+"?cod_categoria="+codCat+"&nombre_categoria="+nombre;
            String categoriaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String categoriaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        codCategoria.setText("");
        editNombre.setText("");

    }
}