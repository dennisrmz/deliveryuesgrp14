package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodCliente;
    EditText editUsuario;
    EditText editNombre;
    EditText editApellido;
    EditText editTelefono;



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
    }
    public void limpiarTexto(View v) {
        CodCliente.setText("");
        editUsuario.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
    }
}