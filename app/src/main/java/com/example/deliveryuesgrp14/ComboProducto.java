package com.example.deliveryuesgrp14;

public class ComboProducto {
    int codProducto;
    int codMenu;
    int cantProduct;

    public ComboProducto() {
    }

    public ComboProducto(int codProducto, int codMenu, int cantProduct) {
        this.codProducto = codProducto;
        this.codMenu = codMenu;
        this.cantProduct = cantProduct;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public int getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(int codMenu) {
        this.codMenu = codMenu;
    }

    public int getCantProduct() {
        return cantProduct;
    }

    public void setCantProduct(int cantProduct) {
        this.cantProduct = cantProduct;
    }
}
