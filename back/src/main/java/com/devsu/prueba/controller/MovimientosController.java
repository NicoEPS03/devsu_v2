package com.devsu.prueba.controller;

import com.devsu.prueba.dto.MovimientoRequestDto;
import com.devsu.prueba.dto.MovimientoResponseDTO;
import com.devsu.prueba.entity.Movimiento;
import com.devsu.prueba.exception.IntegridadException;
import com.devsu.prueba.service.IMovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/movimientos")
@Validated
public class MovimientosController {
    @Autowired
    private IMovimientoService service;

    @PostMapping(value = "/realizarMovimiento", consumes = "application/json")
    public ResponseEntity<?> realizarMovimiento (@Valid @RequestBody Movimiento movimiento) throws IntegridadException, Exception {
        movimiento.setFecha(LocalDate.now());
        movimiento = service.realizarMovimiento(movimiento);

        return new ResponseEntity<Object>(movimiento, HttpStatus.CREATED);
    }

    @PostMapping(value = "/reportes", consumes = "application/json")
    public ResponseEntity<?> consultarEstado (@Valid @RequestBody MovimientoRequestDto movimiento) throws IntegridadException, Exception {
        List<MovimientoResponseDTO> movimientoResponseDTO = service.estadoCuenta(movimiento);

        return new ResponseEntity<Object>(movimientoResponseDTO, HttpStatus.OK);
    }


}
