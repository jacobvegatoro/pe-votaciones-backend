package com.puertoesp.votaciones.alternativas.models.service;

import java.util.List;

import com.puertoesp.votaciones.alternativas.models.Alternativa;

public interface AlternativaService {

    public List<Alternativa> getAll();
    public Alternativa getById(Long id, String texto, Integer estado);

}
