package com.devsu.prueba.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
@Entity
@Table(name = "cuenta")
@NamedQueries({
        @NamedQuery(name = "Cuenta.buscarNumeroCuenta", query = "SELECT COUNT(m) FROM Cuenta m WHERE m.numeroCuenta = :numeroCuenta"),
})
public class Cuenta implements Serializable {
    @Id
    private Integer numeroCuenta;
    @NotNull(message = "El tipo de cuenta es requerida")
    @Column(name = "tipo_cuenta", nullable = false, length = 30)
    private String tipoCuenta;
    @NotNull(message = "El saldo es requerida")
    @Column(name = "saldo", nullable = false, length = 30)
    private Integer saldo;
    @Column(name = "estado", columnDefinition = "boolean default true")
    private Boolean estado = true;
    private Integer clienteId;

    public Cuenta() {
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
