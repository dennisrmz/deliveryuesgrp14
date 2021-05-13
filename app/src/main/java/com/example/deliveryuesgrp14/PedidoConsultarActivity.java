package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PedidoConsultarActivity extends Activity {

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
        setContentView(R.layout.activity_pedido_consultar);
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

    public void consultarPedido(View v) {

        helper.abrir();
        Pedido pedido = helper.consultarPedido(CodPedido.getText().toString());
        helper.cerrar();
        if(pedido == null)
            Toast.makeText(this, "Pedido con codigo " + CodPedido.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            Log.i("codRepar",Integer.toString(pedido.getCodRepar()));
            codRepar.setText(Integer.toString(pedido.getCodRepar()));
            codUbicacion.setText(Integer.toString(pedido.getCodUbicacion()));
            codCliente.setText(Integer.toString(pedido.getCodCliente()));
            codLocal.setText(Integer.toString(pedido.getCodLocal()));
            editTotal.setText(Float.toString(pedido.getTotal()));
            editComentario.setText(pedido.getComentarioPedido());
            editEstado.setText(Integer.toString(pedido.getEstado()));

        }
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