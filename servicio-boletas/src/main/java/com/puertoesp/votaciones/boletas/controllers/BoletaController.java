package com.puertoesp.votaciones.boletas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.puertoesp.votaciones.boletas.models.Boleta;
import com.puertoesp.votaciones.boletas.models.service.BoletaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class BoletaController {

    @Autowired
    @Qualifier("serviceFeign") //esto se usa cuando se agrega un nombre al servicio 
    private BoletaService boletaService;

    @GetMapping("/listar")
    public List<Boleta> listar() {
        return boletaService.getAll();
    }
    
    @GetMapping("/ver/{id}/texto/{texto}")
    public Boleta detalle(@PathVariable Long id, @PathVariable String texto) {
        return boletaService.getById(id, texto);
    }

}
