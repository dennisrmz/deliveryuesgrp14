package com.example.deliveryuesgrp14;

public class Rol {
    private int idRol;
    private String descripcionCrud;
    private int numCrud;

    public Rol(){
    }

    public Rol(int idRol,String descripcionCrud, int numCrud){
        this.idRol = idRol;
        this.descripcionCrud = descripcionCrud;
        this.numCrud = numCrud;
    }

    public String getDescripcion(){
        return descripcionCrud;
    }

    public void setDescripcion(String descripcionCrud){
        this.descripcionCrud = descripcionCrud;
    }

    public int getNum(){
        return numCrud;
    }

    public void setNum(int numCrud){
        this.numCrud = numCrud;
    }

    public int getIdRol(){
        return idRol;
    }

    public void setIdRol(int idRol){
        this.idRol = idRol;
    }

}
