package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolInsertarActivity extends Activity {
    ControlBDG14 helper;
    EditText editRol;
    EditText editDescripcion;
    EditText editNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_insertar);
        helper = new ControlBDG14(this);
        editRol = (EditText) findViewById(R.id.editRol);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNum = (EditText) findViewById(R.id.editNumber);
    }

    public void insertarRol(View v) {
        int idrol = Integer.parseInt(editRol.getText().toString());
        String descripcion = editDescripcion.getText().toString();
        int number = Integer.parseInt(editNum.getText().toString());
        String regInsertados;
        Rol role = new Rol();
        role.setIdRol(idrol);
        role.setDescripcion(descripcion);
        role.setNum(number);
        helper.abrir();
        regInsertados = helper.insertarRol(role);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editRol.setText("");
        editDescripcion.setText("");
        editNum.setText("");
    }
}