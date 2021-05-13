package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolEliminarActivity extends AppCompatActivity {
    EditText editIdRol;
    ControlBDG14 controlhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_eliminar);
        controlhelper=new ControlBDG14 (this);
        editIdRol=(EditText)findViewById(R.id.editRol);
    }

    public void eliminarRol(View v){
        String regEliminadas;
        Rol role=new Rol();
        role.setIdRol(Integer.parseInt(editIdRol.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarRol(role);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}