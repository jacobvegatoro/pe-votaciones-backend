package com.puertoesp.votaciones.consultas.models.service;

import java.util.List;

import com.puertoesp.votaciones.consultas.models.entity.Consulta;

public interface IConsultaService {

    public List<Consulta> getAll();
    public Consulta getById(Long id);

    public Consulta create(Consulta consulta);

    public void dropById(Long id);

}
