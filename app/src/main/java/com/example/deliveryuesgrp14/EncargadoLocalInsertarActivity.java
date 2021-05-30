package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoLocalInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText codEnca;
    EditText editCodUsua;
    EditText editNombreEnca;
    EditText editApeEnca;
    EditText editDuiEnca;
    EditText editTelfEnca;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/encargadoLocal.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/encargadoLocal.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_insertar);
        helper = new ControlBDG14(this);
        codEnca = (EditText) findViewById(R.id.codEncar);
        editCodUsua = (EditText) findViewById(R.id.editCodUsua);
        editNombreEnca = (EditText) findViewById(R.id.editNombreEnca);
        editApeEnca = (EditText) findViewById(R.id.editApeEnca);
        editDuiEnca = (EditText) findViewById(R.id.editDUIEnca);
        editTelfEnca = (EditText) findViewById(R.id.editNumbTelfEnca);
    }
    public void insertarEncargado(View v) {
        String pivote;
        pivote =  codEnca.getText().toString();
        int codEncar = Integer.parseInt(pivote);
        pivote =  editCodUsua.getText().toString();
        int codUsua = Integer.parseInt(pivote);
        String nombreEnca=editNombreEnca.getText().toString();
        String apeEnca=editApeEnca.getText().toString();
        String duiEnca=editDuiEnca.getText().toString();
        String telfEnca=editTelfEnca.getText().toString();
        String regInsertados;
        EncargadoLocal encargado = new EncargadoLocal();
        encargado.setCodEncar(codEncar);
        encargado.setCodUsuario(codUsua);
        encargado.setNombreEncar(nombreEnca);
        encargado.setApellidoEncar(apeEnca);
        encargado.setDuiEncar(duiEnca);
        encargado.setNumTelefono(telfEnca);
        helper.abrir();
        regInsertados=helper.insertarEncargado(encargado);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_encargado="+codEncar+"&cod_usuario="+codUsua+"&nombre="+nombreEnca+"&apellido="+apeEnca+"&dui="+duiEnca+"&numtelefono="+telfEnca;
            urlW = urlWeb+"?cod_encargado="+codEncar+"&cod_usuario="+codUsua+"&nombre="+nombreEnca+"&apellido="+apeEnca+"&dui="+duiEnca+"&numtelefono="+telfEnca;;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        codEnca.setText("");
        editCodUsua.setText("");
        editNombreEnca.setText("");
        editApeEnca.setText("");
        editDuiEnca.setText("");
        editTelfEnca.setText("");

    }
}
