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
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        codCategoria.setText("");
        editNombre.setText("");
    }
}