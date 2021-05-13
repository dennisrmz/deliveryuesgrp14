package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MarcaConsultarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodMarca;
    EditText editNombreMarca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_consultar);
        helper = new ControlBDG14(this);
        CodMarca = (EditText) findViewById(R.id.codMarca);
        editNombreMarca = (EditText) findViewById(R.id.editNombre);
    }
    public void consultarMarca(View v) {
        helper.abrir();
        Marca marca = helper.consultarMarca(CodMarca.getText().toString());
        helper.cerrar();
        if(marca == null)
            Toast.makeText(this, "Marca con el codigo " +
                    CodMarca.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editNombreMarca.setText(marca.getNombreMarca());

        }
    }
    public void limpiarTexto(View v){
        CodMarca.setText("");
        editNombreMarca.setText("");

    }
}