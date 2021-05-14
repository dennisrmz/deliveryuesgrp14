package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComboProductoEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText codMenu;

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
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}