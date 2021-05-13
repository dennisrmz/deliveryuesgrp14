package com.example.deliveryuesgrp14;

public class Usuario {
    private int codUsuario;
    private String correo;
    private String nombreusu;
    private String contrasena;

    public Usuario(){
    }

    public Usuario(int codUsuario,String correo, String nombreusu, String contrasena){
        this.codUsuario = codUsuario;
        this.correo = correo;
        this.nombreusu = nombreusu;
        this.contrasena = contrasena;
    }

    public int getCodUsuario(){
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario){
        this.codUsuario = codUsuario;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getNombreUsu(){
        return nombreusu;
    }

    public void setNombreUsu(String nombreusu){
        this.nombreusu = nombreusu;
    }

    public String getContrasena(){
        return contrasena;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
}
