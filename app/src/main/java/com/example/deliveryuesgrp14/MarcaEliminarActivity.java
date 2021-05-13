package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MarcaEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMarca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_eliminar);
        helper = new ControlBDG14(this);
        CodMarca = (EditText) findViewById(R.id.codMarca);
    }
    public void eliminarMarca(View v){
        String regEliminadas;
        Marca marca = new Marca();
        String pivote;
        pivote =  CodMarca.getText().toString();
        int codMarca = Integer.parseInt(pivote);
        marca.setCodMarca(codMarca);
        helper.abrir();
        regEliminadas=helper.eliminar(marca);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}