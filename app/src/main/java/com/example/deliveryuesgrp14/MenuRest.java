package com.example.deliveryuesgrp14;

public class MenuRest {
    private  int CODMENU;
    private  int CODLOCAL;
    private  float PRECIOCOMBO;
    private  String DESCRIPCIONCOMBO;

    public MenuRest() {
    }

    public MenuRest(int CODMENU, int CODLOCAL, float PRECIOCOMBO, String DESCRIPCIONCOMBO) {
        this.CODMENU = CODMENU;
        this.CODLOCAL = CODLOCAL;
        this.PRECIOCOMBO = PRECIOCOMBO;
        this.DESCRIPCIONCOMBO = DESCRIPCIONCOMBO;
    }

    public int getCODMENU() {
        return CODMENU;
    }

    public void setCODMENU(int CODMENU) {
        this.CODMENU = CODMENU;
    }

    public int getCODLOCAL() {
        return CODLOCAL;
    }

    public void setCODLOCAL(int CODLOCAL) {
        this.CODLOCAL = CODLOCAL;
    }

    public float getPRECIOCOMBO() {
        return PRECIOCOMBO;
    }

    public void setPRECIOCOMBO(float PRECIOCOMBO) {
        this.PRECIOCOMBO = PRECIOCOMBO;
    }

    public String getDESCRIPCIONCOMBO() {
        return DESCRIPCIONCOMBO;
    }

    public void setDESCRIPCIONCOMBO(String DESCRIPCIONCOMBO) {
        this.DESCRIPCIONCOMBO = DESCRIPCIONCOMBO;
    }
}
