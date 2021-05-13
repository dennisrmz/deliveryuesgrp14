package com.example.deliveryuesgrp14;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MarcaActualizarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodMarca;
    EditText editNombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_actualizar);
        helper = new ControlBDG14(this);
        CodMarca = (EditText) findViewById(R.id.codMarca);
        editNombre = (EditText) findViewById(R.id.editNombre);

    }
    public void actualizarMarca(View V){
               String pivote;
        pivote =  CodMarca.getText().toString();
        int codMarca = Integer.parseInt(pivote);
        String nombre=editNombre.getText().toString();
        String regInsertados;
        Marca marca=new Marca();
        marca.setCodMarca(codMarca);
        marca.setNombreMarca(nombre);
        helper.abrir();
        String estado = helper.actualizar(marca);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        CodMarca.setText("");
        editNombre.setText("");
    }

}