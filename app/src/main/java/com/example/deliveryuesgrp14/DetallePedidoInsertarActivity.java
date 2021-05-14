package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodPedido;
    EditText codProducto;
    EditText codMenu;
    EditText cantidadCompra;
    EditText candtidadProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_insertar);
        helper = new ControlBDG14(this);
        codProducto = (EditText) findViewById(R.id.codProducto);
        CodPedido = (EditText) findViewById(R.id.codPedido);
        codMenu = (EditText) findViewById(R.id.codMenu);
        cantidadCompra = (EditText) findViewById(R.id.editCantidadCompra);
        candtidadProducto = (EditText) findViewById(R.id.editCantidadProducto);

    }

    public void insertarDetallePedido(View v) {

        int pedido =(int)Integer.parseInt(CodPedido.getText().toString());
        int producto =(int)Integer.parseInt(codProducto.getText().toString());
        int menu =(int)Integer.parseInt(codMenu.getText().toString());
        int canCompra =(int)Integer.parseInt(cantidadCompra.getText().toString());
        int canProducto =(int)Integer.parseInt(candtidadProducto.getText().toString());

        String regInsertados;
        DetallePedido detalle =new DetallePedido();
        detalle.setCodPedido(pedido);
        detalle.setCodProducto(producto);
        detalle.setCodMenu(menu);
        detalle.setCantidadCompra(canCompra);
        detalle.setCantidadProducto(canProducto);

        helper.abrir();
        regInsertados=helper.insertarDetallePedido(detalle);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        CodPedido.setText("");
        codProducto.setText("");
        codMenu.setText("");
        cantidadCompra.setText("");
        candtidadProducto.setText("");

    }
}