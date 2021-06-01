package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsuarioEliminarActivity extends AppCompatActivity {

    EditText editCorreo;
    ControlBDG14 controlhelper;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//usuarioEliminar.php";
    private final String urlLocal = "http://192.168.1.3/ServicePDM/usuarioEliminar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eliminar);
        controlhelper=new ControlBDG14 (this);
        editCorreo=(EditText)findViewById(R.id.editCorreo);
    }

    public void eliminarUsuario(View v){
        int regEliminadas;
        Usuario usuario=new Usuario();
        usuario.setCorreo(editCorreo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarUsuario(usuario);
        controlhelper.cerrar();
        if(regEliminadas > 0){
            String url = "";
            String urlW = "";
            url = urlLocal+"?email="+editCorreo.getText().toString();
            urlW = urlWeb+"?email="+editCorreo.getText().toString();
            String categoriaL = consumoWSG14.obtenerRespuestaPeticion(url, this);
            String categoriaW = consumoWSG14.obtenerRespuestaPeticion(urlW, this);
            Toast.makeText(this,  "registros eliminados: " + regEliminadas, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error en la eliminacion", Toast.LENGTH_SHORT).show();
        }
    }

}