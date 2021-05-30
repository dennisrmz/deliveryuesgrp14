package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText codLocal;
    EditText editCodEnc;
    EditText editNombrelocal;
    EditText editNumEmpl;
    EditText editDescripcionLoc;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/local.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/local.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        helper = new ControlBDG14(this);
        codLocal = (EditText) findViewById(R.id.codLocal);
        editCodEnc = (EditText) findViewById(R.id.editcodEncar);
        editNombrelocal = (EditText) findViewById(R.id.editNombrelocal);
        editNumEmpl = (EditText) findViewById(R.id.editNumEmpl);
        editDescripcionLoc = (EditText) findViewById(R.id.editDescripcionloc);

    }
    public void insertarLocal(View v) {
        String pivote;
        pivote =  codLocal.getText().toString();
        int codLocal = Integer.parseInt(pivote);
        pivote =  editCodEnc.getText().toString();
        int codEnca = Integer.parseInt(pivote);
        pivote =  editNumEmpl.getText().toString();
        int numEmpl = Integer.parseInt(pivote);
        String nombreLocal=editNombrelocal.getText().toString();
        String descripcionLocal=editDescripcionLoc.getText().toString();
        String regInsertados;
        Local local = new Local();
        local.setCodLocal(codLocal);
        local.setCodEncar(codEnca);
        local.setNombreLocal(nombreLocal);
        local.setNumEmpleados(numEmpl);
        local.setDescripLocal(descripcionLocal);
        helper.abrir();
        regInsertados=helper.insertarLocal(local);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_local="+codLocal+"&cod_encargado="+codEnca+"&nombre="+nombreLocal+"&numeros_empleados="+numEmpl+"&descripcion="+descripcionLocal;
            urlW = urlWeb+"?cod_local="+codLocal+"&cod_encargado="+codEnca+"&nombre="+nombreLocal+"&numeros_empleados="+numEmpl+"&descripcion="+descripcionLocal;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        codLocal.setText("");
        editCodEnc.setText("");
        editNombrelocal.setText("");
        editNumEmpl.setText("");
        editDescripcionLoc.setText("");

    }
}