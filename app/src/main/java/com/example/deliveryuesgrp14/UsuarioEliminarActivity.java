package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioEliminarActivity extends AppCompatActivity {

    EditText editCorreo;
    ControlBDG14 controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eliminar);
        controlhelper=new ControlBDG14 (this);
        editCorreo=(EditText)findViewById(R.id.editCorreo);
    }

    public void eliminarUsuario(View v){
        String regEliminadas;
        Usuario usuario=new Usuario();
        usuario.setCorreo(editCorreo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarUsuario(usuario);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}