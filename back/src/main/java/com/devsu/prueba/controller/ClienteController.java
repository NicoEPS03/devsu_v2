package com.devsu.prueba.controller;

import com.devsu.prueba.entity.Cliente;
import com.devsu.prueba.entity.Cuenta;
import com.devsu.prueba.exception.IntegridadException;
import com.devsu.prueba.exception.ModelNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.devsu.prueba.service.IClienteService;

import java.util.List;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping(value = "listar", produces = "application/json")
    public ResponseEntity<?> retornarClientes() throws  Exception {
        List<Cliente> cliente = service.listaClientes();

        return new ResponseEntity<Object>(cliente, HttpStatus.OK);
    }
    @GetMapping(value = "obtener/{id}", produces = "application/json")
    public ResponseEntity<?> retornarCliente(@PathVariable("id") int id ) throws ModelNotFoundException, Exception {
        Cliente cliente = service.retonarPorId(id);

        return new ResponseEntity<Object>(cliente, HttpStatus.OK);
    }
    @PostMapping(value = "/insertar", consumes = "application/json")
    public ResponseEntity<?> guardar (@Valid @RequestBody Cliente cliente) throws IntegridadException, Exception {
        service.guardar(cliente);

        return new ResponseEntity<Object>(cliente, HttpStatus.CREATED);
    }
    @PutMapping(value = "/editar", consumes = "application/json")
    public ResponseEntity<?> editar (@Valid @RequestBody Cliente cliente) throws ModelNotFoundException, IntegridadException, Exception {
        service.editar(cliente);

        return new ResponseEntity<Object>(cliente, HttpStatus.OK);
    }

    @DeleteMapping (value = "/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException, IntegridadException, Exception {
        service.eliminar(id);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
