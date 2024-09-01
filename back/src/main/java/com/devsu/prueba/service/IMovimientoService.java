package com.devsu.prueba.service;

import com.devsu.prueba.dto.MovimientoRequestDto;
import com.devsu.prueba.dto.MovimientoResponseDTO;
import com.devsu.prueba.entity.Movimiento;
import com.devsu.prueba.exception.ModelNotFoundException;

import java.util.List;

public interface IMovimientoService {
    public Movimiento realizarMovimiento(Movimiento movimiento) throws ModelNotFoundException;
    public List<MovimientoResponseDTO> estadoCuenta(MovimientoRequestDto requestDto) throws ModelNotFoundException;
}
