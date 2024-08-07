package com.puertoesp.votaciones.boletas.models.service;

import java.util.List;

import com.puertoesp.votaciones.boletas.models.Boleta;
import com.puertoesp.votaciones.boletas.models.Consulta;

public interface BoletaService {

    public List<Boleta> getAll();
    public Boleta getById(Long id, String texto);

    public Consulta create(Consulta consulta);
    public Consulta update(Consulta consulta, Long id);
    public void dropById(Long id);

}
