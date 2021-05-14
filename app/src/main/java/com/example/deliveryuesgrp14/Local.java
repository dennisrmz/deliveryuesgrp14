package com.example.deliveryuesgrp14;

public class Local {
    int codLocal;
    int codEncar;
    String nombreLocal;
    int numEmpleados;
    String descripLocal;

    public Local() {
    }

    public Local(int codLocal, int codEncar, String nombreLocal, int numEmpleados, String descripLocal) {
        this.codLocal = codLocal;
        this.codEncar = codEncar;
        this.nombreLocal = nombreLocal;
        this.numEmpleados = numEmpleados;
        this.descripLocal = descripLocal;
    }

    public int getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(int codLocal) {
        this.codLocal = codLocal;
    }

    public int getCodEncar() {
        return codEncar;
    }

    public void setCodEncar(int codEncar) {
        this.codEncar = codEncar;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public String getDescripLocal() {
        return descripLocal;
    }

    public void setDescripLocal(String descripLocal) {
        this.descripLocal = descripLocal;
    }
}
