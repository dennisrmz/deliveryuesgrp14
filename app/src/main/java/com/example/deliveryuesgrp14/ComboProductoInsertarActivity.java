package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ComboProductoInsertarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText codMenu;
    EditText codProduct;
    EditText cantProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_producto_insertar);
        helper = new ControlBDG14(this);
        codMenu = (EditText) findViewById(R.id.codMenu);
        codProduct = (EditText) findViewById(R.id.codProducto);
        cantProduct = (EditText) findViewById(R.id.editCantProduct);

    }
    public void insertarCombo(View v) {
        String pivote;
        pivote =  codMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        pivote =  codProduct.getText().toString();
        int codProduct = Integer.parseInt(pivote);
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
    public void limpiarTexto(View v) {
        codMenu.setText("");
        codProduct.setText("");
        cantProduct.setText("");

    }
}