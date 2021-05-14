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
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        codCategoria.setText("");
        editNombre.setText("");

    }
}