package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RolInsertarActivity extends Activity {
    ControlBDG14 helper;
    EditText editRol;
    EditText editDescripcion;
    int numGlobal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_insertar);
        helper = new ControlBDG14(this);
        editRol = (EditText) findViewById(R.id.editRol);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
    }

    public void insertarRol(View v) {
        int idrol = Integer.parseInt(editRol.getText().toString());
        String descripcion = editDescripcion.getText().toString();
        String regInsertados;
        Rol role = new Rol();
        role.setIdRol(idrol);
        role.setDescripcion(descripcion);
        role.setNum(numGlobal);
        helper.abrir();
        regInsertados = helper.insertarRol(role);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editRol.setText("");
        editDescripcion.setText("");
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.num1:
                if(checked)
                   numGlobal = 1;
                break;
            case R.id.num2:
                if(checked)
                   numGlobal = 2;
                break;
            case R.id.num3:
                if(checked)
                    numGlobal = 3;
                break;
            case R.id.num4:
                if(checked)
                    numGlobal = 4;
                break;
        }
        Toast.makeText(getApplicationContext(), "Eligio : " + numGlobal , Toast.LENGTH_SHORT).show();
    }
}