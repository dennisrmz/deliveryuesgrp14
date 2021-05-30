package com.example.deliveryuesgrp14;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class consumoWSG14 {
    public static String obtenerRespuestaPeticion(String url, Context ctx) {

        String respuesta = " ";

        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);

        try {
            Log.v("Intentando setiar",url );
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }

        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static String obtenerRespuestaPost(String url, JSONObject obj,
                                              Context ctx) {
        String respuesta = " ";
        try {
            HttpParams parametros = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(parametros, 3000);
            HttpConnectionParams.setSoTimeout(parametros, 5000);
            HttpClient cliente = new DefaultHttpClient(parametros);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("content-type", "application/json");
            StringEntity nuevaEntidad = new StringEntity(obj.toString());
            httpPost.setEntity(nuevaEntidad);
            Log.v("Peticion",url);
            Log.v("POST", httpPost.toString());
            HttpResponse httpRespuesta = cliente.execute(httpPost);
            StatusLine estado = httpRespuesta.getStatusLine();

            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                respuesta = Integer.toString(codigoEstado);
                Log.v("respuesta",respuesta);
            }
            else{
                Log.v("respuesta",Integer.toString(codigoEstado));
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static List<Marca> obtenerMateriasLocal(String json, Context ctx) {

        List<Marca> listaMaterias = new ArrayList<Marca>();

        try {
            JSONArray materiasJSON = new JSONArray(json);
            Toast.makeText(ctx, "Antes del for", Toast.LENGTH_LONG).show();
            for (int i = 0; i < materiasJSON.length(); i++) {
                JSONObject obj = materiasJSON.getJSONObject(i);
                Marca materia = new Marca();
                materia.setCodMarca(obj.getInt("CODMARCA"));
                materia.setNombreMarca(obj.getString("NOMBREMARCA"));

                listaMaterias.add(materia);
            }
            return listaMaterias;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseO de JSON", Toast.LENGTH_LONG)
                    .show();
            return null;
        }

    }
    public static List<Marca> obtenerMarcas(String json, Context ctx) {

        List<Marca> LismaMarcas = new ArrayList<Marca>();

        try {
            JSONArray materiasJSON = new JSONArray(json);
            for (int i = 0; i < materiasJSON.length(); i++) {
                JSONObject obj = materiasJSON.getJSONObject(i);
                Marca marca = new Marca();
                marca.setCodMarca(obj.getInt("cod"));
                marca.setNombreMarca(obj.getString("nombre"));
                LismaMarcas.add(marca);
            }
            return LismaMarcas;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG)
                    .show();
            return null;
        }
    }
    public static List<Producto> obtenerProductos(String json, Context ctx) {

        List<Producto> LisProducto = new ArrayList<Producto>();

        try {
            JSONArray materiasJSON = new JSONArray(json);
            for (int i = 0; i < materiasJSON.length(); i++) {
                JSONObject obj = materiasJSON.getJSONObject(i);
                Producto producto = new Producto();
                producto.setCodProduct(obj.getInt("cod"));
                producto.setCodMarca(obj.getInt("codMarca"));
                producto.setCodCategoria(obj.getInt("codCate"));
                producto.setNombreProducto(obj.getString("nombre"));
                producto.setDescripcionProd(obj.getString("descrip"));
                producto.setPrecio((float)obj.getDouble("precio"));
                producto.setExistencias(obj.getInt("existen"));
                LisProducto.add(producto);
            }
            return LisProducto;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG)
                    .show();
            return null;
        }
    }

    public static List<Cliente> obtenerClientesExterno(String json, Context ctx) {

        List<Cliente> listaClientes = new ArrayList<Cliente>();

        try {
            JSONArray clientesJSON = new JSONArray(json);
            for (int i = 0; i < clientesJSON.length(); i++) {
                JSONObject obj = clientesJSON.getJSONObject(i);
                Cliente cliente = new Cliente();
                cliente.setCodCliente(obj.getInt("cod_cliente"));
                cliente.setCodUsuario(obj.getInt("cod_usuario"));
                cliente.setNombreCliente(obj.getString("nombre_cliente"));
                cliente.setApellidoCliente(obj.getString("apellido_cliente"));
                cliente.setNumTelefono(obj.getString("numTel"));
                listaClientes.add(cliente);
            }
            return listaClientes;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG)
                    .show();
            return null;
        }
    }
    public static List<Pedido> obtenerPedidosExterno(String json, Context ctx) {

        List<Pedido> listaPedidos = new ArrayList<Pedido>();

        try {
            JSONArray pedidosJSON = new JSONArray(json);
            for (int i = 0; i < pedidosJSON.length(); i++) {
                JSONObject obj = pedidosJSON.getJSONObject(i);
                Pedido pedido = new Pedido();
                pedido.setCodPedido(obj.getInt("codPedido"));
                pedido.setCodCliente(obj.getInt("codCliente"));
                pedido.setCodLocal(obj.getInt("codLocal"));
                pedido.setCodUbicacion(obj.getInt("codUbicacion"));
                pedido.setCodRepar(obj.getInt("codRepar"));
                pedido.setTotal(obj.getInt("total"));
                pedido.setComentarioPedido(obj.getString("comentarios"));
                pedido.setEstado(obj.getInt("estado"));
                listaPedidos.add(pedido);
            }
            return listaPedidos;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG)
                    .show();
            return null;
        }
    }


//    public static void insertarNotaLocal(String url, JSONObject obj, Context ctx) {
//        String respuesta = obtenerRespuestaPost(url, obj, ctx);
//        try {
//            if(respuesta.equals("200"))
//                Toast.makeText(ctx, "Insercion Correcta", Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
//            Log.v("",respuesta);
//        } catch (Exception e) {
//            Toast.makeText(ctx, "Error en la respuesta JSON", Toast.LENGTH_LONG).show();
//        }
//    }
//    public static void insertarNotaExterno(String peticion, Context ctx) {
//
//        String json = obtenerRespuestaPeticion(peticion, ctx);
//        try {
//            JSONObject resultado = new JSONObject(json);
//
//            Toast.makeText(ctx, "Registro ingresado"+ resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG)
//                    .show();
//            int respuesta = resultado.getInt("resultado");
//            if (respuesta == 1)
//                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG)
//                        .show();
//            else
//                Toast.makeText(ctx, "Error registro duplicado",
//                        Toast.LENGTH_LONG).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//    public static String obtenerPromedioJSON(String json, Context ctx) {
//        try {
//            JSONArray objs = new JSONArray(json);
//            if (objs.length() != 0)
//                //NOTAFINAL PROMEDIO
//                return objs.getJSONObject(0).getString("PROMEDIO");
//            else {
//                Toast.makeText(ctx, "Error carnet no existe", Toast.LENGTH_LONG)
//                        .show();
//                return " ";
//            }
//        } catch (JSONException e) {
//            Toast.makeText(ctx, "Error con la respuesta JSON",
//                    Toast.LENGTH_LONG).show();
//            return " ";
//        }
//    }
}
