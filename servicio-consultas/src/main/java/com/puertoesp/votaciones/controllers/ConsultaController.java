package com.puertoesp.votaciones.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import com.puertoesp.votaciones.models.entity.Consulta;
import com.puertoesp.votaciones.models.service.IConsultaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ConsultaController {

    //@Autowired
    //private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IConsultaService consultaService;

    @GetMapping("/listar")
    public List<Consulta> listar(){
        //return consultaService.getAll();
        return consultaService.getAll().stream().map(consulta -> {
            //consulta.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            consulta.setPort(port);
            return consulta;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Consulta detalle(@PathVariable Long id){
        Consulta consulta = consultaService.getById(id);
        //consulta.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        consulta.setPort(port);
        return consulta;
    }

}
