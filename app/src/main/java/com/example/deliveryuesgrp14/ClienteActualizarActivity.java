package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteActualizarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodCliente;

    EditText editNombre;
    EditText editApellido;
    EditText editTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_actualizar);
        helper = new ControlBDG14(this);
        CodCliente = (EditText) findViewById(R.id.codCliente);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTelefono = (EditText) findViewById(R.id.editTelefono);

    }
    public void actualizarCliente(View v) {
        Cliente cliente = new Cliente();
        cliente.setCodCliente(Integer.parseInt(CodCliente.getText().toString()));
        cliente.setNombreCliente(editNombre.getText().toString());
        cliente.setApellidoCliente(editApellido.getText().toString());
        cliente.setNumTelefono(editTelefono.getText().toString());
        helper.abrir();
        String estado = helper.actualizarCliente(cliente);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        CodCliente.setText("");

        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
    }
}