package com.example.deliveryuesgrp14;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PedidoInsertarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodPedido;
    EditText codRepar;
    EditText codUbicacion;
    EditText codCliente;
    Spinner codLocal;

    EditText editComentario;
    EditText editEstado;
    ArrayList<String> listaLocales;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/pedido_insert.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/pedido_insert.php";
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_insertar);
        helper = new ControlBDG14(this);
        CodPedido= (EditText) findViewById(R.id.codPedido);
        codRepar = (EditText) findViewById(R.id.codRepar);
        codUbicacion= (EditText) findViewById(R.id.codUbicacion);
        codCliente = (EditText) findViewById(R.id.codCliente);
        codLocal = (Spinner) findViewById(R.id.codLocal);

        editComentario = (EditText) findViewById(R.id.editComentario);
        editEstado = (EditText) findViewById(R.id.editEstado);
        helper.abrir();
        listaLocales=helper.listarLocales();
        helper.cerrar();
        if(listaLocales.isEmpty() == false){
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaLocales);
            codLocal.setAdapter(adapter);
        }else {
            Toast.makeText(this, "No se ha registrado un menu o un producto", Toast.LENGTH_LONG).show();
            Log.i("Pedido vacio", "en el no: ");
            try{
                Class<?>
                        clase=Class.forName("com.example.deliveryuesgrp14.MainActivity");
                Intent inte = new Intent(this,clase);

                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }
    public void insertarPedido(View v) {
        int codPedido =(int)Integer.parseInt(CodPedido.getText().toString());
        int repartido =(int)Integer.parseInt(codRepar.getText().toString());
        int ubicacion =(int)Integer.parseInt(codUbicacion.getText().toString());
        int cliente =(int)Integer.parseInt(codCliente.getText().toString());
        String text = codLocal.getSelectedItem().toString();
        int local = idCodmenu(text);

        String comentario=editComentario.getText().toString();
        int estado= (int)Integer.parseInt(editEstado.getText().toString());
        String regInsertados;
        Pedido pedido =new Pedido();
        pedido.setCodPedido(codPedido);
        pedido.setCodRepar(repartido);
        pedido.setCodUbicacion(ubicacion);
        pedido.setCodCliente(cliente);
        pedido.setCodLocal(local);
        pedido.setComentarioPedido(comentario);
        pedido.setEstado(estado);

        helper.abrir();
        regInsertados=helper.insertarPedido(pedido);
        helper.cerrar();
        String mensajeError = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        if(regInsertados.equals(mensajeError)){
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }else {
            String url = "";
            String urlW = "";
            url = urlLocal+"?cod_pedido="+codPedido+"&cod_cliente="+cliente+"&cod_local="+local+"&cod_ubicacion="+ubicacion+"&cod_repar="+repartido+"&total="+0+"&comentarios="+comentario+"&estado="+estado;
            urlW = urlWeb+"?cod_pedido="+codPedido+"&cod_cliente="+cliente+"&cod_local="+local+"&cod_ubicacion="+ubicacion+"&cod_repar="+repartido+"&total="+0+"&comentarios="+comentario+"&estado="+estado;
            String marcaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String marcaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  regInsertados, Toast.LENGTH_SHORT).show();
        }
    }



    private int idCodmenu(String text) {
        int codigoM=0;
        Matcher encontrado = Pattern.compile("\\d+").matcher(text);
        while ( encontrado .find()) {
            codigoM = Integer.parseInt( encontrado .group());
        }
        return codigoM;
    }
    public void limpiarTexto(View v) {
        CodPedido.setText("");
        codRepar.setText("");
        codUbicacion.setText("");
        codCliente.setText("");
        editComentario.setText("");
        editEstado.setText("");

    }
}