package com.example.deliveryuesgrp14;

public class Marca {
    private int codMarca;
    private  String NombreMarca;

    public Marca() {
    }
    public Marca(int codMarca, String nombreMarca) {
        this.codMarca = codMarca;
        NombreMarca = nombreMarca;
    }

    public int getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }

    public String getNombreMarca() {
        return NombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        NombreMarca = nombreMarca;
    }
}
