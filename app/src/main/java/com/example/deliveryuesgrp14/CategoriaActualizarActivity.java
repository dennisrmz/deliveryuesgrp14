package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaActualizarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText codCategoria;
    EditText editNombre;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//categoriaActualizar.php";
    private final String urlLocal = "http://192.168.1.3/ServicePDM/categoriaActualizar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_actualizar);
        helper = new ControlBDG14(this);
        codCategoria = (EditText) findViewById(R.id.codCategoria);
        editNombre = (EditText) findViewById(R.id.editNombre);
    }
    public void actualizarCategoria(View V){

        int codCate = Integer.parseInt(codCategoria.getText().toString());
        String nombre = editNombre.getText().toString();

        Categoria categoria = new Categoria();
        categoria.setCodCategoria(codCate);
        categoria.setNombreCategoria(nombre);
        helper.abrir();
        String estado = helper.actualizarCategoria(categoria);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro categoria, Registro dublicado. Verificar insercion";
        if(estado.equals(mensajeError)){
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_categoria="+codCate+"&nombre_categoria="+nombre;
            urlW = urlWeb+"?cod_categoria="+codCate+"&nombre_categoria="+nombre;
            String categoriaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String categoriaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        codCategoria.setText("");
        editNombre.setText("");
    }
}