package com.puertoesp.votaciones.boletas.models.service;

import java.util.List;

import com.puertoesp.votaciones.boletas.models.Boleta;

public interface BoletaService {

    public List<Boleta> getAll();
    public Boleta getById(Long id, String texto);

}
