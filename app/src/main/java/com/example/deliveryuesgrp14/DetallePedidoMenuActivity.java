package com.example.deliveryuesgrp14;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DetallePedidoMenuActivity extends ListActivity {
    String[] menu={"Insertar Detalle","Consultar Detalle","Eliminar Detalle", "Actualizar Detalle"};
    String[] activities={"DetallePedidoInsertarActivity","DetallePedidoConsultarActivity","DetallePedidoEliminarActivity", "DetallePedidoActualizarActivity"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];

        try{
            Class<?> clase=Class.forName("com.example.deliveryuesgrp14."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }


}