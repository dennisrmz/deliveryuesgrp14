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

import java.util.ArrayList;
@SuppressLint("NewApi")
public class ComboProductoConsultarActivity extends AppCompatActivity {

    ListView listProducto;
    ControlBDG14 helper;
    EditText codMenu;
    static List<String> nombreMarca;
    ArrayList<String> productos;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//comboProducto.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/comboProducto.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_consultar);
        helper = new ControlBDG14(this);
        codMenu = (EditText) findViewById(R.id.codMenu);
        listProducto = (ListView) findViewById(R.id.listProduct);

    }
    public void consultarCombo(View v) {
        helper.abrir();
        String pivote = codMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        productos = helper.consultarCombo(codMenu);
        helper.cerrar();
        if(productos.isEmpty())
            Toast.makeText(this, "Menu con el codigo " +
                    codMenu +
                    " no posee productos", Toast.LENGTH_LONG).show();
        else{
            ArrayAdapter<String> producto = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, productos);
            listProducto.setAdapter(producto);

        }
    }
    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        String pivote = codMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        url = urlLocal+"?cod_menu="+codMenu;
        String combo = consumoWSG14.obtenerRespuestaPeticion(url, this);
        productos = consumoWSG14.combos(combo, this);
        if(productos.isEmpty())
            Toast.makeText(this, "Menu con el codigo " +
                    codMenu +
                    " no posee productos", Toast.LENGTH_LONG).show();
        else{
            ArrayAdapter<String> producto = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, productos);
            listProducto.setAdapter(producto);
        }
    }
    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        String pivote = codMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        url = urlWeb+"?cod_menu="+codMenu;
        String combo = consumoWSG14.obtenerRespuestaPeticion(url, this);
        productos = consumoWSG14.combos(combo, this);
        if(productos.isEmpty())
            Toast.makeText(this, "Menu con el codigo " +
                    codMenu +
                    " no posee productos", Toast.LENGTH_LONG).show();
        else{
            ArrayAdapter<String> producto = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, productos);
            listProducto.setAdapter(producto);
        }
    }

    public void limpiarTexto(View v){
        codMenu.setText("");
        listProducto.setAdapter(null);
    }
}