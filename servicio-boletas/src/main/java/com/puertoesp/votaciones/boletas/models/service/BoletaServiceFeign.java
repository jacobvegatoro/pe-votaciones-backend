package com.puertoesp.votaciones.boletas.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.puertoesp.votaciones.boletas.clientes.ConsultaClienteRest;
import com.puertoesp.votaciones.boletas.models.Boleta;
import com.puertoesp.votaciones.boletas.models.Consulta;

@Service("serviceFeign") //alternativa a Primary
@Primary //esto se incluye en caso que tenga más de una interface, y quiero definir cuál se usará por defecto
public class BoletaServiceFeign implements BoletaService {

    @Autowired
    private ConsultaClienteRest clienteFeign;

    @Override
    public List<Boleta> getAll() {
        return clienteFeign.listar().stream().map(p -> new Boleta(p, "Texto boleta")).collect(Collectors.toList());
    }

    @Override
    public Boleta getById(Long id, String texto) {
        return new Boleta(clienteFeign.detalle(id), texto);
    }

    @Override
    public Consulta create(Consulta consulta) {
        return clienteFeign.crear(consulta);
    }

    @Override
    public Consulta update(Consulta consulta, Long id) {
        return clienteFeign.editar(consulta, id);
    }

    @Override
    public void dropById(Long id) {
        clienteFeign.eliminar(id);
    }

}
