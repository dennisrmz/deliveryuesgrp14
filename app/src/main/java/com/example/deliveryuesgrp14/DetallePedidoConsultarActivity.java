package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetallePedidoConsultarActivity extends Activity {
    ControlBDG14 helper;
    EditText CodPedido;
    EditText codProducto;
    EditText codMenu;
    EditText cantidadCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_consultar);
        helper = new ControlBDG14(this);
        codProducto = (EditText) findViewById(R.id.codProducto);
        CodPedido = (EditText) findViewById(R.id.codPedido);
        codMenu = (EditText) findViewById(R.id.codMenu);
        cantidadCompra = (EditText) findViewById(R.id.editCantidadCompra);

    }

    public void consultarDetallePedido(View v) {

        helper.abrir();

        DetallePedido detalle = helper.consultarDetallePedido(CodPedido.getText().toString());
        helper.cerrar();
        if(detalle == null)
            Toast.makeText(this, "Detalle de Pedido con codigo " + CodPedido.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            codProducto.setText(Integer.toString(detalle.getCodProducto()));
            codMenu.setText(Integer.toString(detalle.getCodMenu()));
            cantidadCompra.setText(Integer.toString(detalle.getCantidadCompra()));

        }
    }

    public void limpiarTexto(View v) {
        CodPedido.setText("");
        codProducto.setText("");
        codMenu.setText("");
        cantidadCompra.setText("");

    }
}