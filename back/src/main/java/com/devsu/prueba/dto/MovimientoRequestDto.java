package com.devsu.prueba.dto;

import java.time.LocalDate;

public class MovimientoRequestDto {
    private String fecha;
    private String fechaRango;
    private Integer clienteId;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaRango() {
        return fechaRango;
    }

    public void setFechaRango(String fechaRango) {
        this.fechaRango = fechaRango;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
