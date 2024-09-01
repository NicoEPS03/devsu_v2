package com.devsu.prueba.service;

import com.devsu.prueba.entity.Cliente;

import java.util.List;

public interface IClienteService extends ICrud<Cliente, Integer>{
    public List<Cliente> listaClientes();
}
