package com.devsu.prueba.controller;

import com.devsu.prueba.entity.Cliente;
import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.exception.IntegridadException;
import com.devsu.prueba.exception.ModelNotFoundException;
import com.devsu.prueba.service.ICuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/cuentas")
@Validated
public class CuentaController {

    @Autowired
    private ICuentaService service;

    @GetMapping(value = "listar", produces = "application/json")
    public ResponseEntity<?> retornarCuentas() throws  Exception {
        List<Cuenta> cuentas = service.listaCuenta();

        return new ResponseEntity<Object>(cuentas, HttpStatus.OK);
    }

    @GetMapping(value = "obtener/{id}", produces = "application/json")
    public ResponseEntity<?> retornarCuenta(@PathVariable("id") int id ) throws ModelNotFoundException, Exception {
        Cuenta cuenta = service.retonarPorId(id);

        return new ResponseEntity<Object>(cuenta, HttpStatus.OK);
    }
    @PostMapping(value = "/insertar", consumes = "application/json")
    public ResponseEntity<?> guardar (@Valid @RequestBody Cuenta cuenta) throws IntegridadException, Exception {
        service.guardar(cuenta);

        return new ResponseEntity<Object>(cuenta, HttpStatus.CREATED);
    }
    @PutMapping(value = "/editar", consumes = "application/json")
    public ResponseEntity<?> editar (@Valid @RequestBody Cuenta cuenta) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(cuenta);

        return new ResponseEntity<Object>(cuenta, HttpStatus.OK);
    }

    @DeleteMapping (value = "/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
