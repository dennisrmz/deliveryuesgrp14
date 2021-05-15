package com.example.deliveryuesgrp14;

public class DetallePedido {

    private  int codDetalle;
    private int codProducto;
    private int codPedido;
    private int codMenu;
    private int cantidadCompra;
    private  int cantidadProducto;

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }





    public DetallePedido(int codDetalle, int codProducto, int codPedido, int codMenu, int cantidadCompra,int cantidadProducto) {
        this.codDetalle = codDetalle;
        this.codProducto = codProducto;
        this.codPedido = codPedido;
        this.codMenu = codMenu;
        this.cantidadCompra = cantidadCompra;
        this.cantidadProducto=cantidadProducto;
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

    public int getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(int codDetalle) {
        this.codDetalle = codDetalle;
    }
}
