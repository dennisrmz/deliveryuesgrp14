package com.example.deliveryuesgrp14;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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
                        "create table CAMBOPRODUCTO \n" +
                        "(\n" +
                        "   CODPRODUCT           INTEGER              not null,\n" +
                        "   CODMENU              INTEGER              not null,\n" +
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
                        "   FECHACREAT           DATE                 not null,\n" +
                        "   FECHAUPDATE          DATE                 not null,\n" +
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
    public String insertar(Producto producto){

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
    public String actualizar(Producto producto){
        long contador = 0;
        String[] id = {Integer.toString(producto.getCodProduct())};
        ContentValues cv = new ContentValues();
        cv.put("CODPRODUCT", producto.getCodProduct());
        cv.put("CODCATEGORIA",producto.getCodCategoria());
        cv.put("CODMARCA",producto.getCodMarca());
        cv.put("NOMBREPRODUCTO",producto.getNombreProducto());
        cv.put("DESCRIPCIONPROD", producto.getDescripcionProd());
        cv.put("EXISTENCIAS", producto.getExistencias());

        contador =  db.update("PRODUCTO", cv, "CODPRODUCT = ?", id);
        if(contador==-1 || contador==0){
            return "Error al insertar el registro, Registro dublicado. Verificar insercion";
        }
        else {
            return "Registro Actualizado Correctamente";
        }

    }

    public String eliminar(Producto producto){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("PRODUCTO", "CODPRODUCT='"+producto.getCodProduct()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    ///******************************Marca*****************************
    public String insertar(Marca marca ){

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
            db.update("CLIENTE", cv, "CODCLIENTE = ?", id);
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
  
    public String actualizar(Marca marca){
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

    public String eliminar(Marca marca){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("MARCA", "CODMARCA='"+marca.getCodMarca()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }


    public String insertarUsuario(Usuario usuario){
        String regInsertados = "Registro Inserado Nº= ";
        long contador = 0;

        ContentValues user = new ContentValues();
        user.put("CORREO", usuario.getCorreo());
        user.put("NOMBREUSU",usuario.getNombreUsu());
        user.put("CONTRASENA",usuario.getContrasena());
        contador = db.insert("USUARIO",null,user);
        if(contador==-1 || contador==0){
            regInsertados = "Error al insertar el registro de usuario, Registro dublicado. Verificar insercion";
        }
        else {
            regInsertados = regInsertados+contador;
        }
        return regInsertados;
    }

    public Usuario consultarUsuario(String correo){
        String[] camposUsuario = {"CORREO", "NOMBREUSU", "CONTRASENA"};
        String[] id = {correo};
        Cursor cursor = db.query("USUARIO", camposUsuario, "CORREO = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setCorreo(cursor.getString(0));
            usuario.setNombreUsu(cursor.getString(1));
            usuario.setContrasena(cursor.getString(2));
            return usuario;
        }else{
            return null;
        }
    }

    public String actualizarUsuario(Usuario usuario){
        if(verificarIntegridadUsuario(usuario,1)){
            String[] id = {usuario.getCorreo()};
            ContentValues cv = new ContentValues();

            cv.put("NOMBREUSU", usuario.getNombreUsu());
            cv.put("CONTRASENA", usuario.getContrasena());
            db.update("USUARIO", cv, "CORREO = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return  "Registro con correo " + usuario.getCorreo() + " no existe";
        }
    }

    public String eliminarUsuario(Usuario usuario){
        String regAfectados="filas afectadas= ";
        int contador=0;
//        if (verificarIntegridadUsuario(usuario,2)) {
//            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
//        }
        contador+=db.delete("USUARIO", "CORREO='"+usuario.getCorreo()+"'", null);
        regAfectados+=contador;
        return regAfectados;
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
        role.put("CODOPCION", rol.getNum());
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




