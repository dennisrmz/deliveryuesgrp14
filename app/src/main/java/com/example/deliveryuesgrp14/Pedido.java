package com.example.deliveryuesgrp14;

public class Pedido {

    private int codPedido;
    private int codRepar;
    private int codUbicacion;
    private int codCliente;
    private int codLocal;
    private  float total;
    private  String comentarioPedido;
    private  int estado;

    public Pedido() {
    }

    public Pedido(int codPedido, int codRepar, int codUbicacion, int codCliente, int codLocal, float total, String comentarioPedido, int estado) {
        this.codPedido = codPedido;
        this.codRepar = codRepar;
        this.codUbicacion = codUbicacion;
        this.codCliente = codCliente;
        this.codLocal = codLocal;
        this.total = total;
        this.comentarioPedido = comentarioPedido;
        this.estado = estado;
    }


    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodRepar() {
        return codRepar;
    }

    public void setCodRepar(int codRepar) {
        this.codRepar = codRepar;
    }

    public int getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(int codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(int codLocal) {
        this.codLocal = codLocal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getComentarioPedido() {
        return comentarioPedido;
    }

    public void setComentarioPedido(String comentarioPedido) {
        this.comentarioPedido = comentarioPedido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }








}
