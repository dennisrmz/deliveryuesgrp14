package com.example.deliveryuesgrp14;


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

import androidx.appcompat.app.AppCompatActivity;
@SuppressLint("NewApi")
public class MarcaConsultarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMarca;
    EditText editNombreMarca;
    static List<Marca> listaMarcas;
    static List<String> nombreMarca;
    ListView listViewMarcass;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//marca.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/marca.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDG14(this);
        CodMarca = (EditText) findViewById(R.id.codMarca);
        editNombreMarca = (EditText) findViewById(R.id.editNombre);
        listaMarcas = new ArrayList<Marca>();
        nombreMarca = new ArrayList<String>();
        listViewMarcass = (ListView) findViewById(R.id.listView1);
    }
    public void consultarMarca(View v) {
        helper.abrir();
        Marca marca = helper.consultarMarca(CodMarca.getText().toString());
        helper.cerrar();

        if(marca == null)
            Toast.makeText(this, "Marca con el codigo " +
                    CodMarca.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editNombreMarca.setText(marca.getNombreMarca());

        }
    }
    public void servicioPHP(View v) {
         String url = "";
        // it was the first button
        url = urlLocal;
        listaMarcas.clear();
        String marca = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaMarcas.addAll(consumoWSG14.obtenerMarcas(marca, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        url = urlWeb;
        listaMarcas.clear();
        String marca = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaMarcas.addAll(consumoWSG14.obtenerMarcas(marca, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreMarca.clear();
        for (int i = 0; i < listaMarcas.size(); i++) {
            dato = listaMarcas.get(i).getCodMarca() + "    "
                    + listaMarcas.get(i).getNombreMarca();
            nombreMarca.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreMarca);
        listViewMarcass.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Marca> conjuntoMateria = new HashSet<Marca>();
        conjuntoMateria.addAll(listaMarcas);
        listaMarcas.clear();
        listaMarcas.addAll(conjuntoMateria);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreMarca);
        nombreMarca.clear();
        nombreMarca.addAll(conjuntoNombre);
    }
    public void limpiarTexto(View v){
        CodMarca.setText("");
        editNombreMarca.setText("");
        listViewMarcass.setAdapter(null);
    }
}