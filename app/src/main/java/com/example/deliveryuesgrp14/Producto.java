package com.example.deliveryuesgrp14;

public class Producto {

    private int codProduct;
    private int codCategoria;
    private  int codMarca;
    private  String NombreProducto;
    private  String descripcionProd;
    private  int existencias;
    private  float precio;

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Producto() {
    }

    public Producto(int codProduct, int codCategoria, int codMarca, String nombreProducto, String descripcionProd, int existencias, float precio) {
        this.codProduct = codProduct;
        this.codCategoria = codCategoria;
        this.codMarca = codMarca;
        NombreProducto = nombreProducto;
        this.descripcionProd = descripcionProd;
        this.existencias = existencias;
        this.precio = precio;
    }

    public int getCodProduct() {
        return codProduct;
    }

    public void setCodProduct(int codProduct) {
        this.codProduct = codProduct;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public int getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
}
