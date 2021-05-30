package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ClienteConsultarActivity extends Activity {

    ControlBDG14 helper;
    EditText CodCliente;
    EditText editNombre;
    EditText editApellido;
    EditText editTelefono;

    static List<Cliente> listaClientes;
    static List<String> nombreClientes;
    ListView listViewClientes;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com/cliente.php";
    private final String urlLocal = "http://192.168.1.2/ServicePDM/cliente.php";
    @SuppressLint("NewApi")
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        helper = new ControlBDG14(this);

        CodCliente = (EditText) findViewById(R.id.codCliente);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTelefono = (EditText) findViewById(R.id.editTelefono);
        listaClientes = new ArrayList<Cliente>();
        nombreClientes = new ArrayList<String>();
        listViewClientes = (ListView) findViewById(R.id.listView1);


    }
    public void consultarCliente(View v) {

        helper.abrir();
        Cliente cliente = helper.ConsultarCliente(CodCliente.getText().toString());
        helper.cerrar();
        if(cliente == null)
            Toast.makeText(this, "Cliente con codigo " + CodCliente.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombre.setText(cliente.getNombreCliente());
            editApellido.setText(cliente.getApellidoCliente());
            editTelefono.setText(cliente.getNumTelefono());

        }
    }
    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        url = urlLocal;
        String cliente = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaClientes.addAll(consumoWSG14.obtenerClientesExterno(cliente,this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        url = urlWeb;
        String cliente = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaClientes.addAll(consumoWSG14.obtenerClientesExterno(cliente, this));
            actualizarListView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreClientes.clear();
        for (int i = 0; i < listaClientes.size(); i++) {
            dato = listaClientes.get(i).getCodCliente() + "    "
                    + listaClientes.get(i).getNombreCliente() +"    "
                    + listaClientes.get(i).getApellidoCliente()+"  "
                    +listaClientes.get(i).getNumTelefono();
            nombreClientes.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreClientes);
        listViewClientes.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Cliente> conjuntoCliente = new HashSet<Cliente>();
        conjuntoCliente.addAll(listaClientes);
        listaClientes.clear();
        listaClientes.addAll(conjuntoCliente);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreClientes);
        nombreClientes.clear();
        nombreClientes.addAll(conjuntoNombre);
    }



    public void limpiarTexto(View v) {
        CodCliente.setText("");

        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
    }
}