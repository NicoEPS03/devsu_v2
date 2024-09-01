package com.devsu.prueba.repository;

import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepo  extends JpaRepository<Cuenta, Integer> {
    public Cuenta findByNumeroCuenta(int numeroCuenta);
    public int buscarNumeroCuenta(int numeroCuenta);

}
