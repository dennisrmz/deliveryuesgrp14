package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodPedido;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/pedido_delete.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/pedido_delete.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_eliminar);
        helper=new ControlBDG14(this);
        CodPedido=(EditText)findViewById(R.id.codPedido);
    }
    public void eliminarPedido(View v){
        String regEliminadas;
        Pedido pedido= new Pedido();
        int cod =Integer.parseInt(CodPedido.getText().toString());
        pedido.setCodPedido(cod);
        helper.abrir();
        regEliminadas=helper.eliminarPedido(pedido);
        helper.cerrar();
        String url = "";
        String urlW = "";
        url = urlLocal+"?cod_pedido="+cod;
        urlW = urlWeb+"?cod_pedido="+cod;
        String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
        String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}