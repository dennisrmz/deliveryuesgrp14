package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MarcaInsertarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMarca;
    EditText editNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_insertar);
        helper = new ControlBDG14(this);
        CodMarca = (EditText) findViewById(R.id.codMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);

    }
    public void insertarMarca(View v) {
        String pivote;
        pivote =  CodMarca.getText().toString();
        int codMarca = Integer.parseInt(pivote);
        String nombre=editNombre.getText().toString();
        String regInsertados;
        Marca marca=new Marca();
        marca.setCodMarca(codMarca);
        marca.setNombreMarca(nombre);

        helper.abrir();
        regInsertados=helper.insertarMarca(marca);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        CodMarca.setText("");
        editNombre.setText("");

    }
}