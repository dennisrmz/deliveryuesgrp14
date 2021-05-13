package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteConsultarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodCliente;
    EditText editNombre;
    EditText editApellido;
    EditText editTelefono;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_consultar);
        helper = new ControlBDG14(this);
        CodCliente = (EditText) findViewById(R.id.codCliente);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTelefono = (EditText) findViewById(R.id.editTelefono);

    }
    public void consultarCliente(View v) {

        helper.abrir();
        Cliente cliente = helper.ConsultarCliente(CodCliente.getText().toString());
        helper.cerrar();
        if(cliente == null)
            Toast.makeText(this, "Cliente con codigo " + CodCliente.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombre.setText(cliente.getNombreCliente());
            editApellido.setText(cliente.getApellidoCliente());
            editTelefono.setText(cliente.getNumTelefono());

        }
    }


    public void limpiarTexto(View v) {
        CodCliente.setText("");

        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
    }
}