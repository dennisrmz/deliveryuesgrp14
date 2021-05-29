package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SuppressLint("NewApi")
public class MarcaInsertarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMarca;
    EditText editNombre;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//insertarMarca.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/insertarMarca.php";

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
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_marca="+codMarca+"&nombre_marca="+nombre;
            urlW = urlWeb+"?cod_marca="+codMarca+"&nombre_marca="+nombre;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v) {
        CodMarca.setText("");
        editNombre.setText("");

    }
}