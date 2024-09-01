package com.devsu.prueba.repository;

import com.devsu.prueba.entity.Cliente;
import com.devsu.prueba.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Integer> {
    public Persona findByIdPersona(int id);
}
