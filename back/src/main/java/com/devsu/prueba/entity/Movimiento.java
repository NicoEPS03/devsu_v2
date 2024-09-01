package com.devsu.prueba.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @NotNull(message = "El numero cuenta es requerido")
    @Column(name = "numeroCuenta", nullable = false)
    private Integer numeroCuenta;
    @NotNull(message = "El tipo de movimiento es requerido")
    @Column(name = "tipoMovimiento", nullable = false, length = 30)
    private String tipoMovimiento;
    @NotNull(message = "El valor es requerido")
    @Column(name = "valor", nullable = false)
    private Integer valor;

    @Column(name = "saldoInicial", nullable = false)
    private Integer saldoInicial;

    public Movimiento() {
    }

    public Movimiento(LocalDate fecha, Integer numeroCuenta, String tipoMovimiento, Integer valor, Integer saldoInicial) {
        this.fecha = fecha;
        this.numeroCuenta = numeroCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldoInicial = saldoInicial;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Integer saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
}
