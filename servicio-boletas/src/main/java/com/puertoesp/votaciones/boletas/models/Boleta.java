package com.puertoesp.votaciones.boletas.models;

public class Boleta {

    private Consulta consulta;
    private String texto;
    
    public Boleta() {
    }

    public Boleta(Consulta consulta, String texto) {
        this.consulta = consulta;
        this.texto = texto;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
