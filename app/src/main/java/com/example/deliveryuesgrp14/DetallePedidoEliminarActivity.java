package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoEliminarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText CodPedido;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/detallePedido_delete.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/detallePedido_delete.php";
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_eliminar);
        helper=new ControlBDG14(this);
        CodPedido=(EditText)findViewById(R.id.codPedido);
    }
    public void eliminarDetallePedido(View v){
        String regEliminadas;
        DetallePedido detalle= new DetallePedido();
        int cod =Integer.parseInt(CodPedido.getText().toString());
        detalle.setCodPedido(cod);
        helper.abrir();
        regEliminadas=helper.eliminarDetallePedido(detalle);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_detalle="+cod;
        urlW = urlWeb+"?cod_detalle="+cod;
        String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}