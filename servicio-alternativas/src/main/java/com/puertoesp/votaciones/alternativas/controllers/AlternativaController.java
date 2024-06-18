package com.puertoesp.votaciones.alternativas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.puertoesp.votaciones.alternativas.models.Alternativa;
import com.puertoesp.votaciones.alternativas.models.service.AlternativaService;

@RestController
public class AlternativaController {

    @Autowired
    private AlternativaService alternativaService;

    @GetMapping("/listar")
    public List<Alternativa> listar(){
        return alternativaService.getAll();
    }

    @GetMapping("/ver/{id}/texto/{texto}/estado/{estado}")
    public Alternativa detalle(@PathVariable Long id, @PathVariable String texto, @PathVariable Integer estado) {
        return alternativaService.getById(id, texto, estado);
    }
    

}
