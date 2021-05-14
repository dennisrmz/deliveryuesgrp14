package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriaConsultarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText codCategoria;
    EditText editNombreCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_consultar);
        helper = new ControlBDG14(this);
        codCategoria = (EditText) findViewById(R.id.codCategoria);
        editNombreCategoria = (EditText) findViewById(R.id.editNombre);
    }

    public void consultarCategoria(View v) {
        helper.abrir();
        Categoria categoria = helper.consultarCategoria(codCategoria.getText().toString());
        helper.cerrar();
        if(categoria == null)
            Toast.makeText(this, "Categoria con el codigo " +
                    codCategoria.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editNombreCategoria.setText(categoria.getNombreCategoria());

        }
    }
    public void limpiarTexto(View v){
        codCategoria.setText("");
        editNombreCategoria.setText("");

    }
}