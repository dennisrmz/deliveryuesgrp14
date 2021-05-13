package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioConsultarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editCorreo;
    EditText editNombre;
    EditText editContrasena;
    EditText editRol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_consultar);

        helper = new ControlBDG14(this);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
        editRol = (EditText) findViewById(R.id.editConsulRol);
    }

    public void consultarUsuario(View v) {
        helper.abrir();
        Usuario usuario = helper.consultarUsuario(editCorreo.getText().toString());
        helper.cerrar();
        if(usuario == null)
            Toast.makeText(this, "Usuario con correo " + editCorreo.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            helper.abrir();
            String rol = helper.consultarRolUser(usuario.getCodUsuario());
            helper.cerrar();

            editCorreo.setText(usuario.getCorreo());
            editNombre.setText(usuario.getNombreUsu());
            editContrasena.setText(usuario.getContrasena());
            editRol.setText(rol);
        }
    }

    public void limpiarTexto(View v){
        editCorreo.setText("");
        editNombre.setText("");
        editContrasena.setText("");
    }

}