package com.puertoesp.votaciones.alternativas.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.puertoesp.votaciones.alternativas.models.Alternativa;
import com.puertoesp.votaciones.alternativas.models.Consulta;

@Service
public class AlternativaServiceImpl implements AlternativaService {

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Alternativa> getAll() {
        //List<Consulta> consultas = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Consulta[].class));
        List<Consulta> consultas = Arrays.asList(clienteRest.getForObject("http://localhost:8090/api/consultas/listar", Consulta[].class));
        return consultas.stream().map(c -> new Alternativa(c, "Texto alternativa",1)).collect(Collectors.toList());
    }

    @Override
    public Alternativa getById(Long id, String texto, Integer estado) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        //Consulta consulta = clienteRest.getForObject("http://localhost:8001/ver/{id}", Consulta.class, pathVariables);
        Consulta consulta = clienteRest.getForObject("http://localhost:8090/api/consultas/ver/{id}", Consulta.class, pathVariables);
        return new Alternativa(consulta, texto, estado);
    }

    @Override
    public Consulta create(Consulta consulta) {
        HttpEntity<Consulta> body = new HttpEntity<Consulta>(consulta);
        ResponseEntity<Consulta> response = clienteRest.exchange("http://localhost:8090/api/consultas/crear",
            HttpMethod.POST, body, Consulta.class);
        Consulta consultaResponse = response.getBody();
        return consultaResponse;
    }

    @Override
    public Consulta update(Consulta consulta, Long id) {

        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        HttpEntity<Consulta> body = new HttpEntity<Consulta>(consulta);
        ResponseEntity<Consulta> response = clienteRest.exchange("http://localhost:8090/api/consultas/editar/{id}",
            HttpMethod.PUT, body, Consulta.class, pathVariables);

        return response.getBody();
    }

    @Override
    public void dropById(Long id) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        clienteRest.delete("http://localhost:8090/api/consultas/eliminar/{id}", pathVariables);
    }



}
