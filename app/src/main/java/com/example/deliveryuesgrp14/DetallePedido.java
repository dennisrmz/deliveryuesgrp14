package com.example.deliveryuesgrp14;

public class DetallePedido {

    private int codProducto;
    private int codPedido;
    private int codMenu;
    private int cantidadCompra;



    public DetallePedido(int codProducto, int codPedido, int codMenu, int cantidadCompra) {
        this.codProducto = codProducto;
        this.codPedido = codPedido;
        this.codMenu = codMenu;
        this.cantidadCompra = cantidadCompra;
    }



    public DetallePedido() {
    }



    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(int codMenu) {
        this.codMenu = codMenu;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }



}
