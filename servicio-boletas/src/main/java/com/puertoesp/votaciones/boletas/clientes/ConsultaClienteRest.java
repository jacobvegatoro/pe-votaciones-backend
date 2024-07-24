package com.puertoesp.votaciones.boletas.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.puertoesp.votaciones.boletas.models.Consulta;

//@FeignClient(name = "servicio-consultas", url = "localhost:8001")
@FeignClient(name = "servicio-consultas")
public interface ConsultaClienteRest {

    @GetMapping("/listar")
    public List<Consulta> listar();

    @GetMapping("/ver/{id}")
    public Consulta detalle(@PathVariable Long id);
    
}
