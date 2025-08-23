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
import com.puertoesp.votaciones.alternativas.models.AlternativaDAO;
import com.puertoesp.votaciones.alternativas.models.Consulta;
import com.puertoesp.votaciones.alternativas.models.repository.AlternativaRepository;

@Service
public class AlternativaServiceImpl implements AlternativaService {

    @Autowired
    private RestTemplate clienteRest;

    @Autowired
    private AlternativaRepository alternativaRepository;

    /**
     * Obtiene todas las alternativas de la base de datos y les asocia la consulta correspondiente
     * obtenida desde el endpoint remoto.
     */
    @Override
    public List<Alternativa> obtenerTodo() {
        List<com.puertoesp.votaciones.alternativas.models.AlternativaDAO> alternativasDAO = new java.util.ArrayList<>();
        alternativaRepository.findAll().forEach(alternativasDAO::add);
        List<Consulta> consultas = Arrays.asList(clienteRest.getForObject("http://localhost:8090/api/consultas/listar", Consulta[].class));
        Map<Long, Consulta> consultaMap = consultas.stream()
                .collect(Collectors.toMap(Consulta::getId, c -> c));
        // Convertir AlternativaDAO a Alternativa y asociar la consulta correspondiente
        return alternativasDAO.stream().map(altDao -> {
            Consulta consulta = consultaMap.get(altDao.getConsultaId());
            return new com.puertoesp.votaciones.alternativas.models.Alternativa(
                altDao.getId(),
                consulta,
                altDao.getTexto(),
                altDao.getEstado()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<Alternativa> getAll() {
        //List<Consulta> consultas = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Consulta[].class));
        List<Consulta> consultas = Arrays.asList(clienteRest.getForObject("http://localhost:8090/api/consultas/listar", Consulta[].class));
        return consultas.stream().map(c -> new Alternativa(1L, c, "Texto alternativa",1)).collect(Collectors.toList());
    }

    @Override
    public Alternativa getById(Long id, String texto, Integer estado) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        //Consulta consulta = clienteRest.getForObject("http://localhost:8001/ver/{id}", Consulta.class, pathVariables);
        Consulta consulta = clienteRest.getForObject("http://localhost:8090/api/consultas/ver/{id}", Consulta.class, pathVariables);
        return new Alternativa(1L, consulta, texto, estado);
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

    @Override
    public AlternativaDAO crear(AlternativaDAO alternativaDAO) {
        // Validar que el consultaId exista
        Long consultaId = alternativaDAO.getConsultaId();
        try {
            String url = "http://localhost:8090/api/consultas/ver/" + consultaId;
            Consulta consulta = clienteRest.getForObject(url, Consulta.class);
            if (consulta == null || consulta.getId() == null) {
                throw new IllegalArgumentException("El consultaId no es válido: " + consultaId);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("El consultaId no es válido: " + consultaId);
        }
        return alternativaRepository.save(alternativaDAO);
    }

    @Override
    public Alternativa obtenerPorId(Long id) {
        return alternativaRepository.findById(id)
            .map(altDao -> {
                Consulta consulta = null;
                try {
                    String url = "http://localhost:8090/api/consultas/ver/" + altDao.getConsultaId();
                    consulta = clienteRest.getForObject(url, Consulta.class);
                } catch (Exception e) {
                    // Manejar la excepción si no se puede obtener la consulta
                    e.printStackTrace();
                }
                return new Alternativa(
                    altDao.getId(),
                    consulta,
                    altDao.getTexto(),
                    altDao.getEstado()
                );
            })
            .orElse(null);
    }

    @Override
    public AlternativaDAO actualizar(Long id, AlternativaDAO alternativaDAO) {
        // Validar que el id de la alternativa exista
        if (!alternativaRepository.existsById(id)) {
            throw new IllegalArgumentException("El id de la alternativa no existe: " + id);
        }
        // Validar que el consultaId exista
        Long consultaId = alternativaDAO.getConsultaId();
        try {
            String url = "http://localhost:8090/api/consultas/ver/" + consultaId;
            Consulta consulta = clienteRest.getForObject(url, Consulta.class);
            if (consulta == null || consulta.getId() == null) {
                throw new IllegalArgumentException("El consultaId no es válido: " + consultaId);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("El consultaId no es válido: " + consultaId);
        }
        alternativaDAO.setId(id); // Asegura que el id sea el correcto
        return alternativaRepository.save(alternativaDAO);
    }

    @Override
    public void eliminar(Long id) {
        if (!alternativaRepository.existsById(id)) {
            throw new IllegalArgumentException("El id de la alternativa no existe: " + id);
        }
        alternativaRepository.deleteById(id);
    }

}
