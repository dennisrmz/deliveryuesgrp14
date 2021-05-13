package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodProducto;

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
        regEliminadas=helper.eliminar(producto);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}