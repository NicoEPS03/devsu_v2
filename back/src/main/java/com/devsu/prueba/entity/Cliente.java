package com.devsu.prueba.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @NotNull(message = "La contraseña es requerida")
    @Column(name = "password", nullable = false, length = 30)
    private String password;
    @Column(name = "estado", columnDefinition = "boolean default true")
    private Boolean estado = true;

    public Cliente() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
