package com.example.deliveryuesgrp14;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuPrincipalActivity extends ListActivity {

    String[] menu_empleado = {"Tabla Producto", "Tabla Marcas","Clientes",
            "Pedidos","Menu Restaurante", "Combo Producto", "Categoria","Llena Base de Datos", "Cerrar Sesion" };

    String[] activities_empleado = {"ProductoMenuActivity", "MarcaMenuActivity","ClienteMenuActivity",
            "PedidoMenuActivity","MenuRestMenuActivity", "ComboProductoMenuActivity", "CategoriaMenuActivity"};

    String[] menu_propietario ={"Tabla Usuario" ,"Tabla Opciones Crud", "Cerrar Sesion"};

    String[] activities_propietario = { "UsuarioMenuActivity","RolMenuActivity"};

    int numRol = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        numRol = intent.getIntExtra("numRol", -1);

        Toast.makeText(this, "Num rol es:  " + numRol, Toast.LENGTH_LONG).show();
        if(numRol == 3){
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, menu_empleado));
        }else if(numRol == 12){
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, menu_propietario));
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue = "";
        if (numRol == 3) {
            Toast.makeText(this, "Pos : " + position, Toast.LENGTH_LONG).show();

            if(position == 8){

                Toast.makeText(this, "Cerrando Sesion", Toast.LENGTH_LONG).show();
                SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                try{
                    Class<?>
                            clase=Class.forName("com.example.deliveryuesgrp14.MainActivity");
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }else if(position == 7){
                //CODIGO PARA LLENAR BASE DE DATOS
                //            BDhelper.abrir();
                //            String tost=BDhelper.llenarBDRG14();
                //            BDhelper.cerrar();
                            Toast.makeText(this, "Llenar bd", Toast.LENGTH_SHORT).show();
            }else{
                nombreValue = activities_empleado[position];
                try{
                    Class<?>
                            clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }

        }else if(numRol == 12){

            if(position == 2){
                Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_LONG).show();
                SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                try{
                    Class<?>
                            clase=Class.forName("com.example.deliveryuesgrp14.MainActivity");
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }else{
                nombreValue = activities_propietario[position];
                try{
                    Class<?>
                            clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }

        }

    }
}