package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolActualizarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editCodRol;
    EditText editDescripcion;
    EditText editNumCrud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_actualizar);

        helper = new ControlBDG14(this);
        editCodRol = (EditText) findViewById(R.id.editRol);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNumCrud = (EditText) findViewById(R.id.editNumRol);

    }

    public void actualizarRol(View v) {
        Rol role = new Rol();
        role.setIdRol(Integer.parseInt(editCodRol.getText().toString()));
        role.setDescripcion(editDescripcion.getText().toString());
        role.setNum(Integer.parseInt(editNumCrud.getText().toString()));
        helper.abrir();
        String estado = helper.actualizarRol(role);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
}