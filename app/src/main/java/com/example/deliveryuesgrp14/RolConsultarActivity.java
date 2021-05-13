package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolConsultarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editIdRol;
    EditText editDescripcion;
    EditText editNumCrud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_consultar);

        helper = new ControlBDG14(this);
        editIdRol = (EditText) findViewById(R.id.editRol);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNumCrud = (EditText) findViewById(R.id.editNumber);
    }

    public void consultarRol(View v) {
        helper.abrir();
        Rol role = helper.consultarRol(editIdRol.getText().toString());
        helper.cerrar();
        if(role == null)
            Toast.makeText(this, "Usuario con correo " + editIdRol.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdRol.setText(Integer.toString(role.getIdRol()));
            editDescripcion.setText(role.getDescripcion());
            editNumCrud.setText(Integer.toString(role.getNum()));
        }
    }

    public void limpiarTexto(View v){
        editIdRol.setText("");
        editDescripcion.setText("");
        editNumCrud.setText("");
    }
}