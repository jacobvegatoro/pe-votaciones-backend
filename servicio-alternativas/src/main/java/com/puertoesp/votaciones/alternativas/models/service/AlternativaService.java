package com.puertoesp.votaciones.alternativas.models.service;

import java.util.List;

import com.puertoesp.votaciones.alternativas.models.Alternativa;
import com.puertoesp.votaciones.alternativas.models.Consulta;

public interface AlternativaService {

    public List<Alternativa> getAll();
    public Alternativa getById(Long id, String texto, Integer estado);

    public Consulta create(Consulta consulta);
    public Consulta update(Consulta consulta, Long id);
    public void dropById(Long id);

}
