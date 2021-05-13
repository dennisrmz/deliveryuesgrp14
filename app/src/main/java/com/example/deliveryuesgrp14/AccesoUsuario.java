package com.example.deliveryuesgrp14;

public class AccesoUsuario {
    private int codUsuario;
    private int codOpcion;

    public AccesoUsuario(){
    }

    public AccesoUsuario(int codUsuario, int codOpcion){
        this.codUsuario = codUsuario;
        this.codOpcion = codOpcion;
    }

    public int getIdUsuario(){
        return codUsuario;
    }

    public void setIdUsuario(int numUser){
        this.codUsuario = numUser;
    }

    public int getIdRol(){
        return codOpcion;
    }

    public void setIdRol(int numRol){
        this.codOpcion = numRol;
    }
}
