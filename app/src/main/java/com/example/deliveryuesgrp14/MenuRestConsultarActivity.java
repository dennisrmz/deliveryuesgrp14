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
public class MenuRestConsultarActivity extends AppCompatActivity {

    ControlBDG14 helper;
    EditText CodMenu;
    EditText editCodLocal;
    EditText editPrecioComb;
    EditText editDescripCombo;
    static List<MenuRest> listaMenus;
    static List<String> nombreMenu;
    ListView listViewMenus;
    private final String urlWeb = "https://pdmgrupo14.000webhostapp.com//menu.php";
    private final String urlLocal = "http://192.168.1.5/ServicePDM/menu.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rest_consultar);
        helper = new ControlBDG14(this);
        CodMenu = (EditText) findViewById(R.id.codMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editPrecioComb = (EditText) findViewById(R.id.editPrec);
        editDescripCombo = (EditText) findViewById(R.id.editDescripCombo);
        listaMenus = new ArrayList<MenuRest>();
        nombreMenu = new ArrayList<String>();
        listViewMenus = (ListView) findViewById(R.id.listView1);
    }
    public void consultarMenu(View v) {
        helper.abrir();
        MenuRest menuRest = helper.consultarMenu(CodMenu.getText().toString());
        helper.cerrar();
        if(menuRest == null)
            Toast.makeText(this, "Mennu con el codigo " +
                    CodMenu.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editCodLocal.setText(Integer.toString(menuRest.getCODLOCAL()));
            editPrecioComb.setText(Float.toString(menuRest.getPRECIOCOMBO()));
            editDescripCombo.setText(menuRest.getDESCRIPCIONCOMBO());

        }
    }
    public void servicioPHP(View v) {
        String url = "";
        // it was the first button
        url = urlLocal;
        listaMenus.clear();
        String menu = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaMenus.addAll(consumoWSG14.obtenerMenus(menu, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void servicioWEB(View v) {
        String url = "";
        // it was the first button
        url = urlWeb;
        listaMenus.clear();
        String menu = consumoWSG14.obtenerRespuestaPeticion(url, this);
        try {
            listaMenus.addAll(consumoWSG14.obtenerMenus(menu, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreMenu.clear();
        for (int i = 0; i < listaMenus.size(); i++) {
            dato = "Cod local:  "+ listaMenus.get(i).getCODLOCAL() + " Cod menu   "
                    + listaMenus.get(i).getCODMENU()+ " Descrip   "
                    + listaMenus.get(i).getDESCRIPCIONCOMBO()+ " Precio  "
                    + listaMenus.get(i).getPRECIOCOMBO();
            nombreMenu.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreMenu);
        listViewMenus.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<MenuRest> conjuntoMateria = new HashSet<MenuRest>();
        conjuntoMateria.addAll(listaMenus);
        listaMenus.clear();
        listaMenus.addAll(conjuntoMateria);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreMenu);
        nombreMenu.clear();
        nombreMenu.addAll(conjuntoNombre);
    }
    public void limpiarTexto(View v){
        CodMenu.setText("");
        editCodLocal.setText("");
        editPrecioComb.setText("");
        editDescripCombo.setText("");
        listViewMenus.setAdapter(null);
    }
}