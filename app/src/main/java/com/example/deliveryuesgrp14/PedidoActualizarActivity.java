package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoActualizarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodPedido;
    EditText codRepar;
    EditText codUbicacion;

    EditText editComentario;
    EditText editEstado;

    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/pedido_update.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/pedido_update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_actualizar);
        helper = new ControlBDG14(this);
        CodPedido= (EditText) findViewById(R.id.codPedido);
        codRepar = (EditText) findViewById(R.id.codRepar);
        codUbicacion= (EditText) findViewById(R.id.codUbicacion);

        editComentario = (EditText) findViewById(R.id.editComentario);
        editEstado = (EditText) findViewById(R.id.editEstado);
    }
    public void actualizarPedido(View v) {
        Pedido pedido = new Pedido();
        int codPedido=Integer.parseInt(CodPedido.getText().toString());
        int repartido=Integer.parseInt(codRepar.getText().toString());
        int ubicacion=Integer.parseInt(codUbicacion.getText().toString());
        String comentario=editComentario.getText().toString();
        int estado1=Integer.parseInt(editEstado.getText().toString());
        pedido.setCodPedido(codPedido);
        pedido.setCodRepar(repartido);
        pedido.setCodUbicacion(ubicacion);
        pedido.setComentarioPedido(comentario);
        pedido.setEstado(estado1);

        helper.abrir();
        String estado = helper.actualizarPedido(pedido);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(estado.equals(mensajeError)){
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_pedido="+codPedido+"&cod_ubicacion="+ubicacion+"&cod_repar="+repartido+"&comentarios="+comentario+"&estado="+estado1;
            urlW = urlWeb+"?cod_pedido="+codPedido+"&cod_ubicacion="+ubicacion+"&cod_repar="+repartido+"&comentarios="+comentario+"&estado="+estado1;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  estado, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        CodPedido.setText("");
        codRepar.setText("");
        codUbicacion.setText("");

        editComentario.setText("");
        editEstado.setText("");

    }

}