package com.devsu.prueba.service.imp;

import com.devsu.prueba.entity.Cliente;
import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.entity.Persona;
import com.devsu.prueba.exception.ArgumentRequiredException;
import com.devsu.prueba.exception.IntegridadException;
import com.devsu.prueba.exception.ModelNotFoundException;
import com.devsu.prueba.repository.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devsu.prueba.repository.IClienteRepo;
import com.devsu.prueba.service.IClienteService;

import java.util.List;
import java.util.Objects;

@Service
public class IClienteServiceImp implements IClienteService {

    @Autowired
    private IClienteRepo repoCliente;
    @Autowired
    private IPersonaRepo repoPersona;

    private Boolean validarExistenciaPorId(int idCliente) {
        return repoPersona.existsById(idCliente);
    }
    @Override
    public Cliente retonarPorId(Integer id) throws ModelNotFoundException {
        if(this.repoCliente.existsById(id)) {
            Cliente cliente = (Cliente) this.repoCliente.findByIdPersona(id);
            return cliente;
        } else
            throw new ModelNotFoundException("Cliente no encontrado");
    }

    @Override
    public void guardar(Cliente cliente) throws IntegridadException {
        if (repoPersona.findByIdentificacion(cliente.getIdentificacion()) != null) {
            throw new IntegridadException("Número de identificacion ya existe");
        }
        this.repoPersona.save(cliente);
    }

    @Override
    public void editar(Cliente cliente) throws ArgumentRequiredException, ModelNotFoundException, IntegridadException {
        if(cliente.getIdPersona() != null) {
            if(validarExistenciaPorId(cliente.getIdPersona())) {
                if(repoPersona.buscarIdentificacion(cliente.getIdentificacion()) == 1) {
                    this.repoPersona.save(cliente);
                }else {
                    throw new IntegridadException("Identificacion ya existe");
                }
            } else
                throw new ModelNotFoundException("Cliente no encontrado");
        } else {
            throw new ArgumentRequiredException("IdCliente es requerido");
        }
    }

    @Override
    public void eliminar(int idPersona) throws ModelNotFoundException, ArgumentRequiredException{
        if(!Objects.isNull(idPersona)) {
            if(this.repoPersona.findById(idPersona).isPresent()) {
                Persona persona = this.repoPersona.findById(idPersona).get();
                Cliente cliente = (Cliente) this.repoCliente.findByIdPersona(persona.getIdPersona());
                cliente.setEstado(false);
                this.repoCliente.save(cliente);
            } else
                throw new ModelNotFoundException("Cliente no encontrado");
        } else {
            throw new ArgumentRequiredException("IdCliente es requerido");
        }
    }

    @Override
    public List<Cliente> listaClientes() {
        return repoCliente.findAll();
    }
}
