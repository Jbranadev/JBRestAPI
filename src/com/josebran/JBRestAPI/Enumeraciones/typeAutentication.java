package com.josebran.jbrestapi.Enumeraciones;

public enum typeAutentication {
    BASIC("Basic "), DIGEST("Digest "),BEARER("Bearer "), APIKEY("Apikey ");

    private String tipoautenticacion;

    private typeAutentication(String s) {
        this.tipoautenticacion=s;

    }

    public String getTipoAutenticacion(){
        return tipoautenticacion;
    }

}
