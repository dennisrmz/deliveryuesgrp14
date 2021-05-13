package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoActualizarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodProducto;
    EditText editCategoria;
    EditText editMarca;
    EditText editNombre;
    EditText editDescripcionProd;
    EditText editExistencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_actualizar);
        helper = new ControlBDG14(this);
        CodProducto = (EditText) findViewById(R.id.codProducto);
        editCategoria = (EditText) findViewById(R.id.editCategoria);
        editMarca = (EditText) findViewById(R.id.editMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editDescripcionProd = (EditText) findViewById(R.id.editDescripcion);
        editExistencias = (EditText) findViewById(R.id.editExistencias);
    }
    public void actualizarProducto(View V){
        String pivote;
        pivote =  CodProducto.getText().toString();
        int codProducto = Integer.parseInt(pivote);
        pivote =  editCategoria.getText().toString();
        int categoria = Integer.parseInt(pivote);
        pivote =  editMarca.getText().toString();
        int marca = Integer.parseInt(pivote);
        String nombre=editNombre.getText().toString();
        String descripcionProducto=editDescripcionProd.getText().toString();
        pivote = editExistencias.getText().toString();
        int existencias = Integer.parseInt(pivote);
        String regInsertados;
        Producto producto=new Producto();
        producto.setCodProduct(codProducto);
        producto.setCodCategoria(categoria);
        producto.setCodMarca(marca);
        producto.setNombreProducto(nombre);
        producto.setDescripcionProd(descripcionProducto);
        producto.setExistencias(existencias);
        helper.abrir();
        String estado = helper.actualizar(producto);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        CodProducto.setText("");
        editCategoria.setText("");
        editMarca.setText("");
        editNombre.setText("");
        editDescripcionProd.setText("");
        editExistencias.setText("");
    }
}