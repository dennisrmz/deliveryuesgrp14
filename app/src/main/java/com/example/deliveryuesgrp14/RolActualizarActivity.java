package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RolActualizarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editCodRol;
    EditText editDescripcion;
    int numGlobal = 0;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//rolActualizar.php";
    private final String urlLocal = "http://192.168.1.3/ServicePDM/rolActualizar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_actualizar);

        helper = new ControlBDG14(this);
        editCodRol = (EditText) findViewById(R.id.editRol);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);

    }

    public void actualizarRol(View v) {
        Rol role = new Rol();
        int idrol = Integer.parseInt(editCodRol.getText().toString());
        String descripcion = editDescripcion.getText().toString();
        role.setIdRol(Integer.parseInt(editCodRol.getText().toString()));
        role.setDescripcion(editDescripcion.getText().toString());
        role.setNum(numGlobal);
        helper.abrir();
        String estado = helper.actualizarRol(role);
        helper.cerrar();
        String mensajeError = "Error al actualizar, registro no existe";
        if(estado.equals(mensajeError)){
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_opcion="+idrol+"&nombre_opcioncrud="+descripcion+"&num_opcion="+numGlobal;
            urlW = urlWeb+"?cod_opcion="+idrol+"&nombre_opcioncrud="+descripcion+"&num_opcion="+numGlobal;
            String categoriaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String categoriaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }
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