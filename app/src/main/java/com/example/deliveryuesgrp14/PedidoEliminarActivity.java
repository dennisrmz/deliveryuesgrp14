package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoEliminarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodPedido;
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
        pedido.setCodPedido(Integer.parseInt(CodPedido.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminarPedido(pedido);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}