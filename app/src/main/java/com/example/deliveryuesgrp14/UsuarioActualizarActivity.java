package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UsuarioActualizarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editCorreo;
    EditText editNombre;
    EditText editContrasena;
    Spinner comboRoles;
    ArrayList<String> rolesList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actualizar);
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

    public void actualizarUsuario(View v) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(editCorreo.getText().toString());
        usuario.setNombreUsu(editNombre.getText().toString());
        usuario.setContrasena(editContrasena.getText().toString());
        String rolSelected  = comboRoles.getSelectedItem().toString();
        String [] parts = rolSelected.split("-");
        helper.abrir();
        String estado = helper.actualizarUsuario(usuario, Integer.parseInt(parts[0]));
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCorreo.setText("");
        editNombre.setText("");
        editContrasena.setText("");
    }
}