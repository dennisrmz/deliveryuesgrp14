package com.example.deliveryuesgrp14;

public class EncargadoLocal {
    int codEncar;
    int codUsuario;
    String nombreEncar;
    String apellidoEncar;
    String duiEncar;
    String numTelefono;

    public EncargadoLocal() {
    }

    public EncargadoLocal(int codEncar, int codUsuario, String nombreEncar, String apellidoEncar, String duiEncar, String numTelefono) {
        this.codEncar = codEncar;
        this.codUsuario = codUsuario;
        this.nombreEncar = nombreEncar;
        this.apellidoEncar = apellidoEncar;
        this.duiEncar = duiEncar;
        this.numTelefono = numTelefono;
    }

    public int getCodEncar() {
        return codEncar;
    }

    public void setCodEncar(int codEncar) {
        this.codEncar = codEncar;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreEncar() {
        return nombreEncar;
    }

    public void setNombreEncar(String nombreEncar) {
        this.nombreEncar = nombreEncar;
    }

    public String getApellidoEncar() {
        return apellidoEncar;
    }

    public void setApellidoEncar(String apellidoEncar) {
        this.apellidoEncar = apellidoEncar;
    }

    public String getDuiEncar() {
        return duiEncar;
    }

    public void setDuiEncar(String duiEncar) {
        this.duiEncar = duiEncar;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }
}
