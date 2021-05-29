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

    String[] menu_empleado = {"Producto", "Marcas","Clientes",
            "Pedidos","Menu Restaurante", "Combo Producto", "Categoria", "Cerrar Sesion" };


    String[] activities_empleado = {"ProductoMenuActivity", "MarcaMenuActivity","ClienteMenuActivity",
            "PedidoMenuActivity","MenuRestMenuActivity", "ComboProductoMenuActivity", "CategoriaMenuActivity"};


    String[] menu_cliente={"Pedidos",
                 "Detalle de Pedido",  "Cerrar Sesion"};
    String[] activities_cliente={"PedidoMenuActivity",
                 "DetallePedidoMenuActivity"};


    String[] menu_propietario ={"Producto", "Marcas","Clientes",
            "Pedidos","Menu Restaurante", "Combo Producto",
            "Detalle de Pedido",
            "Categoria", "Tabla Usuario" ,"Tabla Opciones Crud", "Cerrar Sesion"};

    String[] activities_propietario = { "ProductoMenuActivity", "MarcaMenuActivity","ClienteMenuActivity",
            "PedidoMenuActivity","MenuRestMenuActivity", "ComboProductoMenuActivity",
            "DetallePedidoMenuActivity",
            "CategoriaMenuActivity", "UsuarioMenuActivity", "RolMenuActivity" };

    String[] menu_repartidor = { "Pedidos",
            "Detalle de Pedido", "Cerrar Sesion" };


    String[] activities_repartidor = { "PedidoMenuActivity",
            "DetallePedidoMenuActivity"};


    String[] menu_admin = {"Producto", "Marcas","Clientes",
            "Pedidos","Menu Restaurante", "Combo Producto", 
            "Local", "Encargado Local","Detalle de Pedido",
            "Categoria", "Tabla Usuario" ,"Tabla Opciones Crud", 
            "Llena Base de Datos", "Cerrar Sesion" };

    String[] activities_admin = { "ProductoMenuActivity", "MarcaMenuActivity","ClienteMenuActivity",
            "PedidoMenuActivity","MenuRestMenuActivity", "ComboProductoMenuActivity",
            "LocalMenuActivity","EncargadoLocalMenuActivity","DetallePedidoMenuActivity",
            "CategoriaMenuActivity", "UsuarioMenuActivity", "RolMenuActivity" };

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
        }else if(numRol == 2){
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, menu_propietario));
        }else if(numRol == 5){
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, menu_admin));
        }else if(numRol == 1){
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, menu_cliente));
        }else if(numRol == 4){
            setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, menu_repartidor));
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue = "";
        if (numRol == 3) {
            Toast.makeText(this, "Pos : " + position, Toast.LENGTH_LONG).show();

            if(position == 7){

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

        }else if(numRol == 2){

            if(position == 10){
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

        }else if(numRol == 5){
            Toast.makeText(this, "Pos : " + position, Toast.LENGTH_LONG).show();
            if(position == 13){
                //Cerrar Sesion
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
            }else if(position == 12){
                //Aqui metodo llenar bd
                Toast.makeText(this, "Llenar BD", Toast.LENGTH_LONG).show();
            }
            else{
                nombreValue = activities_admin[position];
                try{
                    Class<?>
                            clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }

        }else if(numRol == 1){
            Toast.makeText(this, "Pos : " + position, Toast.LENGTH_LONG).show();
            if(position == 2){
                //Cerrar Sesion
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
            }
            else{
                nombreValue = activities_cliente[position];
                try{
                    Class<?>
                            clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
                    Intent inte = new Intent(this,clase);
                    this.startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }

        }else if(numRol == 4){
            Toast.makeText(this, "Pos : " + position, Toast.LENGTH_LONG).show();
            if(position == 2){
                //Cerrar Sesion
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
            }
            else{
                nombreValue = activities_repartidor[position];
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