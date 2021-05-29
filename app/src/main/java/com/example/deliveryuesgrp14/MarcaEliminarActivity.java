package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
@SuppressLint("NewApi")
public class MarcaEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMarca;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//eliminarMarca.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/eliminarMarca.php";
    @SuppressLint("NewApi")
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
        regEliminadas=helper.eliminarMarca(marca);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_marca="+codMarca;
        urlW = urlWeb+"?cod_marca="+codMarca;
        String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}