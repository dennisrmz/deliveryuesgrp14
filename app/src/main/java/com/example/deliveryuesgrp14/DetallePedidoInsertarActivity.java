package com.example.deliveryuesgrp14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetallePedidoInsertarActivity extends Activity {

    ControlBDG14 helper;
    Spinner CodPedido;
    Spinner codMenu;
    Spinner codProducto;
    EditText cantidadCompra;
    EditText candtidadProducto;
    ArrayList<String> listaMenus;
    ArrayList<String> listarProductos;
    ArrayList<String> listarPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_insertar);
        helper = new ControlBDG14(this);
        codProducto = (Spinner) findViewById(R.id.codProducto);
        CodPedido = (Spinner) findViewById(R.id.codPedido);
        codMenu = (Spinner) findViewById(R.id.codMenu);
        cantidadCompra = (EditText) findViewById(R.id.editCantidadCompra);
        candtidadProducto = (EditText) findViewById(R.id.editCantidadProducto);
        helper.abrir();
        listarProductos=helper.listarProductos();
        listaMenus=helper.listarMenus();
        listarPedidos=helper.listarPedidos();
        helper.cerrar();
        if(listarPedidos.isEmpty() == false && listaMenus.isEmpty() == false && listarProductos.isEmpty() == false){
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listarProductos);
            codProducto.setAdapter(adapter);
            ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaMenus);
            codMenu.setAdapter(adapter2);
            ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listarPedidos);
            CodPedido.setAdapter(adapter3);
            Log.i("Pedido vacio", "en el si: ");
        }else {
            Toast.makeText(this, "No se ha registrado un menu, un producto o un pedido", Toast.LENGTH_LONG).show();
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

    public void insertarDetallePedido(View v) {
        String pedidos = CodPedido.getSelectedItem().toString();
        int pedido =getid(pedidos);
        String productos = codProducto.getSelectedItem().toString();
        int producto =getid(productos);
        String menus = codMenu.getSelectedItem().toString();
        int menu =getid(menus);
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
    private int getid(String text) {
        int codigoM=0;
        Matcher encontrado = Pattern.compile("\\d+").matcher(text);
        while ( encontrado .find()) {
            codigoM = Integer.parseInt( encontrado .group());
        }
        return codigoM;
    }
    public void limpiarTexto(View v) {

        cantidadCompra.setText("");
        candtidadProducto.setText("");

    }
}