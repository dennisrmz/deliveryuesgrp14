package com.example.deliveryuesgrp14;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import androidx.annotation.MenuRes;


public class ControlBDG14 {

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBDG14(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }
    private static final String[]camposProducto = new String []
            {"CODPRODUCT","CODCATEGORIA" ,"CODMARCA","NOMBREPRODUCTO","DESCRIPCIONPROD", "EXISTENCIAS"};
    private static final String[]camposMarca = new String []
            {"CODMARCA","NOMBREMARCA"};
    private static final String[]camposCliente = new String [] {"CODCLIENTE","CODUSUARIO","NOMBRECLIENTE","APELLIDOCLIENTE", "NUMTELEFONO"};
    private static final String[]camposPedido = new String [] {"CODPEDIDO","CODREPAR","CODUBICACION","CODCLIENTE","CODLOCAL","TOTAL","COMENTARIOPEDIDO", "ESTADO"};
    private static final String[]camposMenu = new String []
            {"CODMENU","CODLOCAL" ,"PRECIOCOMBO","DESCRIPCIONCOMBO"};


  
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "sistemaCafetines.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: ACCESOUSUARIO                                         */\n" +
                        "/*==============================================================*/\n" +
                        "create table ACCESOUSUARIO \n" +
                        "(\n" +
                        "   CODUSUARIO           INTEGER              not null,\n" +
                        "   CODOPCION            INTEGER              not null,\n" +
                        "   constraint PK_ACCESOUSUARIO primary key (CODUSUARIO, CODOPCION)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: CAMBOPRODUCTO                                         */\n" +
                        "/*==============================================================*/\n" +
                        "create table COMBOPRODUCTO \n" +
                        "(\n" +
                        "   CODPRODUCT           INTEGER              not null,\n" +
                        "   CODMENU              INTEGER              not null,\n" +
                        "   CANTPRODUCT           INTEGER              not null,\n" +
                        "   constraint PK_CAMBOPRODUCTO primary key (CODPRODUCT, CODMENU)\n" +
                        ");");
                db.execSQL(
                        "/*==============================================================*/\n" +
                                "/* Table: CATEGORIA                                             */\n" +
                                "/*==============================================================*/\n" +
                                "create table CATEGORIA \n" +
                                "(\n" +
                                "   CODCATEGORIA         INTEGER              not null,\n" +
                                "   NOMBRECATEGORIA      CHAR(256)            not null,\n" +
                                "   constraint PK_CATEGORIA primary key (CODCATEGORIA)\n" +
                                ");");
                db.execSQL("\n" +
                        "/*==============================================================*/\n" +
                        "/* Table: CLIENTE                                               */\n" +
                        "/*==============================================================*/\n" +
                        "create table CLIENTE \n" +
                        "(\n" +
                        "   CODCLIENTE           INTEGER              not null,\n" +
                        "   CODUSUARIO           INTEGER,\n" +
                        "   NOMBRECLIENTE        CHAR(250)            not null,\n" +
                        "   APELLIDOCLIENTE      CHAR(250)            not null,\n" +
                        "   NUMTELEFONO          CHAR(250)            not null,\n" +
                        "   constraint PK_CLIENTE primary key (CODCLIENTE)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: DETALLEPEDIDO                                         */\n" +
                        "/*==============================================================*/\n" +
                        "create table DETALLEPEDIDO \n" +
                        "(\n" +
                        "   CODPRODUCT           INTEGER,\n" +
                        "   CODPEDIDO            INTEGER              not null,\n" +
                        "   CODMENU              INTEGER,\n" +
                        "   CANTIDADCOMPRA       INTEGER              not null,\n" +
                        "   CANTIDADPRODUCTO       INTEGER              not null,\n" +
                        "   constraint PK_DETALLEPEDIDO primary key (CODPEDIDO)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: ENCARGADOLOCAL                                        */\n" +
                        "/*==============================================================*/\n" +
                        "create table ENCARGADOLOCAL \n" +
                        "(\n" +
                        "   CODENCAR             INTEGER              not null,\n" +
                        "   CODUSUARIO           INTEGER,\n" +
                        "   NOMBREENCAR          CHAR(250)            not null,\n" +
                        "   APELLIDOENCAR        CHAR(250)            not null,\n" +
                        "   DUIENCAR             CHAR(250)            not null,\n" +
                        "   NUMTELEFONO          CHAR(250)            not null,\n" +
                        "   constraint PK_ENCARGADOLOCAL primary key (CODENCAR)\n" +
                        ");");
                db.execSQL("\n" +
                        "/*==============================================================*/\n" +
                        "/* Table: FACULTAD                                              */\n" +
                        "/*==============================================================*/\n" +
                        "create table FACULTAD \n" +
                        "(\n" +
                        "   CODFACU              INTEGER              not null,\n" +
                        "   NOMBREFACU           CHAR(250)            not null,\n" +
                        "   constraint PK_FACULTAD primary key (CODFACU)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: LOCAL                                                 */\n" +
                        "/*==============================================================*/\n" +
                        "create table LOCAL \n" +
                        "(\n" +
                        "   CODLOCAL             INTEGER              not null,\n" +
                        "   CODENCAR             INTEGER,\n" +
                        "   NOMBRELOCAL          CHAR(250)            not null,\n" +
                        "   NUMEMPLEADOS         INTEGER              not null,\n" +
                        "   DESCRIPLOCAL         CHAR(256)            not null,\n" +
                        "   constraint PK_LOCAL primary key (CODLOCAL)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: MARCA                                                 */\n" +
                        "/*==============================================================*/\n" +
                        "create table MARCA\n" +
                        "(\n" +
                        "   CODMARCA             int not null,\n" +
                        "   NOMBREMARCA          char(256) not null,\n" +
                        "   primary key (CODMARCA)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: MENU                                                  */\n" +
                        "/*==============================================================*/\n" +
                        "create table MENU \n" +
                        "(\n" +
                        "   CODMENU              INTEGER              not null,\n" +
                        "   CODLOCAL             INTEGER,\n" +
                        "   PRECIOCOMBO          FLOAT                not null,\n" +
                        "   DESCRIPCIONCOMBO     CHAR(250)            not null,\n" +
                        "   constraint PK_MENU primary key (CODMENU)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: OPCIONCRUD                                            */\n" +
                        "/*==============================================================*/\n" +
                        "create table OPCIONCRUD \n" +
                        "(\n" +
                        "   CODOPCION            INTEGER              not null,\n" +
                        "   DESCRIPCIONCRUD      CHAR(256)            not null,\n" +
                        "   NUMCRUD              INTEGER              not null,\n" +
                        "   constraint PK_OPCIONCRUD primary key (CODOPCION)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: PEDIDO                                                */\n" +
                        "/*==============================================================*/\n" +
                        "create table PEDIDO \n" +
                        "(\n" +
                        "   CODPEDIDO            INTEGER              not null,\n" +
                        "   CODREPAR             INTEGER,\n" +
                        "   CODUBICACION         INTEGER,\n" +
                        "   CODCLIENTE           INTEGER,\n" +
                        "   CODLOCAL             INTEGER,\n" +
                        "   TOTAL                FLOAT                not null,\n" +
                        "   COMENTARIOPEDIDO     CHAR(250)            not null,\n" +
                        "   ESTADO               SMALLINT             not null,\n" +
                        "   constraint PK_PEDIDO primary key (CODPEDIDO)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: PRODUCTO                                              */\n" +
                        "/*==============================================================*/\n" +
                        "create table PRODUCTO\n" +
                        "(\n" +
                        "   CODPRODUCT           int not null,\n" +
                        "   CODCATEGORIA         int,\n" +
                        "   CODMARCA             int,\n" +
                        "   NOMBREPRODUCTO       char(250) not null,\n" +
                        "   DESCRIPCIONPROD      char(250) not null,\n" +
                        "   EXISTENCIAS          int not null,\n" +
                        "   primary key (CODPRODUCT)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: PRODUCTOPRECIO                                        */\n" +
                        "/*==============================================================*/\n" +
                        "create table PRODUCTOPRECIO \n" +
                        "(\n" +
                        "   CODPRODUCT           INTEGER              not null,\n" +
                        "   CODLOCAL             INTEGER              not null,\n" +
                        "   PRECIO               FLOAT                not null,\n" +
                        "   TIPOPRECIO           SMALLINT             not null,\n" +
                        "   constraint PK_PRODUCTOPRECIO primary key (CODPRODUCT, CODLOCAL)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: REPARTIDOR                                            */\n" +
                        "/*==============================================================*/\n" +
                        "create table REPARTIDOR \n" +
                        "(\n" +
                        "   CODREPAR             INTEGER              not null,\n" +
                        "   CODLOCAL             INTEGER,\n" +
                        "   CODUSUARIO           INTEGER,\n" +
                        "   NOMBREREPAR          CHAR(256)            not null,\n" +
                        "   APELLIDOREPAR        CHAR(256)            not null,\n" +
                        "   DUIREPAR             CHAR(256)            not null,\n" +
                        "   NUMTELREPAR          CHAR(256)            not null,\n" +
                        "   DIRECCIONREPAR       CHAR(256)            not null,\n" +
                        "   constraint PK_REPARTIDOR primary key (CODREPAR)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: TIPOUBICACION                                         */\n" +
                        "/*==============================================================*/\n" +
                        "create table TIPOUBICACION \n" +
                        "(\n" +
                        "   CODUBIC              INTEGER              not null,\n" +
                        "   NOMBREUBIC           CHAR(256)            not null,\n" +
                        "   constraint PK_TIPOUBICACION primary key (CODUBIC)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: UBICACION                                             */\n" +
                        "/*==============================================================*/\n" +
                        "create table UBICACION \n" +
                        "(\n" +
                        "   CODUBICACION         INTEGER              not null,\n" +
                        "   CODFACU              INTEGER,\n" +
                        "   CODUBIC              INTEGER,\n" +
                        "   CIUDAD               CHAR(250)            not null,\n" +
                        "   DEPARTAMENTO         CHAR(250)            not null,\n" +
                        "   MUNICIPIO            CHAR(250)            not null,\n" +
                        "   PUNTOREFE            CHAR(250)            not null,\n" +
                        "   constraint PK_UBICACION primary key (CODUBICACION)\n" +
                        ");");
                db.execSQL("/*==============================================================*/\n" +
                        "/* Table: USUARIO                                               */\n" +
                        "/*==============================================================*/\n" +
                        "create table USUARIO \n" +
                        "(\n" +
                        "   CODUSUARIO           INTEGER              not null,\n" +
                        "   CORREO               CHAR(256)            not null,\n" +
                        "   NOMBREUSU            CHAR(256)            not null,\n" +
                        "   CONTRASENA           CHAR(256)            not null,\n" +
                        "   constraint PK_USUARIO primary key (CODUSUARIO)\n" +
                        ");");

                db.execSQL("CREATE TRIGGER email_unique \n" +
                        "before insert on USUARIO\n" +
                        "FOR EACH ROW\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN((SELECT USUARIO.CORREO FROM USUARIO WHERE USUARIO.CORREO = NEW.CORREO ) IS NOT NULL)\n" +
                        "                THEN RAISE (ABORT, \"Ya existe usuario\")\n" +
                        "            END;\n" +
                        "END;");

                db.execSQL("CREATE TRIGGER validate_rol_cliente\n" +
                        "before insert on CLIENTE\n" +
                        "FOR EACH ROW\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN((SELECT USUARIO.CODUSUARIO FROM USUARIO \n" +
                        "                    INNER JOIN ACCESOUSUARIO ON USUARIO.CODUSUARIO = ACCESOUSUARIO.CODUSUARIO\n" +
                        "                    INNER JOIN OPCIONCRUD ON ACCESOUSUARIO.CODOPCION = OPCIONCRUD.CODOPCION\n" +
                        "                    WHERE OPCIONCRUD.NUMCRUD = 1\n" +
                        "                    AND USUARIO.CODUSUARIO = NEW.CODUSUARIO ) IS NULL)\n" +
                        "                THEN RAISE (ABORT, \"cod usuario no es cliente\")\n" +
                        "            END;\n" +
                        "END;\n");

                db.execSQL("CREATE TRIGGER validate_update_rol_cliente\n" +
                        "before update on CLIENTE\n" +
                        "FOR EACH ROW\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN((SELECT USUARIO.CODUSUARIO FROM USUARIO \n" +
                        "                    INNER JOIN ACCESOUSUARIO ON USUARIO.CODUSUARIO = ACCESOUSUARIO.CODUSUARIO\n" +
                        "                    INNER JOIN OPCIONCRUD ON ACCESOUSUARIO.CODOPCION = OPCIONCRUD.CODOPCION\n" +
                        "                    WHERE OPCIONCRUD.NUMCRUD = 1\n" +
                        "                    AND USUARIO.CODUSUARIO = NEW.CODUSUARIO ) IS NULL)\n" +
                        "                THEN RAISE (ABORT, \"cod usuario no es cliente\")\n" +
                        "            END;\n" +
                        "END;");

                db.execSQL("CREATE TRIGGER validate_marca\n" +
                        "before insert on PRODUCTO\n" +
                        "FOR EACH ROW\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN(( SELECT MARCA.CODMARCA FROM MARCA WHERE MARCA.CODMARCA = NEW.CODMARCA ) IS NULL)\n" +
                        "                THEN RAISE (ABORT, \"la marca no existe\")\n" +
                        "            END;\n" +
                        "END;");

                db.execSQL("CREATE TRIGGER validate_update_marca\n" +
                        "before update on PRODUCTO\n" +
                        "FOR EACH ROW\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN(( SELECT MARCA.CODMARCA FROM MARCA WHERE MARCA.CODMARCA = NEW.CODMARCA ) IS NULL)\n" +
                        "                THEN RAISE (ABORT, \"la marca no existe\")\n" +
                        "            END;\n" +
                        "END;");

                db.execSQL("CREATE TRIGGER validate_categoria\n" +
                        "before insert on PRODUCTO\n" +
                        "FOR EACH ROW\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN(( SELECT CATEGORIA.CODCATEGORIA FROM CATEGORIA WHERE CATEGORIA.CODCATEGORIA = NEW.CODCATEGORIA ) IS NULL)\n" +
                        "                THEN RAISE (ABORT, \"la categoria no existe\")\n" +
                        "            END;\n" +
                        "END;\n");

                db.execSQL("CREATE TRIGGER validate_update_categoria\n" +
                        "before update on PRODUCTO\n" +
                        "    BEGIN \n" +
                        "        SELECT CASE\n" +
                        "            WHEN(( SELECT CATEGORIA.CODCATEGORIA FROM CATEGORIA WHERE CATEGORIA.CODCATEGORIA = NEW.CODCATEGORIA ) IS NULL)\n" +
                        "                THEN RAISE (ABORT, \"la categoria no existe\")\n" +
                        "            END;\n" +
                        "END;");


                db.execSQL("INSERT INTO OPCIONCRUD (NUMCRUD,DESCRIPCIONCRUD,CODOPCION)\n" +
                        "       VALUES (3,'empleado',2),\n" +
                        "              (1,'cliente',3),\n" +
                        "              (2,'propietario',4),\n" +
                        "              (4,'repartidor',5);");

                db.execSQL("INSERT INTO USUARIO (CONTRASENA,NOMBREUSU,CORREO,CODUSUARIO)\n" +
                        "            VALUES (1234,'Dennis Orellana','repartidor@gmail.com',4),\n" +
                        "                   (1234,'Agustin Ascencio','propietario@gmail.com',3),\n" +
                        "                   (1234,'Kevin Gonzalo','cliente@gmail.com',2),\n" +
                        "                   (1234,'Mario Castro','empleado@gmail.com',5);");

                db.execSQL("INSERT INTO ACCESOUSUARIO (CODOPCION,CODUSUARIO)\n" +
                        "                  VALUES (2,5),\n" +
                        "                         (3,2),\n" +
                        "                         (4,3),\n" +
                        "                         (5,4);");


            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }
    public String insertarProducto(Producto producto){

        String regInsertados = "Registro Inserado Nº= ";
        long contador = 0;

         ContentValues prod = new ContentValues();
          prod.put("CODPRODUCT", producto.getCodProduct());
          prod.put("CODCATEGORIA",producto.getCodCategoria());
          prod.put("CODMARCA",producto.getCodMarca());
          prod.put("NOMBREPRODUCTO",producto.getNombreProducto());
          prod.put("DESCRIPCIONPROD", producto.getDescripcionProd());
          prod.put("EXISTENCIAS", producto.getExistencias());
          contador = db.insert("PRODUCTO",null,prod);
          if(contador==-1 || contador==0){
          regInsertados = "Error al insertar el registro, Registro dublicado. Verificar insercion";
         }
        else {
            regInsertados = regInsertados+contador;
        }
       return regInsertados;
        }

    public String insertarCliente(Cliente cliente){

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        ContentValues prod = new ContentValues();
        prod.put("CODCLIENTE", cliente.getCodCliente());
        prod.put("CODUSUARIO",cliente.getCodUsuario());
        prod.put("NOMBRECLIENTE",cliente.getNombreCliente());
        prod.put("APELLIDOCLIENTE",cliente.getApellidoCliente());
        prod.put("NUMTELEFONO", cliente.getNumTelefono());

        contador = db.insert("CLIENTE",null,prod);
        if(contador==-1 || contador==0){
                regInsertados = "Error al insertar el registro, Registro dublicado. Verificar insercion";
            }
            else {
                regInsertados = regInsertados+contador;
            }
            return regInsertados;
      }


    public Producto consultarProducto(String codProduct){
        String[] id = {codProduct};
        Cursor cursor = db.query("PRODUCTO", camposProducto, "CODPRODUCT = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Producto producto = new Producto();
            producto.setCodProduct(cursor.getInt(0));
            producto.setCodCategoria(cursor.getInt(1));
            producto.setCodMarca(cursor.getInt(2));
            producto.setNombreProducto(cursor.getString(3));
            producto.setDescripcionProd(cursor.getString(4));
            producto.setExistencias(cursor.getInt(5));
            return producto;
        }else{
            return null;
        }

    }
    public String actualizarProducto(Producto producto){
        long contador = 0;
        String[] id = {Integer.toString(producto.getCodProduct())};
        ContentValues cv = new ContentValues();
        cv.put("CODPRODUCT", producto.getCodProduct());
        cv.put("CODCATEGORIA",producto.getCodCategoria());
        cv.put("CODMARCA",producto.getCodMarca());
        cv.put("NOMBREPRODUCTO",producto.getNombreProducto());
        cv.put("DESCRIPCIONPROD", producto.getDescripcionProd());
        cv.put("EXISTENCIAS", producto.getExistencias());
        try{
            contador =  db.update("PRODUCTO", cv, "CODPRODUCT = ?", id);
        }catch(Exception e){
            contador = 0;
        }

        if(contador==-1 || contador==0){
            return "Error al insertar el registro, Registro dublicado. Verificar insercion";
        }
        else {
            return "Registro Actualizado Correctamente";
        }

    }

    public String eliminarProducto(Producto producto){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("PRODUCTO", "CODPRODUCT='"+producto.getCodProduct()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    ///******************************Marca*****************************
    public String insertarMarca(Marca marca ){

        String regInsertados = "Registro Inserado Nº= ";
        long contador = 0;

        ContentValues marc = new ContentValues();
        marc.put("CODMARCA", marca.getCodMarca());
        marc.put("NOMBREMARCA",marca.getNombreMarca());

        contador = db.insert("MARCA",null,marc);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }


    public Cliente ConsultarCliente(String codCliente){
        String[] id = {codCliente};
        Cursor cursor = db.query("CLIENTE", camposCliente, "CODCLIENTE = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Cliente cliente = new Cliente();
            cliente.setCodCliente(cursor.getInt(0));
            cliente.setCodUsuario(cursor.getInt(1));
            cliente.setNombreCliente(cursor.getString(2));
            cliente.setApellidoCliente(cursor.getString(3));
            cliente.setNumTelefono(cursor.getString(4));
            return cliente;
         }else{
            return null;
        }

    }
    public Marca consultarMarca(String codMarca){
        String[] id = {codMarca};
        Cursor cursor = db.query("MARCA", camposMarca, "CODMARCA = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Marca marca = new Marca();
            marca.setCodMarca(cursor.getInt(0));
            marca.setNombreMarca(cursor.getString(1));

            return marca;
        }else{
            return null;
        }

    }

    public String actualizarCliente(Cliente cliente){

            String[] id = {Integer.toString( cliente.getCodCliente())};
            ContentValues cv = new ContentValues();
            cv.put("NOMBRECLIENTE", cliente.getNombreCliente());
            cv.put("APELLIDOCLIENTE", cliente.getApellidoCliente());
            cv.put("NUMTELEFONO", cliente.getNumTelefono());
            try{
                db.update("CLIENTE", cv, "CODCLIENTE = ?", id);
            }catch (Exception e){
                return "Registro no pudo ser actualizado";
            }

            return "Registro Actualizado Correctamente";

      }

    public String eliminarCliente(Cliente cliente){
    String regAfectados="filas afectadas= ";
    int contador=0;

    contador+=db.delete("CLIENTE", "CODCLIENTE='"+cliente.getCodCliente()+"'", null);
    regAfectados+=contador;
    return regAfectados;
   }


    public String insertarPedido(Pedido pedido){

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        ContentValues prod = new ContentValues();
        prod.put("CODPEDIDO", pedido.getCodPedido());
        prod.put("CODREPAR", pedido.getCodRepar());
        prod.put("CODUBICACION", pedido.getCodUbicacion());
        prod.put("CODCLIENTE", pedido.getCodCliente());
        prod.put("CODLOCAL",pedido.getCodLocal());
        prod.put("TOTAL",pedido.getTotal());
        prod.put("COMENTARIOPEDIDO",pedido.getComentarioPedido());
        prod.put("ESTADO", pedido.getEstado());

        contador = db.insert("PEDIDO",null,prod);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        }else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    public Pedido consultarPedido(String codPedido){
        String[] id = {codPedido};
        Cursor cursor = db.query("PEDIDO",camposPedido, "CODPEDIDO = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Pedido pedido = new Pedido();
            pedido.setCodPedido(cursor.getInt(0));
            pedido.setCodRepar(cursor.getInt(1));
            pedido.setCodUbicacion(cursor.getInt(2));
            pedido.setCodCliente(cursor.getInt(3));
            pedido.setCodLocal(cursor.getInt(4));
            pedido.setTotal(cursor.getFloat(5));
            pedido.setComentarioPedido(cursor.getString(6));
            pedido.setEstado(cursor.getInt(7));
            return pedido;

        }else{

            return null;
        }

    }

    public String eliminarPedido(Pedido pedido){
        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("PEDIDO", "CODPEDIDO='"+pedido.getCodPedido()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String actualizarPedido(Pedido pedido){

        String[] id = {Integer.toString( pedido.getCodPedido())};
        ContentValues cv = new ContentValues();
        cv.put("CODPEDIDO", pedido.getCodPedido());
        cv.put("CODREPAR", pedido.getCodRepar());
        cv.put("CODUBICACION", pedido.getCodUbicacion());
//        cv.put("CODCLIENTE", pedido.getCodCliente());
//        cv.put("CODLOCAL", pedido.getCodLocal());
//        cv.put("TOTAL", pedido.getTotal());
        cv.put("COMENTARIOPEDIDO", pedido.getComentarioPedido());
        cv.put("ESTADO", pedido.getEstado());

        db.update("PEDIDO", cv, "CODPEDIDO = ?", id);
        return "Registro de Pedido Actualizado Correctamente";

    }

    public String insertarDetallePedido(DetallePedido detalle){

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        ContentValues prod = new ContentValues();
        prod.put("CODPEDIDO", detalle.getCodPedido());
        prod.put("CODPRODUCT", detalle.getCodProducto());
        prod.put("CODMENU", detalle.getCodMenu());
        prod.put("CANTIDADCOMPRA", detalle.getCantidadCompra());
        prod.put("CANTIDADPRODUCTO", detalle.getCantidadProducto());


        contador = db.insert("DETALLEPEDIDO",null,prod);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro, Registro dublicado. Verificar insercion";
        }else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    public ArrayList<String> consultarDetallePedido(int codPedido){

        ArrayList<String>  ListadeDetalle= new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT PEDIDO.COMENTARIOPEDIDO, PEDIDO.TOTAL,DETALLEPEDIDO.CODMENU,PRODUCTO.NOMBREPRODUCTO,DETALLEPEDIDO.CANTIDADCOMPRA,DETALLEPEDIDO.CANTIDADPRODUCTO FROM DETALLEPEDIDO INNER JOIN PEDIDO ON PEDIDO.CODPEDIDO = DETALLEPEDIDO.CODPEDIDO \n" +
                " INNER JOIN PRODUCTO ON PRODUCTO.CODPRODUCT=DETALLEPEDIDO.CODPRODUCT INNER JOIN MENU ON MENU.CODMENU=DETALLEPEDIDO.CODMENU WHERE DETALLEPEDIDO.CODPEDIDO= " + codPedido , null);

        String comentarioPedido;
        float total=0;
        int codMenu;
        String nombreProducto;
        int cantidadCompra;
        int cantidadProducto;


        while (cursor.moveToNext()){

            comentarioPedido=cursor.getString(0);
            total=cursor.getFloat(1);
            codMenu=cursor.getInt(2);
            nombreProducto=cursor.getString(3);
            cantidadCompra=cursor.getInt(4);
            cantidadProducto=cursor.getInt(5);

            ListadeDetalle.add(" CodMenu : \t"+ codMenu + "\n Cantidad de Menus Comprados : \t" + cantidadCompra+"\n nombre de Producto : "
            +nombreProducto+"\n Cantidad comprada de producto: "+cantidadProducto +"\n Comentario: "+comentarioPedido );
        }
        if(!ListadeDetalle.isEmpty()){
            ListadeDetalle.add("Total:$"+total);
        }

        return  ListadeDetalle;
    }
    public String actualizarDetallePedido(DetallePedido detalle){

        String[] id = {Integer.toString( detalle.getCodPedido())};
        ContentValues cv = new ContentValues();
        cv.put("CANTIDADCOMPRA", detalle.getCantidadCompra());

        db.update("DETALLEPEDIDO", cv, "CODPEDIDO = ?", id);
        return "Registro Actualizado Correctamente";

    }
    public String eliminarDetallePedido(DetallePedido detalle){
        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("DETALLEPEDIDO", "CODPEDIDO='"+detalle.getCodPedido()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

  
    public String actualizarMarca(Marca marca){
        long contador = 0;
        String[] id = {Integer.toString(marca.getCodMarca())};
        ContentValues cv = new ContentValues();
        cv.put("CODMARCA", marca.getCodMarca());
        cv.put("NOMBREMARCA",marca.getNombreMarca());


        contador =  db.update("MARCA", cv, "CODMARCA = ?", id);
        if(contador==-1 || contador==0){
            return "Error al insertar el registro, Registro dublicado. Verificar insercion";
        }
        else {
            return "Registro Actualizado Correctamente";
        }

    }

    public String eliminarMarca(Marca marca){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("MARCA", "CODMARCA='"+marca.getCodMarca()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

/// *****************************Menu Restaurante*******************************************************

    public String insertarMenu(MenuRest menuRest ){

        String regInsertados = "Menu Insertado Nº= ";
        long contador = 0;

        ContentValues menu = new ContentValues();
        menu.put("CODMENU", menuRest.getCODMENU());
        menu.put("CODLOCAL",menuRest.getCODLOCAL());
        menu.put("PRECIOCOMBO",menuRest.getPRECIOCOMBO());
        menu.put("DESCRIPCIONCOMBO",menuRest.getDESCRIPCIONCOMBO());

        contador = db.insert("MENU",null,menu);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el Menu, Registro dublicado. Verificar insercion";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    public MenuRest consultarMenu(String codMenu){
        String[] id = {codMenu};
        Cursor cursor = db.query("MENU", camposMenu, "CODMENU = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            MenuRest menu = new MenuRest();
            menu.setCODMENU(cursor.getInt(0));
            menu.setCODLOCAL(cursor.getInt(1));
            menu.setPRECIOCOMBO(cursor.getFloat(2));
            menu.setDESCRIPCIONCOMBO(cursor.getString(3));

            return menu;
        }else{
            return null;
        }

    }

    public String actualizarMenu(MenuRest menuRest){
        long contador = 0;
        String[] id = {Integer.toString(menuRest.getCODMENU())};
        ContentValues cv = new ContentValues();
        cv.put("CODMENU", menuRest.getCODMENU());
        cv.put("CODLOCAL",menuRest.getCODLOCAL());
        cv.put("PRECIOCOMBO",menuRest.getPRECIOCOMBO());
        cv.put("DESCRIPCIONCOMBO",menuRest.getDESCRIPCIONCOMBO());

        contador =  db.update("MENU", cv, "CODMENU = ?", id);
        if(contador==-1 || contador==0){
            return "Error al actualizar  el menu";
        }
        else {
            return "Menu Actualizado Correctamente";
        }

    }

    public String eliminarMenu(MenuRest menuRest){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("MENU", "CODMENU='"+menuRest.getCODMENU()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

//*******************************Combo Menu*********************************************
public String insertarCombo(ComboProducto combo ){

    String regInsertados = "Registro Inserado Nº= ";
    long contador = 0;

    ContentValues marc = new ContentValues();
    marc.put("CODMENU", combo.getCodMenu());
    marc.put("CODPRODUCT",combo.getCodProducto());
    marc.put("CANTPRODUCT",combo.getCodProducto());


    contador = db.insert("COMBOPRODUCTO",null,marc);
    if(contador==-1 || contador==0){
        regInsertados = "Error al insertar el combo, Registro dublicado. Verificar insercion";
    }
    else {
        regInsertados = regInsertados+contador;
    }
    return regInsertados;
}
    public ArrayList<String> consultarCombo(int codMenu){

        ArrayList<String>  Listaproducto = new ArrayList<>();

        Cursor cursor = db.rawQuery("select PRODUCTO.NOMBREPRODUCTO, COMBOPRODUCTO.CANTPRODUCT, MENU.PRECIOCOMBO FROM COMBOPRODUCTO" +
                " INNER JOIN PRODUCTO ON PRODUCTO.CODPRODUCT = COMBOPRODUCTO.CODPRODUCT\n" +
                " INNER JOIN  MENU ON  COMBOPRODUCTO.CODMENU = MENU.CODMENU WHERE COMBOPRODUCTO.CODMENU = " + codMenu +" group by PRODUCTO.NOMBREPRODUCTO", null);
            int cantidad;
            float precio=0;
            while (cursor.moveToNext()){
            Producto productos = new Producto();
            productos.setNombreProducto(cursor.getString(0));
            cantidad = cursor.getInt(1);
            precio = cursor.getFloat(2);
            Listaproducto.add(productos.getNombreProducto() + "\t Cantidad:\t" + cantidad);
        }
            if(!Listaproducto.isEmpty()){
                Listaproducto.add("Precio:$"+precio);
            }

        return  Listaproducto;

    }
    public String eliminarCombo(ComboProducto combo){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("COMBOPRODUCTO", "CODMENU='"+combo.getCodMenu()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }


    //*************Local*********////////////
public String insertarLocal(Local local ){

    String regInsertados = "Registro Inserado Nº= ";
    long contador = 0;

    ContentValues marc = new ContentValues();
    marc.put("CODLOCAL", local.getCodLocal());
    marc.put("CODENCAR",local.getCodEncar());
    marc.put("NOMBRELOCAL",local.getNombreLocal());
    marc.put("NUMEMPLEADOS",local.getNumEmpleados());
    marc.put("DESCRIPLOCAL",local.getDescripLocal());


    contador = db.insert("LOCAL",null,marc);
    if(contador==-1 || contador==0){
        regInsertados = "Error al insertar el Local, Registro dublicado. Verificar insercion";
    }
    else {
        regInsertados = regInsertados+contador;
    }
    return regInsertados;
}

//////***************Encargado***********
public String insertarEncargado(EncargadoLocal encargado ){

    String regInsertados = "Registro Inserado Nº= ";
    long contador = 0;

    ContentValues marc = new ContentValues();
    marc.put("CODENCAR", encargado.getCodEncar());
    marc.put("CODUSUARIO",encargado.getCodUsuario());
    marc.put("NOMBREENCAR",encargado.getNombreEncar());
    marc.put("APELLIDOENCAR",encargado.getApellidoEncar());
    marc.put("DUIENCAR",encargado.getDuiEncar());
    marc.put("NUMTELEFONO",encargado.getNumTelefono());


    contador = db.insert("ENCARGADOLOCAL",null,marc);
    if(contador==-1 || contador==0){
        regInsertados = "Error al insertar el encargado, Registro dublicado. Verificar insercion";
    }
    else {
        regInsertados = regInsertados+contador;
    }
    return regInsertados;
}
  
////////////////////////////////////////////////////////////////////////////////////////////

    public String insertarUsuario(Usuario usuario, String idRol){

        String regInsertados = "Registro Inserado Nº= ";
        long contador = 0;
        long contador_two = 0;

        ContentValues user = new ContentValues();
        user.put("CORREO", usuario.getCorreo());
        user.put("NOMBREUSU",usuario.getNombreUsu());
        user.put("CONTRASENA",usuario.getContrasena());
        contador = db.insert("USUARIO",null,user);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro de usuario, Registro dublicado. Verificar insercion";
            return regInsertados;
        }
        else {
            ContentValues accesousuario = new ContentValues();
            accesousuario.put("CODUSUARIO", contador);
            accesousuario.put("CODOPCION",Integer.parseInt(idRol));
            contador_two = db.insert("ACCESOUSUARIO",null,accesousuario);

            if(contador==-1 || contador==0){
                regInsertados = "Se creo Usuario pero Error al insertar el registro de ACCESO USUARIO, Registro dublicado. Verificar insercion";
                return regInsertados;
            }else{
                regInsertados = regInsertados+contador;
            }

        }
        return regInsertados;
    }

    public Usuario consultarUsuario(String correo){
        String[] camposUsuario = {"CODUSUARIO","CORREO", "NOMBREUSU", "CONTRASENA"};
        String[] id = {correo};
        Cursor cursor = db.query("USUARIO", camposUsuario, "CORREO = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setCodUsuario(cursor.getInt(0));
            usuario.setCorreo(cursor.getString(1));
            usuario.setNombreUsu(cursor.getString(2));
            usuario.setContrasena(cursor.getString(3));
            return usuario;
        }else{
            return null;
        }
    }

    public String actualizarUsuario(Usuario usuario, int role){
        if(verificarIntegridadUsuario(usuario,1)){
            String[] id = {usuario.getCorreo()};
            ContentValues cv = new ContentValues();

            cv.put("NOMBREUSU", usuario.getNombreUsu());
            cv.put("CONTRASENA", usuario.getContrasena());
            db.update("USUARIO", cv, "CORREO = ?", id);

            String[] camposUsuario = {"CODUSUARIO","CORREO", "NOMBREUSU", "CONTRASENA"};
            Cursor cursor = db.query("USUARIO", camposUsuario, "CORREO = ?", id, null, null, null);
            if(cursor.moveToFirst()){

                ContentValues cv_two = new ContentValues();
                String[] id_two = {Integer.toString(cursor.getInt(0))};
                cv_two.put("CODOPCION", role);
                db.update("ACCESOUSUARIO", cv_two, "CODUSUARIO = ?", id_two);

            }else{
                return null;
            }
            return "Registro Actualizado Correctamente";
        }else{
            return  "Registro con correo " + usuario.getCorreo() + " no existe";
        }
    }

    public String eliminarUsuario(Usuario usuario){
        String regAfectados="filas afectadas= ";
        int contado_two = 0;
        int contador=0;
//        if (verificarIntegridadUsuario(usuario,2)) {
//            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
//        }
        Usuario user = consultarUsuario(usuario.getCorreo());
        eliminarRolUser(user.getCodUsuario());
        contador+=db.delete("USUARIO", "CORREO='"+usuario.getCorreo()+"'", null);
        regAfectados+=contador;
        regAfectados+=contado_two;
        return regAfectados;
    }

    public int eliminarRolUser(int ide){
        int contador=0;
        contador+=db.delete("ACCESOUSUARIO", "CODUSUARIO='"+ide+"'", null);

        return contador;
    }


    private boolean verificarIntegridadUsuario(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1:
            {
                //verificar que exista usuario
                Usuario usuario2 = (Usuario)dato;
                String[] id = {usuario2.getCorreo()};
                abrir();
                Cursor c2 = db.query("USUARIO", null, "CORREO = ?", id, null, null,null);
                if(c2.moveToFirst()){
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }
            case 2:
            {
                //verificar que exista usuario
                Rol rol2 = (Rol)dato;
                String[] id = {Integer.toString(rol2.getIdRol())};
                abrir();
                Cursor c2 = db.query("OPCIONCRUD", null, "CODOPCION = ?", id, null, null,null);
                if(c2.moveToFirst()){
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }
            default: {
                return false;
            }
        }
    }

    public String insertarRol(Rol rol){
        String regInsertados = "Registro Inserado Nº= ";
        long contador = 0;

        ContentValues role = new ContentValues();
        role.put("CODOPCION", rol.getIdRol());
        role.put("DESCRIPCIONCRUD", rol.getDescripcion());
        role.put("NUMCRUD",rol.getNum());

        contador = db.insert("OPCIONCRUD",null,role);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro de rol, Registro dublicado. Verificar insercion";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    public Rol consultarRol(String idRol){
        String[] camposRol = {"CODOPCION", "DESCRIPCIONCRUD", "NUMCRUD"};
        String[] id = {idRol};
        Cursor cursor = db.query("OPCIONCRUD", camposRol, "CODOPCION = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Rol role = new Rol();
            role.setIdRol(cursor.getInt(0));
            role.setDescripcion(cursor.getString(1));
            role.setNum(cursor.getInt(2));
            return role;
        }else{
            return null;
        }
    }

    public ArrayList<String> consultarRoles(){
        ArrayList<String> listaRoles = new ArrayList<String>();
        ArrayList<Rol> rolList;
        rolList = new ArrayList<Rol>();
        String[] camposRol = {"CODOPCION", "DESCRIPCIONCRUD", "NUMCRUD"};
        Cursor cursor = db.rawQuery("SELECT * FROM OPCIONCRUD", null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Rol role = new Rol();
                role.setIdRol(cursor.getInt(0));
                role.setDescripcion(cursor.getString(1));
                role.setNum(cursor.getInt(2));

                rolList.add(role);
            }

            for(int i=0; i < rolList.size(); i++){
                listaRoles.add(rolList.get(i).getIdRol() + "-" + rolList.get(i).getDescripcion());
            }
            return listaRoles;
        }else {
            return listaRoles;
        }
    }

    public String actualizarRol(Rol rol){
        if(verificarIntegridadUsuario(rol,2)){
            String[] id = {Integer.toString(rol.getIdRol())};
            ContentValues cv = new ContentValues();

            cv.put("DESCRIPCIONCRUD", rol.getDescripcion());
            cv.put("NUMCRUD", rol.getNum());
            db.update("OPCIONCRUD", cv, "CODOPCION = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return  "Registro con COD " + rol.getIdRol() + " no existe";
        }
    }

    public String eliminarRol(Rol rol){
        String regAfectados="filas afectadas= ";
        int contador=0;
//        if (verificarIntegridadUsuario(usuario,2)) {
//            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
//        }
        contador+=db.delete("OPCIONCRUD", "CODOPCION='"+rol.getIdRol()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String consultarRolUser(int idUser){
        String rol = "No posee rol";
        Cursor cursor = db.rawQuery("select OPCIONCRUD.NUMCRUD FROM ACCESOUSUARIO\n" +
                "INNER JOIN OPCIONCRUD ON ACCESOUSUARIO.CODOPCION = OPCIONCRUD.CODOPCION\n" +
                "WHERE ACCESOUSUARIO.CODUSUARIO = " + idUser, null);

        if(cursor.moveToFirst()){
            rol = cursor.getString(0);
            return rol;
        }else {
            return rol;
        }
    }

    public int consultarRolUserNum(int idUser){
        int rol = -1;
        Cursor cursor = db.rawQuery("select OPCIONCRUD.DESCRIPCIONCRUD,OPCIONCRUD.NUMCRUD FROM ACCESOUSUARIO\n" +
                "INNER JOIN OPCIONCRUD ON ACCESOUSUARIO.CODOPCION = OPCIONCRUD.CODOPCION\n" +
                "WHERE ACCESOUSUARIO.CODUSUARIO = " + idUser, null);

        if(cursor.moveToFirst()){
            rol = cursor.getInt(1);
            return rol;
        }else {
            return rol;
        }
    }

    public String insertarCategoria(Categoria categoria){

        String regInsertados = "Registro Inserado Nº= ";
        long contador = 0;

        ContentValues cate = new ContentValues();
        cate.put("CODCATEGORIA", categoria.getCodCategoria());
        cate.put("NOMBRECATEGORIA",categoria.getNombreCategoria());

        contador = db.insert("CATEGORIA",null,cate);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro categoria, Registro dublicado. Verificar insercion";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    public Categoria consultarCategoria(String codCategoria){
        String[] camposCategoria = {"CODCATEGORIA", "NOMBRECATEGORIA"};
        String[] id = {codCategoria};
        Cursor cursor = db.query("CATEGORIA", camposCategoria, "CODCATEGORIA = ?",
                id, null, null, null);
        if(cursor.moveToFirst()){
            Categoria categoria = new Categoria();
            categoria.setCodCategoria(cursor.getInt(0));
            categoria.setNombreCategoria(cursor.getString(1));

            return categoria;
        }else{
            return null;
        }

    }

    public String actualizarCategoria(Categoria categoria){
        long contador = 0;
        String[] id = {Integer.toString(categoria.getCodCategoria())};
        ContentValues cv = new ContentValues();
        cv.put("CODCATEGORIA", categoria.getCodCategoria());
        cv.put("NOMBRECATEGORIA", categoria.getNombreCategoria());


        contador =  db.update("CATEGORIA", cv, "CODCATEGORIA = ?", id);
        if(contador==-1 || contador==0){
            return "Error al insertar el registro Categoria, Registro dublicado. Verificar insercion";
        }
        else {
            return "Registro Actualizado Correctamente";
        }

    }

    public String eliminarCategoria(Categoria categoria){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("CATEGORIA", "CODCATEGORIA='"+categoria.getCodCategoria()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public Usuario consultarUsuarioLog(String correo, String password){
        String[] camposUsuario = {"CODUSUARIO","CORREO", "NOMBREUSU", "CONTRASENA"};
        String[] id = {correo,password};
        Cursor cursor = db.query("USUARIO", camposUsuario, "CORREO = ? AND CONTRASENA = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setCodUsuario(cursor.getInt(0));
            usuario.setCorreo(cursor.getString(1));
            usuario.setNombreUsu(cursor.getString(2));
            usuario.setContrasena(cursor.getString(3));
            return usuario;
        }else{
            return null;
        }
    }
//    public String insertar(Alumno alumno){
//
//        String regInsertados = "Registro Inserado Nº= ";
//        long contador = 0;
//
//        ContentValues alum = new ContentValues();
//        alum.put("Carnet", alumno.getCarnet());
//        alum.put("nombre",alumno.getNombre());
//        alum.put("apellido",alumno.getApellido());
//        alum.put("sexo",alumno.getSexo());
//        alum.put("matganadas", alumno.getMatganadas());
//        contador = db.insert("alumno",null,alum);
//        if(contador==-1 || contador==0){
//            regInsertados = "Error al insertar el registro, Registro dublicado. Verificar insercion";
//        }
//        else {
//            regInsertados = regInsertados+contador;
//        }
//        return regInsertados;
//    }

//    public String actualizar(Alumno alumno){
//        if(verificarIntegridad(alumno, 5)){
//            String[] id = {alumno.getCarnet()};
//            ContentValues cv = new ContentValues();
//            cv.put("nombre", alumno.getNombre());
//            cv.put("apellido", alumno.getApellido());
//            cv.put("sexo", alumno.getSexo());
//            db.update("alumno", cv, "carnet = ?", id);
//            return "Registro Actualizado Correctamente";
//        }else{
//            return "Registro con carnet " + alumno.getCarnet() + " no existe";
//        }
//      }

//    public String eliminar(Alumno alumno){
//        String regAfectados="filas afectadas= ";
//        int contador=0;
//        if (verificarIntegridad(alumno,3)) {
//            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
//        }
//        contador+=db.delete("alumno", "carnet='"+alumno.getCarnet()+"'", null);
//        regAfectados+=contador;
//        return regAfectados;
//   }


//    public Alumno consultarAlumno(String carnet){
//        String[] id = {carnet};
//        Cursor cursor = db.query("alumno", camposAlumno, "carnet = ?",
//                id, null, null, null);
//        if(cursor.moveToFirst()){
//            Alumno alumno = new Alumno();
//            alumno.setCarnet(cursor.getString(0));
//            alumno.setNombre(cursor.getString(1));
//            alumno.setApellido(cursor.getString(2));
//            alumno.setSexo(cursor.getString(3));
//            alumno.setMatganadas(cursor.getInt(4));
//            return alumno;
//        }else{
//            return null;
//        }
//
//    }
//
//    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
//        switch(relacion){case 1:
//        {
////verificar que al insertar nota exista carnet del alumno y el codigo de materia
//            Nota nota = (Nota)dato;
//            String[] id1 = {nota.getCarnet()};
//            String[] id2 = {nota.getCodmateria()};
////abrir();
//            Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null,
//                    null, null);
//            Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2,
//                    null, null, null);
//            if(cursor1.moveToFirst() && cursor2.moveToFirst()){
////Se encontraron datos
//                return true;
//            }
//            return false;
//        }
//            case 2:
//            {
////verificar que al modificar nota exista carnet del alumno, el   codigo de materia y el ciclo
//                Nota nota1 = (Nota)dato;
//                String[] ids = {nota1.getCarnet(), nota1.getCodmateria(),
//                        nota1.getCiclo()};
//                abrir();
//                Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND ciclo = ?", ids, null, null, null);
//                if(c.moveToFirst()){
////Se encontraron datos
//                    return true;
//                }
//                return false;
//            }
//            case 3:
//            {
//                Alumno alumno = (Alumno)dato;
//                Cursor c=db.query(true, "nota", new String[] {
//                                "carnet" }, "carnet='"+alumno.getCarnet()+"'",null,
//                        null, null, null, null);
//                if(c.moveToFirst())
//                    return true;
//                else
//                    return false;
//            }
//            case 4:
//            {
//                Materia materia = (Materia)dato;
//                Cursor cmat=db.query(true, "nota", new String[] {
//                                "codmateria" },
//                        "codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
//                if(cmat.moveToFirst())
//                    return true;
//                else
//                    return false;
//            }
//            case 5:
//            {
////verificar que exista alumno
//                Alumno alumno2 = (Alumno)dato;
//                String[] id = {alumno2.getCarnet()};
//                abrir();
//                Cursor c2 = db.query("alumno", null, "carnet = ?", id, null, null,
//                        null);
//                if(c2.moveToFirst()){
////Se encontro Alumno
//                    return true;
//                }
//                return false;
//            }
//            case 6:
//            {
////verificar que exista Materia
//                Materia materia2 = (Materia)dato;
//                String[] idm = {materia2.getCodmateria()};
//                abrir();
//                Cursor cm = db.query("materia", null, "codmateria = ?", idm, null,
//                        null, null);
//                if(cm.moveToFirst()){
////Se encontro Materia
//                    return true;
//                }
//                return false;
//            }
//            default:
//                return false;
//        }
//       }
public String llenarBDRG14(){
//      final String[] VACODCLIENTE = {"3"};
//    final String[] VAnombre = {"Carlos","Pedro","Sara","Gabriela"};
//    final String[] VAapellido = {"Orantes","Ortiz","Gonzales","Coto"};
//    final String[] VAsexo = {"M","M","F","F"};
//    final String[] VMcodmateria = {"MAT115","PRN115","IEC115","TSI115"};
//    final String[] VMnommateria = {"Matematica I","Programacion I","Ingenieria Economica","Teoria de Sistemas"};
//    final String[] VMunidadesval = {"4","4","4","4"};
//    final String[] VNcarnet =
//            {"OO12035","OF12044","GG11098","CC12021","OO12035","GG11098","OF12044"};
//    final String[] VNcodmateria =
//            {"MAT115","PRN115","IEC115","TSI115","IC115","MAT115","PRN115"};
//    final String[] VNciclo =
//            {"12016","12016","22016","22016","22016","12016","22016"};
//    final float[] VNnotafinal = {7,5,8,7,6,10,7};
     abrir();
//    db.execSQL("DELETE FROM alumno");
//    db.execSQL("DELETE FROM materia");
//    db.execSQL("DELETE FROM nota");
//    Alumno alumno = new Alumno();
//    for(int i=0;i<4;i++){
//        alumno.setCarnet(VAcarnet[i]);
//        alumno.setNombre(VAnombre[i]);
//        alumno.setApellido(VAapellido[i]);
//        alumno.setSexo(VAsexo[i]);
//        alumno.setMatganadas(0);
//        insertar(alumno);
//    }
//    Materia materia = new Materia();
//    for(int i=0;i<4;i++){
//        materia.setCodmateria(VMcodmateria[i]);
//        materia.setNommateria(VMnommateria[i]);
//        materia.setUnidadesval(VMunidadesval[i]);
//        insertar(materia);
//    }
//    Nota nota = new Nota();
//    for(int i=0;i<7;i++){
//        nota.setCarnet(VNcarnet[i]);
//        nota.setCodmateria(VNcodmateria[i]);
//        nota.setCiclo(VNciclo[i]);
//        nota.setNotafinal(VNnotafinal[i]);
//        insertar(nota);
//    }
     cerrar();
    return "Guardo Correctamente";
}
}




