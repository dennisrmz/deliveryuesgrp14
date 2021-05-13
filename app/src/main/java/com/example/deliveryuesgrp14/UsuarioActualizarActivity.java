package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioActualizarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editCorreo;
    EditText editNombre;
    EditText editContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actualizar);
        helper = new ControlBDG14(this);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editContrasena = (EditText) findViewById(R.id.editContrasena);

    }

    public void actualizarUsuario(View v) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(editCorreo.getText().toString());
        usuario.setNombreUsu(editNombre.getText().toString());
        usuario.setContrasena(editContrasena.getText().toString());
        helper.abrir();
        String estado = helper.actualizarUsuario(usuario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editCorreo.setText("");
        editNombre.setText("");
        editContrasena.setText("");
    }
}