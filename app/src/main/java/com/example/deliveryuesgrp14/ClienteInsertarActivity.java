package com.example.deliveryuesgrp14;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ClienteInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodCliente;
    EditText editUsuario;
    EditText editNombre;
    EditText editApellido;
    EditText editTelefono;


    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/cliente_insert.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/cliente_insert.php";
    @SuppressLint("NewApi")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_insertar);
        helper = new ControlBDG14(this);
        CodCliente = (EditText) findViewById(R.id.codCliente);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTelefono = (EditText) findViewById(R.id.editTelefono);


    }
    public void insertarCliente(View v) {
        int codCliente =(int)Integer.parseInt(CodCliente.getText().toString());
        //int codCliente = (int) CodCliente.getTextAlignment();
        int usuario = (int)Integer.parseInt(editUsuario.getText().toString());
        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String telefono=editTelefono.getText().toString();
        String regInsertados;
        Cliente cliente =new Cliente();
        cliente.setCodCliente(codCliente);
        cliente.setCodUsuario(usuario);
        cliente.setNombreCliente(nombre);
        cliente.setApellidoCliente(apellido);
        cliente.setNumTelefono(telefono);

        helper.abrir();
        regInsertados=helper.insertarCliente(cliente);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_cliente="+codCliente+"&cod_usuario="+usuario+"&nombre_cliente="+nombre+"&apellido_cliente="+apellido+"&numTel="+telefono;
            urlW = urlWeb+"?cod_cliente="+codCliente+"&cod_usuario="+usuario+"&nombre_cliente="+nombre+"&apellido_cliente="+apellido+"&numTel="+telefono;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }


    }

    public void limpiarTexto(View v) {
        CodCliente.setText("");
        editUsuario.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
    }
}