package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class PedidoInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodPedido;
    EditText codRepar;
    EditText codUbicacion;
    EditText codCliente;
    EditText codLocal;
    EditText editTotal;
    EditText editComentario;
    EditText editEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_insertar);
        helper = new ControlBDG14(this);
        CodPedido= (EditText) findViewById(R.id.codPedido);
        codRepar = (EditText) findViewById(R.id.codRepar);
        codUbicacion= (EditText) findViewById(R.id.codUbicacion);
        codCliente = (EditText) findViewById(R.id.codCliente);
        codLocal = (EditText) findViewById(R.id.codLocal);
        editTotal = (EditText) findViewById(R.id.editTotal);
        editComentario = (EditText) findViewById(R.id.editComentario);
        editEstado = (EditText) findViewById(R.id.editEstado);
    }
    public void insertarPedido(View v) {
        int codPedido =(int)Integer.parseInt(CodPedido.getText().toString());
        int repartido =(int)Integer.parseInt(codRepar.getText().toString());
        int ubicacion =(int)Integer.parseInt(codUbicacion.getText().toString());
        int cliente =(int)Integer.parseInt(codCliente.getText().toString());
        int local =(int)Integer.parseInt(codLocal.getText().toString());
        float total=Float.valueOf(editTotal.getText().toString());
        String comentario=editComentario.getText().toString();
        int estado= (int)Integer.parseInt(editEstado.getText().toString());
        String regInsertados;
        Pedido pedido =new Pedido();
        pedido.setCodPedido(codPedido);
        pedido.setCodRepar(repartido);
        pedido.setCodUbicacion(ubicacion);
        pedido.setCodCliente(cliente);
        pedido.setCodLocal(local);
        pedido.setTotal(total);
        pedido.setComentarioPedido(comentario);
        pedido.setEstado(estado);

        helper.abrir();
        regInsertados=helper.insertarPedido(pedido);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        CodPedido.setText("");
        codRepar.setText("");
        codUbicacion.setText("");
        codCliente.setText("");
        codLocal.setText("");
        editTotal.setText("");
        editComentario.setText("");
        editEstado.setText("");

    }
}