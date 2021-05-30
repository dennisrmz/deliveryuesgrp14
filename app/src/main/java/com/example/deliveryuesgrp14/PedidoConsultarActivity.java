package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@SuppressLint("NewApi")
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

    static List<Pedido> listaPedidos;
    static List<String> nombrePedidos;
    ListView listViewPedidos;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/pedido.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/pedido.php";
    @SuppressLint("NewApi")

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
        listaPedidos = new ArrayList<Pedido>();
        nombrePedidos = new ArrayList<String>();
        listViewPedidos = (ListView) findViewById(R.id.listView1);
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

    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        url = urlLocal;
        String pedido = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaPedidos.addAll(consumoWSG14.obtenerPedidosExterno(pedido,this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        url = urlWeb;
        String pedido = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaPedidos.addAll(consumoWSG14.obtenerPedidosExterno(pedido, this));
            actualizarListView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombrePedidos.clear();
        for (int i = 0; i < listaPedidos.size(); i++) {
            dato = listaPedidos.get(i).getCodPedido() + "    "
                    + listaPedidos.get(i).getCodCliente() +"    "
                    + listaPedidos.get(i).getCodLocal()+"  "
                    +listaPedidos.get(i).getCodUbicacion()+"  "
                    + listaPedidos.get(i).getCodRepar()+"  "
                    + listaPedidos.get(i).getTotal()+"  "
                    + listaPedidos.get(i).getComentarioPedido()+"  "
                    + listaPedidos.get(i).getEstado();

            nombrePedidos.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombrePedidos);
        listViewPedidos.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Pedido> conjuntoPedido = new HashSet<Pedido>();
        conjuntoPedido.addAll(listaPedidos);
        listaPedidos.clear();
        listaPedidos.addAll(conjuntoPedido);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombrePedidos);
        nombrePedidos.clear();
        nombrePedidos.addAll(conjuntoNombre);
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