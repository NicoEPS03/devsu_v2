package com.devsu.prueba.repository;

import com.devsu.prueba.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
    public Persona findByIdentificacion(String identificacion);

    public int buscarIdentificacion(String identificacion);

}
