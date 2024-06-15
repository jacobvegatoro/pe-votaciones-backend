package com.puertoesp.votaciones.models.service;

import java.util.List;

import com.puertoesp.votaciones.models.entity.Consulta;

public interface IConsultaService {

    public List<Consulta> getAll();
    public Consulta getById(Long id);

}
