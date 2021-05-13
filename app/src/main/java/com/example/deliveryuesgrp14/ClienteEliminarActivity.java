package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_eliminar);
        helper=new ControlBDG14(this);
        CodCliente=(EditText)findViewById(R.id.codCliente);
    }
    public void eliminarCLiente(View v){
        String regEliminadas;
        Cliente cliente= new Cliente();
        cliente.setCodCliente(Integer.parseInt(CodCliente.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminarCliente(cliente);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}