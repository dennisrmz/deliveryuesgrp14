package com.example.deliveryuesgrp14;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoConsultarActivity extends Activity {

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
        setContentView(R.layout.activity_producto_consultar);
        helper = new ControlBDG14(this);
        CodProducto = (EditText) findViewById(R.id.codProducto);
        editCategoria = (EditText) findViewById(R.id.editCategoria);
        editMarca = (EditText) findViewById(R.id.editMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editDescripcionProd = (EditText) findViewById(R.id.editDescripcion);
        editExistencias = (EditText) findViewById(R.id.editExistencias);
    }
    public void consultarProducto(View v) {
        helper.abrir();
        Producto producto = helper.consultarProducto(CodProducto.getText().toString());
        helper.cerrar();
        if(producto == null)
            Toast.makeText(this, "Producto con el codigo " +
                    CodProducto.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editCategoria.setText(Integer.toString(producto.getCodCategoria()));
            editMarca.setText(Integer.toString(producto.getCodMarca()));
            editNombre.setText(producto.getNombreProducto());
            editDescripcionProd.setText(producto.getDescripcionProd());
            editExistencias.setText(Integer.toString(producto.getExistencias()));
        }
    }
    public void limpiarTexto(View v){
        CodProducto.setText("");
        editCategoria.setText("");
        editMarca.setText("");
        editNombre.setText("");
        editDescripcionProd.setText("");
        editExistencias.setText("");
    }
}