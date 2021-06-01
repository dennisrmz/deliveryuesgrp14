package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
@SuppressLint("NewApi")
public class ComboProductoEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText codMenu;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/eliminarComboProuducto.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/eliminarComboProuducto.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_eliminar);
        helper = new ControlBDG14(this);
        codMenu = (EditText) findViewById(R.id.codMenu);
    }
    public void eliminarCombo(View v){
        String regEliminadas;
        ComboProducto combo = new ComboProducto();
        String pivote;
        pivote =  codMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        combo.setCodMenu(codMenu);
        helper.abrir();
        regEliminadas=helper.eliminarCombo(combo);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_menu="+codMenu;
        urlW = urlWeb+"?cod_menu="+codMenu;
        String combol = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String combow = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}