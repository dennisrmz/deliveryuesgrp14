package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

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


public class ComboProductoInsertarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    Spinner codMenu;
    Spinner codProduct;
    EditText cantProduct;

    ArrayList<String> listaMenus;
    ArrayList<String> listarProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_insertar);
        helper = new ControlBDG14(this);
        codMenu = (Spinner) findViewById(R.id.codMenu);
        codProduct = (Spinner)  findViewById(R.id.codProducto);
        cantProduct = (EditText) findViewById(R.id.editCantProduct);
        helper.abrir();
        listarProductos=helper.listarProductos();
        listaMenus=helper.listarMenus();
        helper.cerrar();
        if(listarProductos.isEmpty() == false && listaMenus.isEmpty() == false){
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listarProductos);
            codProduct.setAdapter(adapter);
            ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaMenus);
            codMenu.setAdapter(adapter2);
        }else {
            Toast.makeText(this, "No se ha registrado un menu o un producto", Toast.LENGTH_LONG).show();
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
    public void insertarCombo(View v) {
        String pivote;
        String productos = codProduct.getSelectedItem().toString();
        int codProduct = getid(productos);
        String menus = codMenu.getSelectedItem().toString();
        int codMenu =  getid(menus);
        pivote =  cantProduct.getText().toString();
        int cantProduct = Integer.parseInt(pivote);

        String regInsertados;
        ComboProducto combo = new ComboProducto();
        combo.setCodMenu(codMenu);
        combo.setCodProducto(codProduct);
        combo.setCantProduct(cantProduct);

        helper.abrir();
        regInsertados=helper.insertarCombo(combo);
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
        cantProduct.setText("");

    }
}