package com.puertoesp.votaciones.alternativas.models;

public class Alternativa {

    private Consulta consulta;
    private String texto;
    private Integer estado;

    public Alternativa() {}

    public Alternativa(Consulta consulta, String texto, Integer estado) {
        this.consulta = consulta;
        this.texto = texto;
        this.estado = estado;
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
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    

}
