package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
@SuppressLint("NewApi")
public class ProductoEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodProducto;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//eliminarProducto.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/eliminarProducto.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        helper = new ControlBDG14(this);
        CodProducto = (EditText) findViewById(R.id.codProducto);
    }
    public void eliminarProducto(View v){
        String regEliminadas;
        Producto producto=new Producto();
        String pivote;
        pivote =  CodProducto.getText().toString();
        int codProducto = Integer.parseInt(pivote);
        producto.setCodProduct(codProducto);
        helper.abrir();
        regEliminadas=helper.eliminarProducto(producto);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_producto="+codProducto;
        urlW = urlWeb+"?cod_producto="+codProducto;
        String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}