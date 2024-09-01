package com.devsu.prueba.service.imp;

import com.devsu.prueba.entity.Persona;
import com.devsu.prueba.repository.ICuentaRepo;
import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.exception.ArgumentRequiredException;
import com.devsu.prueba.exception.IntegridadException;
import com.devsu.prueba.exception.ModelNotFoundException;
import com.devsu.prueba.repository.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devsu.prueba.service.ICuentaService;

import java.util.List;
import java.util.Objects;

@Service
public class ICuentaServiceImp implements ICuentaService {
    @Autowired
    private ICuentaRepo repo;

    @Autowired
    private IPersonaRepo repoPersona;

    private int existByNumeroCuenta(int idCliente) {
        return repo.buscarNumeroCuenta(idCliente);
    }
    @Override
    public Cuenta retonarPorId(Integer id) throws ModelNotFoundException {
        if(existByNumeroCuenta(id) == 1) {
            Cuenta cuenta = this.repo.findByNumeroCuenta(id);
            return cuenta;
        } else
            throw new ModelNotFoundException("Cuenta no encontrada");
    }

    @Override
    public void guardar(Cuenta cuenta) throws IntegridadException {
        if (this.repoPersona.findById(cuenta.getClienteId()).isPresent()){
            if (repo.findByNumeroCuenta(cuenta.getNumeroCuenta()) != null) {
                throw new IntegridadException("Número de cuenta ya existe");
            }
            this.repo.save(cuenta);
        }else
            throw new IntegridadException("El cliente no existe");
    }

    @Override
    public void editar(Cuenta cuenta) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if(cuenta.getNumeroCuenta() != null) {
            if(existByNumeroCuenta(cuenta.getNumeroCuenta()) > 1) {
                throw new ModelNotFoundException("Numero cuenta ya existente");
            } else {
                if (this.repoPersona.findById(cuenta.getClienteId()).isPresent())
                    this.repo.save(cuenta);
                else
                    throw new IntegridadException("El cliente no existe");
            }
        } else {
            throw new ArgumentRequiredException("IdNumeroCuenta es requerido");
        }
    }

    @Override
    public void eliminar(int idCuenta) throws ModelNotFoundException, ArgumentRequiredException {
        if(!Objects.isNull(idCuenta)) {
            if(existByNumeroCuenta(idCuenta) == 1) {
                Cuenta cuenta = this.repo.findById(idCuenta).get();
                cuenta.setEstado(false);
                this.repo.save(cuenta);
            } else
                throw new ModelNotFoundException("Numero cuenta no encontrado");
        } else {
            throw new ArgumentRequiredException("IdNumeroCuenta es requerido");
        }
    }

    @Override
    public List<Cuenta> listaCuenta() {
        return repo.findAll();
    }
}
