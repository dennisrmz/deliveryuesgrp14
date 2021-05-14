package com.example.deliveryuesgrp14;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuRestInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodMenu;
    EditText editCodLocal;
    EditText editPrecioComb;
    EditText editDescripCombo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rest_insertar);
        helper = new ControlBDG14(this);
        CodMenu = (EditText) findViewById(R.id.codMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editPrecioComb = (EditText) findViewById(R.id.editPrec);
        editDescripCombo = (EditText) findViewById(R.id.editDescripCombo);

    }
    public void insertarMenu(View v) {
        String pivote;
        pivote =  CodMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        pivote =  editCodLocal.getText().toString();
        int codLocal = Integer.parseInt(pivote);
        pivote =  editPrecioComb.getText().toString();
        float precioComb = Float.parseFloat(pivote);
        String descripcionComb = editDescripCombo.getText().toString();

        String regInsertados;
        MenuRest menuRest = new MenuRest();
        menuRest.setCODMENU(codMenu);
        menuRest.setCODLOCAL(codLocal);
        menuRest.setPRECIOCOMBO(precioComb);
        menuRest.setDESCRIPCIONCOMBO(descripcionComb);


        helper.abrir();
        regInsertados=helper.insertarMenu(menuRest);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        CodMenu.setText("");
        editCodLocal.setText("");
        editPrecioComb.setText("");
        editDescripCombo.setText("");

    }
}