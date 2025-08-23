package com.puertoesp.votaciones.alternativas.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.puertoesp.votaciones.alternativas.models.AlternativaDAO;

public interface AlternativaRepository extends CrudRepository<AlternativaDAO, Long> {

    // Aquí puedes agregar métodos personalizados si es necesario

}
