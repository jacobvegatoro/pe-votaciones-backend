package com.puertoesp.votaciones.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puertoesp.votaciones.models.entity.Consulta;
import com.puertoesp.votaciones.models.repository.ConsultaRepository;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> getAll() {
        return (List<Consulta>) consultaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Consulta getById(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

}
