package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuRestEliminarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText CodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rest_eliminar);
        helper = new ControlBDG14(this);
        CodMenu = (EditText) findViewById(R.id.codMenu);
    }
    public void eliminarMenu(View v){
        String regEliminadas;
        MenuRest menu = new MenuRest();
        String pivote;
        pivote =  CodMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        menu.setCODMENU(codMenu);
        helper.abrir();
        regEliminadas=helper.eliminarMenu(menu);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}