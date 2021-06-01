package com.example.deliveryuesgrp14;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DetallePedidoConsultarActivity extends Activity {
    ControlBDG14 helper;
    EditText CodPedido;
    ListView listDetallePedio;

    static List<DetallePedido> listaDetallePedido;
    static List<String> nombreDetalle;
    ListView listViewDetallePedidos;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/detallePedido.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/detallePedido.php";
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_consultar);
        helper = new ControlBDG14(this);

        CodPedido = (EditText) findViewById(R.id.codPedido);
        listDetallePedio = (ListView) findViewById(R.id.listDetallePedido);

        listaDetallePedido = new ArrayList<DetallePedido>();
        nombreDetalle = new ArrayList<String>();
        listViewDetallePedidos = (ListView) findViewById(R.id.listView1);

    }

    public void consultarDetallePedido(View v) {

        helper.abrir();
        String pivote = CodPedido.getText().toString();
        int codPedidos = Integer.parseInt(pivote);

        ArrayList<String> detalle = helper.consultarDetallePedido(codPedidos);
        helper.cerrar();
        if(detalle.isEmpty())
            Toast.makeText(this, "Detalle de Pedido con codigo " + CodPedido.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            ArrayAdapter<String> detalles = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, detalle);
            listDetallePedio.setAdapter(detalles);;

        }
    }

    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        url = urlLocal;
        String detalle = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaDetallePedido.addAll(consumoWSG14.obtenerDetallePedidosExterno(detalle,this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        url = urlWeb;
        String detalle = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaDetallePedido.addAll(consumoWSG14.obtenerDetallePedidosExterno(detalle, this));
            actualizarListView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreDetalle.clear();
        for (int i = 0; i < listaDetallePedido.size(); i++) {
            dato = listaDetallePedido.get(i).getCodDetalle() + "    "
                    + listaDetallePedido.get(i).getCodPedido() +"    "
                    + listaDetallePedido.get(i).getCodMenu()+"  "
                    +listaDetallePedido.get(i).getCantidadCompra()+" "
                    +listaDetallePedido.get(i).getCantidadProducto();
            nombreDetalle.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreDetalle);
        listViewDetallePedidos.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<DetallePedido> conjuntoDetallePedido = new HashSet<DetallePedido>();
        conjuntoDetallePedido.addAll(listaDetallePedido);
        listaDetallePedido.clear();
        listaDetallePedido.addAll(conjuntoDetallePedido);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreDetalle);
        nombreDetalle.clear();
        nombreDetalle.addAll(conjuntoNombre);
    }



    public void limpiarTexto(View v) {
        CodPedido.setText("");
        listDetallePedio.setAdapter(null);


    }
}