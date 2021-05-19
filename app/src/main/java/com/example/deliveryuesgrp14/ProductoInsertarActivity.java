package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductoInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodProducto;
    Spinner editCategoria;
    Spinner editMarca;
    EditText editNombre;
    EditText editDescripcionProd;
    EditText editExistencias;
    EditText editPrec;
    ArrayList<String> listaCategorias;
    ArrayList<String> listaMarcas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_insertar);
        helper = new ControlBDG14(this);
        CodProducto = (EditText) findViewById(R.id.codProducto);
        editCategoria = (Spinner) findViewById(R.id.codCategoria);
        editMarca = (Spinner) findViewById(R.id.codMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editDescripcionProd = (EditText) findViewById(R.id.editDescripcion);
        editExistencias = (EditText) findViewById(R.id.editExistencias);
        editPrec = (EditText) findViewById(R.id.editPrec);

        helper.abrir();
        listaCategorias=helper.listarCategorias();
        listaMarcas=helper.listarMarcas();
        helper.cerrar();
        if(listaCategorias.isEmpty() == false && listaMarcas.isEmpty() == false ){
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaCategorias);
            editCategoria.setAdapter(adapter);
            ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaMarcas);
            editMarca.setAdapter(adapter2);
        }else {
            Toast.makeText(this, "No se ha registrado una marca o categoria ", Toast.LENGTH_LONG).show();
            Log.i("Pedido vacio", "en el no: ");
            try{
                Class<?>
                        clase=Class.forName("com.example.deliveryuesgrp14.MainActivity");
                Intent inte = new Intent(this,clase);

                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    public void insertarProducto(View v) {
        String pivote;
        pivote =  CodProducto.getText().toString();
        int codProducto = Integer.parseInt(pivote);
        String categorias = editCategoria.getSelectedItem().toString();
        int categoria = getid(categorias);
        String marcas = editMarca.getSelectedItem().toString();
        int marca =  getid(marcas);
        String nombre=editNombre.getText().toString();
        String descripcionProducto=editDescripcionProd.getText().toString();
        pivote = editExistencias.getText().toString();
        int existencias = Integer.parseInt(pivote);
        pivote = editPrec.getText().toString();
        float precio = Float.parseFloat(pivote);
        String regInsertados;
        Producto producto=new Producto();
        producto.setCodProduct(codProducto);
        producto.setCodCategoria(categoria);
        producto.setCodMarca(marca);
        producto.setNombreProducto(nombre);
        producto.setDescripcionProd(descripcionProducto);
        producto.setExistencias(existencias);
        producto.setPrecio(precio);

        helper.abrir();
        regInsertados=helper.insertarProducto(producto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    private int getid(String text) {
        int codigoM=0;
        Matcher encontrado = Pattern.compile("\\d+").matcher(text);
        while ( encontrado .find()) {
            codigoM = Integer.parseInt( encontrado .group());
        }
        return codigoM;
    }
    public void limpiarTexto(View v) {
        CodProducto.setText("");
        editNombre.setText("");
        editDescripcionProd.setText("");
        editExistencias.setText("");
    }
}