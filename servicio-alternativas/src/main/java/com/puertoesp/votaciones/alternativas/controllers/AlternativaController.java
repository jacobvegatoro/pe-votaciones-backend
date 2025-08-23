package com.puertoesp.votaciones.alternativas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puertoesp.votaciones.alternativas.models.Alternativa;
import com.puertoesp.votaciones.alternativas.models.AlternativaDAO;
import com.puertoesp.votaciones.alternativas.models.Consulta;
import com.puertoesp.votaciones.alternativas.models.service.AlternativaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AlternativaController {

    @Autowired
    private AlternativaService alternativaService;

    @GetMapping("/listar")
    public List<Alternativa> listar(
        @RequestParam(name="nombre", required = false) String nombre,
        @RequestHeader(name="token-request", required = false) String token
    ){
        System.out.println(nombre);
        System.out.println(token);
        return alternativaService.getAll();
    }

    @GetMapping("/ver/{id}/texto/{texto}/estado/{estado}")
    public Alternativa detalle(@PathVariable Long id, @PathVariable String texto, @PathVariable Integer estado) {
        return alternativaService.getById(id, texto, estado);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta crear(@RequestBody Consulta consulta) {        
        return alternativaService.create(consulta);
    }
    
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta editar(@RequestBody Consulta consulta, @PathVariable Long id) {
        return alternativaService.update(consulta, id);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        alternativaService.dropById(id);
    }

    @GetMapping("/listaralternativas")
    public List<Alternativa> listarnuevo(){
        return alternativaService.obtenerTodo();
    }

    @PostMapping("/crearalternativa")
    @ResponseStatus(HttpStatus.CREATED)
    public AlternativaDAO crearnuevo(@RequestBody AlternativaDAO alternativaDAO) {        
        return alternativaService.crear(alternativaDAO);
    }

    @GetMapping("/ver/{id}")
    public Alternativa getByIdFromDb(@PathVariable Long id) {
        return alternativaService.obtenerPorId(id);
    }

    @PutMapping("/editaralternativa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlternativaDAO editarnuevo(@PathVariable Long id, @RequestBody AlternativaDAO alternativaDAO) {        
        return alternativaService.actualizar(id, alternativaDAO);
    }

    @DeleteMapping("/eliminaralternativa/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaralternativa(@PathVariable Long id) {
        alternativaService.eliminar(id);
    }

}
