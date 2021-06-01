package com.example.deliveryuesgrp14;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SuppressLint("NewApi")
public class ProductoConsultarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodProducto;
    EditText editCategoria;
    EditText editMarca;
    EditText editNombre;
    EditText editDescripcionProd;
    EditText editExistencias;
    EditText editPrec;
    static List<Producto> listaProductos;
    static List<String> nombreProducto;
    ListView listViewProductos;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//producto.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/producto.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_consultar);
        helper = new ControlBDG14(this);
        CodProducto = (EditText) findViewById(R.id.codProducto);
        editCategoria = (EditText) findViewById(R.id.editCategoria);
        editMarca = (EditText) findViewById(R.id.editMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editDescripcionProd = (EditText) findViewById(R.id.editDescripcion);
        editExistencias = (EditText) findViewById(R.id.editExistencias);
        editPrec = (EditText) findViewById(R.id.editPrec);
        listaProductos = new ArrayList<Producto>();
        nombreProducto = new ArrayList<String>();
        listViewProductos = (ListView) findViewById(R.id.listView1);
    }
    public void consultarProducto(View v) {
        helper.abrir();
        Producto producto = helper.consultarProducto(CodProducto.getText().toString());
        helper.cerrar();
        if(producto == null)
            Toast.makeText(this, "Producto con el codigo " +
                    CodProducto.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editCategoria.setText(Integer.toString(producto.getCodCategoria()));
            editMarca.setText(Integer.toString(producto.getCodMarca()));
            editNombre.setText(producto.getNombreProducto());
            editDescripcionProd.setText(producto.getDescripcionProd());
            editExistencias.setText(Integer.toString(producto.getExistencias()));
            editPrec.setText(Float.toString(producto.getPrecio()));
        }
    }

    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        url = urlLocal;
        listaProductos.clear();
        String marca = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaProductos.addAll(consumoWSG14.obtenerProductos(marca, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void servicioWEB(View v) {
        String urlW = "";
        // it was the first button
        urlW = urlWeb;
        listaProductos.clear();
        String producto = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        try {
            listaProductos.addAll(consumoWSG14.obtenerProductos(producto, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreProducto.clear();
        for (int i = 0; i < listaProductos.size(); i++) {
            dato =  listaProductos.get(i).getNombreProducto() + "    "
                    + listaProductos.get(i).getCodCategoria() + "    "
                    + listaProductos.get(i).getCodMarca() + "    "
                    + listaProductos.get(i).getDescripcionProd() + "    "
                    + listaProductos.get(i).getExistencias() + "    "
                    + listaProductos.get(i).getPrecio() ;
            nombreProducto.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreProducto);
        listViewProductos.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Producto> conjuntoProducto = new HashSet<Producto>();
        conjuntoProducto.addAll(listaProductos);
        listaProductos.clear();
        listaProductos.addAll(conjuntoProducto);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreProducto);
        nombreProducto.clear();
        nombreProducto.addAll(conjuntoNombre);
    }
    public void limpiarTexto(View v){
        CodProducto.setText("");
        editCategoria.setText("");
        editMarca.setText("");
        editNombre.setText("");
        editDescripcionProd.setText("");
        editExistencias.setText("");
        editPrec.setText("");
        listViewProductos.setAdapter(null);
    }
}