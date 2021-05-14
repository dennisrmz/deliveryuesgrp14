package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ComboProductoConsultarActivity extends AppCompatActivity {

    ListView listProducto;
    ControlBDG14 helper;
    EditText codMenu;
    EditText codProduct;
    EditText cantProduct;
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
        ArrayList<String> productos = helper.consultarCombo(codMenu);
        helper.cerrar();
        if(productos == null)
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