package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuRestConsultarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodMenu;
    EditText editCodLocal;
    EditText editPrecioComb;
    EditText editDescripCombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rest_consultar);
        helper = new ControlBDG14(this);
        CodMenu = (EditText) findViewById(R.id.codMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editPrecioComb = (EditText) findViewById(R.id.editPrec);
        editDescripCombo = (EditText) findViewById(R.id.editDescripCombo);
    }
    public void consultarMenu(View v) {
        helper.abrir();
        MenuRest menuRest = helper.consultarMenu(CodMenu.getText().toString());
        helper.cerrar();
        if(menuRest == null)
            Toast.makeText(this, "Mennu con el codigo " +
                    CodMenu.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editCodLocal.setText(Integer.toString(menuRest.getCODLOCAL()));
            editPrecioComb.setText(Float.toString(menuRest.getPRECIOCOMBO()));
            editDescripCombo.setText(menuRest.getDESCRIPCIONCOMBO());

        }
    }
    public void limpiarTexto(View v){
        CodMenu.setText("");
        editCodLocal.setText("");
        editPrecioComb.setText("");
        editDescripCombo.setText("");
    }
}