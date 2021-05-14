package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaEliminarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText codCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_eliminar);
        helper = new ControlBDG14(this);
        codCategoria = (EditText) findViewById(R.id.codCategoria);
    }
    public void eliminarCategoria(View v){
        String regEliminadas;
        Categoria categoria = new Categoria();
        int codCate = Integer.parseInt(codCategoria.getText().toString());
        categoria.setCodCategoria(codCate);
        helper.abrir();
        regEliminadas=helper.eliminarCategoria(categoria);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}