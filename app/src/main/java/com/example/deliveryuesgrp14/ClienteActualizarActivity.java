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
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/cliente_update.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/cliente_update.php";
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
        int cod = Integer.parseInt(CodCliente.getText().toString());
        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String tel=editTelefono.getText().toString();
        cliente.setCodCliente(cod);
        cliente.setNombreCliente(nombre);
        cliente.setApellidoCliente(apellido);
        cliente.setNumTelefono(tel);
        helper.abrir();
        String estado = helper.actualizarCliente(cliente);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(estado.equals(mensajeError)){
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_cliente="+cod+"&nombre_cliente="+nombre+"&apellido_cliente="+apellido+"&numTel="+tel;
            urlW = urlWeb+"?cod_cliente="+cod+"&nombre_cliente="+nombre+"&apellido_cliente="+apellido+"&numTel="+tel;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        CodCliente.setText("");

        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
    }
}