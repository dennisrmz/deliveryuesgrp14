package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] menu={"Login" ,"LLenar Basede Datos"};
    String[] activities={"MenuPrincipalActivity" };

    EditText editCorreo;
    EditText editPassword;
    ControlBDG14 BDhelper;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    public static final String Pass = "passKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, menu));
        BDhelper=new ControlBDG14(this);
        //crear usuario por defecto
        validate_user_admin();

        SharedPreferences sh = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String pass_get = "";
        String email_get = "";
        if(sh != null){
            email_get = sh.getString("emailKey", "");
            pass_get = sh.getString("passKey", "");
            if(email_get != "" && pass_get != ""){
                BDhelper.abrir();
                Usuario user = BDhelper.consultarUsuarioLog(email_get,pass_get);
                BDhelper.cerrar();
                if(user != null){
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Pass,user.getContrasena());
                    editor.putString(Email, user.getCorreo());
                    editor.commit();

                    Toast.makeText(this, "Sesion Iniciada", Toast.LENGTH_LONG).show();
                    //Mandare a traer el num del rol
                    BDhelper.abrir();
                    int numRol = BDhelper.consultarRolUserNum(user.getCodUsuario());
                    BDhelper.cerrar();
                    try{
                        Class<?>
                                clase=Class.forName("com.example.deliveryuesgrp14.MenuPrincipalActivity");
                        Intent inte = new Intent(this,clase);
                        inte.putExtra("numRol", numRol);
                        this.startActivity(inte);
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(this, "Usuario con correo " + editCorreo.getText().toString() +
                            " no encontrado", Toast.LENGTH_LONG).show();
                }
            }
        }

        editCorreo=(EditText)findViewById(R.id.email);
        editPassword=(EditText)findViewById(R.id.password);

        editCorreo.setText(email_get, TextView.BufferType.EDITABLE);
        editPassword.setText(pass_get, TextView.BufferType.EDITABLE);
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        if(position!=3){
//
//            String nombreValue=activities[position];
//
//            try{
//                Class<?>
//                        clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
//                Intent inte = new Intent(this,clase);
//                this.startActivity(inte);
//            }catch(ClassNotFoundException e){
//                e.printStackTrace();
//            }
//        }else{
//
//            //CODIGO PARA LLENAR BASE DE DATOS
//            BDhelper.abrir();
//            String tost=BDhelper.llenarBDRG14();
//            BDhelper.cerrar();
//            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
//
//            String nombreValue=activities[0];
//            try{
//                Class<?>
//                        clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
//                Intent inte = new Intent(this,clase);
//                this.startActivity(inte);
//            }catch(ClassNotFoundException e){
//                e.printStackTrace();
//            }
//
//
//        }
//    }

    public void inicarSesion(View view) {
        BDhelper.abrir();
        Usuario user = BDhelper.consultarUsuarioLog(editCorreo.getText().toString(),editPassword.getText().toString());
        BDhelper.cerrar();
        if(user != null){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Pass,user.getContrasena());
            editor.putString(Email, user.getCorreo());
            editor.commit();

            Toast.makeText(this, "Sesion Iniciada", Toast.LENGTH_LONG).show();
            //Mandare a traer el num del rol
            BDhelper.abrir();
            int numRol = BDhelper.consultarRolUserNum(user.getCodUsuario());
            BDhelper.cerrar();
            try{
                Class<?>
                        clase=Class.forName("com.example.deliveryuesgrp14.MenuPrincipalActivity");
                Intent inte = new Intent(this,clase);
                inte.putExtra("numRol", numRol);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Usuario con correo " + editCorreo.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        }

    }

    public void validate_user_admin(){
        BDhelper.abrir();
        Rol role = BDhelper.consultarRol("1");
        BDhelper.cerrar();

        if(role == null){
            Rol newrole = new Rol();
            newrole.setIdRol(1);
            newrole.setDescripcion("admin");
            newrole.setNum(5);
            BDhelper.abrir();
            String roleInsert = BDhelper.insertarRol(newrole);
            BDhelper.cerrar();
        }

        BDhelper.abrir();
        Usuario user = BDhelper.consultarUsuario("admin@gmail.com");
        BDhelper.cerrar();
        if(user == null){
            Usuario usuario = new Usuario();
            usuario.setCorreo("admin@gmail.com");
            usuario.setNombreUsu("admin");
            usuario.setContrasena("1234");
            BDhelper.abrir();
            long respuesta = BDhelper.insertarUsuario(usuario, "1");
            BDhelper.cerrar();
        }
    }
}