package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SuppressLint("NewApi")
public class CategoriaConsultarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText codCategoria;
    EditText editNombreCategoria;
    static List<Categoria> listaCategorias;
    static List<String> nombreCategorias;
    ListView listViewCategorias;
    private final String urlLocal = "http://192.168.1.3/ServicePDM/categoriaConsultar.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDG14(this);
        codCategoria = (EditText) findViewById(R.id.codCategoria);
        editNombreCategoria = (EditText) findViewById(R.id.editNombre);
        listaCategorias = new ArrayList<Categoria>();
        nombreCategorias = new ArrayList<String>();
        listViewCategorias = (ListView) findViewById(R.id.listView1);
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

    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        url = urlLocal;
        String categoria = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaCategorias.addAll(consumoWSG14.obtenerCategoriasExterno(categoria, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListView() {
        String dato = "";
        nombreCategorias.clear();
        for (int i = 0; i < listaCategorias.size(); i++) {
            dato = listaCategorias.get(i).getCodCategoria() + "    "
                    + listaCategorias.get(i).getNombreCategoria();
            nombreCategorias.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreCategorias);
        listViewCategorias.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<Categoria> conjuntoMateria = new HashSet<Categoria>();
        conjuntoMateria.addAll(listaCategorias);
        listaCategorias.clear();
        listaCategorias.addAll(conjuntoMateria);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreCategorias);
        nombreCategorias.clear();
        nombreCategorias.addAll(conjuntoNombre);
    }

    public void limpiarTexto(View v){
        codCategoria.setText("");
        editNombreCategoria.setText("");

    }
}