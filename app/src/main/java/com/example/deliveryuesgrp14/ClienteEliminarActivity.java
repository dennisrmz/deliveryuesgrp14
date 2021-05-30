package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodCliente;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/cliente_delete.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/cliente_delete.php";
    @SuppressLint("NewApi")

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
        int cod =Integer.parseInt(CodCliente.getText().toString());
        cliente.setCodCliente(cod);
        helper.abrir();
        regEliminadas=helper.eliminarCliente(cliente);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_cliente="+cod;
        urlW = urlWeb+"?cod_cliente="+cod;
        String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

    }
}