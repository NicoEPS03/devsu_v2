package com.devsu.prueba.service;

import com.devsu.prueba.entity.Cuenta;

import java.util.List;

public interface ICuentaService extends ICrud<Cuenta, Integer>{
    public List<Cuenta> listaCuenta();

}
