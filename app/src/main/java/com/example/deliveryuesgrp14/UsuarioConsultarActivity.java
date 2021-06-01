package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class UsuarioConsultarActivity extends AppCompatActivity {
    ControlBDG14 helper;
    EditText editCorreo;
    EditText editNombre;
    EditText editContrasena;
    EditText editRol;
    static List<Usuario> listaUsuarios;
    static List<String> nombreUsuarios;
    ListView listViewUsuarios;
    private final String urlLocal = "http://192.168.1.3/ServicePDM/usuarioConsultar.php";
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//usuarioConsultar.php";
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDG14(this);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
        editRol = (EditText) findViewById(R.id.editConsulRol);
        listaUsuarios = new ArrayList<Usuario>();
        nombreUsuarios = new ArrayList<String>();
        listViewUsuarios = (ListView) findViewById(R.id.listView1);
    }

    public void consultarUsuario(View v) {
        helper.abrir();
        Usuario usuario = helper.consultarUsuario(editCorreo.getText().toString());
        helper.cerrar();
        if(usuario == null)
            Toast.makeText(this, "Usuario con correo " + editCorreo.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            helper.abrir();
            String rol = helper.consultarRolUser(usuario.getCodUsuario());
            helper.cerrar();

            editCorreo.setText(usuario.getCorreo());
            editNombre.setText(usuario.getNombreUsu());
            editContrasena.setText(usuario.getContrasena());
            editRol.setText(rol);
        }
    }

    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        String cod_user = editCorreo.getText().toString();
        url = urlLocal;
        if(cod_user != ""){
            url = urlLocal + "?email_user=" + cod_user;
        }
        String user = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaUsuarios.addAll(consumoWSG14.obtenerUsuariosExterno(user, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        String cod_user = editCorreo.getText().toString();
        url = urlWeb;
        if(cod_user != ""){
            url = urlWeb + "?email_user=" + cod_user;
        }
        String user = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaUsuarios.addAll(consumoWSG14.obtenerUsuariosExterno(user, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListView() {
        String dato = "";
        nombreUsuarios.clear();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            dato = listaUsuarios.get(i).getCorreo() + "    "
                    + listaUsuarios.get(i).getNombreUsu();
            nombreUsuarios.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreUsuarios);
        listViewUsuarios.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<Usuario> conjuntoUsuario = new HashSet<Usuario>();
        conjuntoUsuario.addAll(listaUsuarios);
        listaUsuarios.clear();
        listaUsuarios.addAll(conjuntoUsuario);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreUsuarios);
        nombreUsuarios.clear();
        nombreUsuarios.addAll(conjuntoNombre);
    }

    public void limpiarTexto(View v){
        editCorreo.setText("");
        editNombre.setText("");
        editContrasena.setText("");
    }

}