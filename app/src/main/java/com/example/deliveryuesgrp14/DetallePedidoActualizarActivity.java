package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoActualizarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText codDetalle;
    EditText cantidadCompra;
    EditText cantidadProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_actualizar);
        helper = new ControlBDG14(this);
        codDetalle = (EditText) findViewById(R.id.codDetalle);
        cantidadCompra = (EditText) findViewById(R.id.editCantidadCompra);
        cantidadProducto = (EditText) findViewById(R.id.editCantidadProducto);
    }
    public void actualizarDetallePedido(View v) {
        DetallePedido detalle = new DetallePedido();
        detalle.setCodDetalle(Integer.parseInt(codDetalle.getText().toString()));
        detalle.setCantidadCompra(Integer.parseInt(cantidadCompra.getText().toString()));
        detalle.setCantidadProducto(Integer.parseInt(cantidadProducto.getText().toString()));
        helper.abrir();
        String estado = helper.actualizarDetallePedido(detalle);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        codDetalle.setText("");
        cantidadCompra.setText("");

    }
}