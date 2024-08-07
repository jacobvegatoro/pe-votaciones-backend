package com.puertoesp.votaciones.boletas.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.puertoesp.votaciones.boletas.models.Consulta;

//@FeignClient(name = "servicio-consultas", url = "localhost:8001")
@FeignClient(name = "servicio-consultas")
public interface ConsultaClienteRest {

    @GetMapping("/listar")
    public List<Consulta> listar();

    @GetMapping("/ver/{id}")
    public Consulta detalle(@PathVariable Long id);

    @PostMapping("/crear")
    public Consulta crear(@RequestBody Consulta consulta);

    @PutMapping("/editar/{id}")
    public Consulta editar(@RequestBody Consulta consulta, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id);

}
