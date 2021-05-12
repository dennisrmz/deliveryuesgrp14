package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoInsertarActivity extends Activity {

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
        setContentView(R.layout.activity_producto_insertar);
        helper = new ControlBDG14(this);
        CodProducto = (EditText) findViewById(R.id.codProducto);
        editCategoria = (EditText) findViewById(R.id.editCategoria);
        editMarca = (EditText) findViewById(R.id.editMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editDescripcionProd = (EditText) findViewById(R.id.editDescripcion);
        editExistencias = (EditText) findViewById(R.id.editExistencias);
    }
    public void insertarAlumno(View v) {
        int codProducto = (int) CodProducto.getTextAlignment();
        int categoria = (int) editCategoria.getTextAlignment();
        int marca = (int) editMarca.getTextAlignment();
        String nombre=editNombre.getText().toString();
        String descripcionProducto=editDescripcionProd.getText().toString();
        int existencias = (int) editExistencias.getTextAlignment();
        String regInsertados;
        Producto producto=new Producto();
        producto.setCodProduct(codProducto);
        producto.setCodCategoria(categoria);
        producto.setCodMarca(marca);
        producto.setNombreProducto(nombre);
        producto.setDescripcionProd(descripcionProducto);
        producto.setExistencias(existencias);

        helper.abrir();
        regInsertados=helper.insertar(producto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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