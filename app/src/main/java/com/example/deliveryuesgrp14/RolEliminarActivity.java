package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RolEliminarActivity extends AppCompatActivity {
    EditText editIdRol;
    ControlBDG14 controlhelper;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//rolEliminar.php";
    private final String urlLocal = "http://192.168.1.3/ServicePDM/rolEliminar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_eliminar);
        controlhelper=new ControlBDG14 (this);
        editIdRol=(EditText)findViewById(R.id.editRol);
    }

    public void eliminarRol(View v){
        Integer regEliminadas;
        Rol role=new Rol();
        int codRole = Integer.parseInt(editIdRol.getText().toString());
        role.setIdRol(codRole);
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarRol(role);
        controlhelper.cerrar();
        if(regEliminadas > 0){
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_opcion="+codRole;
            urlW = urlWeb+"?cod_opcion="+codRole;
            String roleL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String roleW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  "registros eliminados: " + regEliminadas, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error en la eliminacion", Toast.LENGTH_SHORT).show();
        }
    }
}