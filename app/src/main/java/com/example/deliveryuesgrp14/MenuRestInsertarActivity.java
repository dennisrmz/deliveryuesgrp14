package com.example.deliveryuesgrp14;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuppressLint("NewApi")
public class MenuRestInsertarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMenu;

    EditText editPrecioComb;
    EditText editDescripCombo;
    Spinner comboLocal;
    ArrayList<String> listaLocales;

    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//insertarMenu.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/insertarMenu.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rest_insertar);
        helper = new ControlBDG14(this);
        CodMenu = (EditText) findViewById(R.id.codMenu);

        editPrecioComb = (EditText) findViewById(R.id.editPrec);
        editDescripCombo = (EditText) findViewById(R.id.editDescripCombo);
        comboLocal = (Spinner) findViewById(R.id.comboLocales);


        helper.abrir();
        listaLocales=helper.listarLocales();
        helper.cerrar();
        if(listaLocales.isEmpty() == false){
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaLocales);
            comboLocal.setAdapter(adapter);
        }else {
            Toast.makeText(this, "No se ha registrado un local", Toast.LENGTH_LONG).show();
            Log.i("Pedido vacio", "en el no: ");
            try{
                Class<?>
                        clase=Class.forName("com.example.deliveryuesgrp14.MainActivity");
                Intent inte = new Intent(this,clase);

                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }



    public void insertarMenu(View v) {
        String pivote;
        pivote =  CodMenu.getText().toString();
        int codMenu = Integer.parseInt(pivote);
        pivote =  editPrecioComb.getText().toString();
        float precioComb = Float.parseFloat(pivote);
        String descripcionComb = editDescripCombo.getText().toString();
        String text = comboLocal.getSelectedItem().toString();
        int codLocal = idCodmenu(text);
        String regInsertados;
        MenuRest menuRest = new MenuRest();
        menuRest.setCODMENU(codMenu);
        menuRest.setCODLOCAL(codLocal);
        menuRest.setPRECIOCOMBO(precioComb);
        menuRest.setDESCRIPCIONCOMBO(descripcionComb);


        helper.abrir();
        regInsertados=helper.insertarMenu(menuRest);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_menu="+codMenu+"&cod_local="+codLocal+"&descripcion="+descripcionComb+"&precio="+precioComb;
            urlW = urlWeb+"?cod_menu="+codMenu+"&cod_local="+codLocal+"&descripcion="+descripcionComb+"&precio="+precioComb;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    private int idCodmenu(String text) {
        int codigoM=0;
        Matcher encontrado = Pattern.compile("\\d+").matcher(text);
        while ( encontrado .find()) {
            codigoM = Integer.parseInt( encontrado .group());
        }
        return codigoM;
    }

    public void limpiarTexto(View v) {
        CodMenu.setText("");
        editPrecioComb.setText("");
        editDescripCombo.setText("");

    }
}