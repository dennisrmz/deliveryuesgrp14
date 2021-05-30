package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
public class RolConsultarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editIdRol;
    EditText editDescripcion;
    EditText editNumCrud;
    static List<Rol> listaRoles;
    static List<String> nombreRoles;
    ListView listViewRoles;
    private final String urlLocal = "http://192.168.1.3/ServicePDM/rolConsultar.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDG14(this);
        editIdRol = (EditText) findViewById(R.id.editRol);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNumCrud = (EditText) findViewById(R.id.editNumber);
        listaRoles = new ArrayList<Rol>();
        nombreRoles = new ArrayList<String>();
        listViewRoles = (ListView) findViewById(R.id.listView1);
    }

    public void consultarRol(View v) {
        helper.abrir();
        Rol role = helper.consultarRol(editIdRol.getText().toString());
        helper.cerrar();
        if(role == null)
            Toast.makeText(this, "Usuario con correo " + editIdRol.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editIdRol.setText(Integer.toString(role.getIdRol()));
            editDescripcion.setText(role.getDescripcion());
            editNumCrud.setText(Integer.toString(role.getNum()));
        }
    }

    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        String cod_role = editIdRol.getText().toString();
        url = urlLocal;
        Log.v("COD_ROLE", cod_role);
        if(!cod_role.trim().isEmpty()){
            url = urlLocal + "?cod_opcion=" + cod_role;
        }
        Log.v("URL_PETICION",url);
        String role = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaRoles.addAll(consumoWSG14.obtenerRolesExterno(role, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListView() {
        String dato = "";
        nombreRoles.clear();
        for (int i = 0; i < listaRoles.size(); i++) {
            dato = listaRoles.get(i).getIdRol() + "    " + listaRoles.get(i).getDescripcion();
            nombreRoles.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreRoles);
        listViewRoles.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<Rol> conjuntoMateria = new HashSet<Rol>();
        conjuntoMateria.addAll(listaRoles);
        listaRoles.clear();
        listaRoles.addAll(conjuntoMateria);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreRoles);
        nombreRoles.clear();
        nombreRoles.addAll(conjuntoNombre);
    }

    public void limpiarTexto(View v){
        editIdRol.setText("");
        editDescripcion.setText("");
        editNumCrud.setText("");
    }
}