package com.devsu.prueba.service;

import com.devsu.prueba.exception.ArgumentRequiredException;
import com.devsu.prueba.exception.IntegridadException;
import com.devsu.prueba.exception.ModelNotFoundException;

public interface ICrud<T,ID> {

    public T retonarPorId(ID id) throws ModelNotFoundException;

    public void guardar(T objeto)  throws IntegridadException;

    public void editar(T objeto)  throws ArgumentRequiredException, ModelNotFoundException, IntegridadException;

    public void eliminar(int idObjeto) throws ModelNotFoundException, ArgumentRequiredException;
}
