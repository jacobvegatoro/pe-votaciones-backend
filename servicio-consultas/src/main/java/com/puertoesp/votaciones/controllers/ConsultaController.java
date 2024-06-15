package com.puertoesp.votaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.puertoesp.votaciones.models.entity.Consulta;
import com.puertoesp.votaciones.models.service.IConsultaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping("/listar")
    public List<Consulta> listar(){
        return consultaService.getAll();
    }

    @GetMapping("/ver/{id}")
    public Consulta detalle(@PathVariable Long id){
        return consultaService.getById(id);
    }

}
