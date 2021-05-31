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
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/detallePedido_update.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/detallePedido_update.php";
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
        int cod =Integer.parseInt(codDetalle.getText().toString());
        int cantCompra=Integer.parseInt(cantidadCompra.getText().toString());
        int cantProducto=Integer.parseInt(cantidadProducto.getText().toString());
        detalle.setCodDetalle(cod);
        detalle.setCantidadCompra(cantCompra);
        detalle.setCantidadProducto(cantProducto);
        helper.abrir();
        String estado = helper.actualizarDetallePedido(detalle);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(estado.equals(mensajeError)){
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_detalle="+cod+"&cantCompra="+cantCompra+"&cantProducto="+cantProducto;
            urlW = urlWeb+"?cod_detalle="+cod+"&cantCompra="+cantCompra+"&cantProducto="+cantProducto;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        codDetalle.setText("");
        cantidadCompra.setText("");

    }
}