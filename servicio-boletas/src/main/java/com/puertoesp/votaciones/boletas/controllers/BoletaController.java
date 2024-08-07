package com.puertoesp.votaciones.boletas.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puertoesp.votaciones.boletas.models.Boleta;
import com.puertoesp.votaciones.boletas.models.Consulta;
import com.puertoesp.votaciones.boletas.models.service.BoletaService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
public class BoletaController {

    private final Logger logger = LoggerFactory.getLogger(BoletaController.class);

    @SuppressWarnings("rawtypes")
    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    @Qualifier("serviceFeign") //esto se usa cuando se agrega un nombre al servicio 
    private BoletaService boletaService;

    @GetMapping("/listar")
    public List<Boleta> listar() {
        return boletaService.getAll();
    }
    
    @GetMapping("/ver/{id}/texto/{texto}")
    public Boleta detalle(@PathVariable Long id, @PathVariable String texto) {
        //return boletaService.getById(id, texto);
        return cbFactory.create("boletas")
            .run(() -> boletaService.getById(id, texto), e -> metodoAlternativo(id, texto, e));
    }
    
    @CircuitBreaker(name="boletas", fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver2/{id}/texto/{texto}")
    public Boleta detalle2(@PathVariable Long id, @PathVariable String texto) {
        return boletaService.getById(id, texto);
    }
    
    @CircuitBreaker(name="boletas", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name="boletas")
    @GetMapping("/ver3/{id}/texto/{texto}")
    public CompletableFuture<Boleta> detalle3(@PathVariable Long id, @PathVariable String texto) {
        return CompletableFuture.supplyAsync(() -> boletaService.getById(id, texto));
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta crear(@RequestBody Consulta consulta) {        
        return boletaService.create(consulta);
    }
    
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta editar(@RequestBody Consulta consulta, @PathVariable Long id) {
        return boletaService.update(consulta, id);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        boletaService.dropById(id);
    }

    public Boleta metodoAlternativo(Long id, String texto, Throwable e){

        logger.info(e.getMessage());

        Boleta boleta = new Boleta();
        Consulta consulta = new Consulta();

        boleta.setTexto(texto);
        consulta.setId(id);
        consulta.setTitulo("Consulta X");
        consulta.setDetalle("Detalle consulta X");
        consulta.setEstado(1);
        consulta.setFechaInicio(null);
        consulta.setFechaFin(null);
        boleta.setConsulta(consulta);

        return boleta;
    }

    public CompletableFuture<Boleta> metodoAlternativo2(Long id, String texto, Throwable e){

        logger.info(e.getMessage());

        Boleta boleta = new Boleta();
        Consulta consulta = new Consulta();

        boleta.setTexto(texto);
        consulta.setId(id);
        consulta.setTitulo("Consulta X");
        consulta.setDetalle("Detalle consulta X");
        consulta.setEstado(1);
        consulta.setFechaInicio(null);
        consulta.setFechaFin(null);
        boleta.setConsulta(consulta);

        return CompletableFuture.supplyAsync(() -> boleta);
    }

}
