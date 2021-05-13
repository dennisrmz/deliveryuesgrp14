package com.example.deliveryuesgrp14;

public class Cliente {

    private int codCliente;
    private int codUsuario;
    private  String nombreCliente;
    private  String apellidoCliente;
    private  String numTelefono;

    public Cliente() {
    }


    public Cliente(int codCliente, int codUsuario, String nombreCliente, String apellidoCliente, String numTelefono) {
        this.codCliente = codCliente;
        this.codUsuario = codUsuario;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.numTelefono = numTelefono;
    }


    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }





}
