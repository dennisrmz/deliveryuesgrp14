package com.example.deliveryuesgrp14;

public class Usuario {
    private String correo;
    private String nombreusu;
    private String contrasena;

    public Usuario(){
    }

    public Usuario(String correo, String nombreusu, String contrasena){
        this.correo = correo;
        this.nombreusu = nombreusu;
        this.contrasena = contrasena;
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
