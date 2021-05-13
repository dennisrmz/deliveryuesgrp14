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
        pedido.setCodPedido(Integer.parseInt(CodPedido.getText().toString()));
        pedido.setCodRepar(Integer.parseInt(codRepar.getText().toString()));
        pedido.setCodUbicacion(Integer.parseInt(codUbicacion.getText().toString()));

        pedido.setComentarioPedido(editComentario.getText().toString());
        pedido.setEstado(Integer.parseInt(editEstado.getText().toString()));
        helper.abrir();
        String estado = helper.actualizarPedido(pedido);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        CodPedido.setText("");
        codRepar.setText("");
        codUbicacion.setText("");

        editComentario.setText("");
        editEstado.setText("");

    }

}