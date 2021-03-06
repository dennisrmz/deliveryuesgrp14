package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UsuarioInsertarActivity extends Activity {
    ControlBDG14 helper;
    EditText editCorreo;
    EditText editNombre;
    EditText editContrasena;
    Spinner comboRoles;
    ArrayList<String> rolesList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_insertar);
        helper = new ControlBDG14(this);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
        comboRoles = (Spinner) findViewById(R.id.spinnerOpcionesCrud);
        helper.abrir();
        rolesList = helper.consultarRoles();
        helper.cerrar();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item,rolesList);
        comboRoles.setAdapter(adaptador);
    }

    public void insertarUsuario(View v) {
        String correo = editCorreo.getText().toString();
        String nombre = editNombre.getText().toString();
        String contrasena = editContrasena.getText().toString();
        String rolSelected  = comboRoles.getSelectedItem().toString();
        String regInsertados;
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setNombreUsu(nombre);
        usuario.setContrasena(contrasena);
        String [] parts = rolSelected.split("-");
        helper.abrir();
        regInsertados = helper.insertarUsuario(usuario, parts[0]);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCorreo.setText("");
        editNombre.setText("");
        editContrasena.setText("");
    }


}