package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
@SuppressLint("NewApi")
public class MenuRestEliminarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText CodMenu;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//eliminarMenu.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/eliminarMenu.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rest_eliminar);
        helper = new ControlBDG14(this);
        CodMenu = (EditText) findViewById(R.id.codMenu);
    }
    public void eliminarMenu(View v){
        String regEliminadas;
        MenuRest menu = new MenuRest();
        String pivote;
        pivote =  CodMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        menu.setCODMENU(codMenu);
        helper.abrir();
        regEliminadas=helper.eliminarMenu(menu);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_menu="+codMenu;
        urlW = urlWeb+"?cod_menu="+codMenu;
        String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}