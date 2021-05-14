package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetallePedidoConsultarActivity extends Activity {
    ControlBDG14 helper;
    EditText CodPedido;
    ListView listDetallePedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_consultar);
        helper = new ControlBDG14(this);

        CodPedido = (EditText) findViewById(R.id.codPedido);
        listDetallePedio = (ListView) findViewById(R.id.listDetallePedido);

    }

    public void consultarDetallePedido(View v) {

        helper.abrir();
        String pivote = CodPedido.getText().toString();
        int codPedidos = Integer.parseInt(pivote);

        ArrayList<String> detalle = helper.consultarDetallePedido(codPedidos);
        helper.cerrar();
        if(detalle.isEmpty())
            Toast.makeText(this, "Detalle de Pedido con codigo " + CodPedido.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            ArrayAdapter<String> detalles = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, detalle);
            listDetallePedio.setAdapter(detalles);;

        }
    }

    public void limpiarTexto(View v) {
        CodPedido.setText("");
        listDetallePedio.setAdapter(null);


    }
}