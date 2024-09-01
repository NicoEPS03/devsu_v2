package com.devsu.prueba.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
@NamedQueries({
        @NamedQuery(name = "Persona.buscarIdentificacion", query = "SELECT COUNT(m) FROM Persona m WHERE m.identificacion = :identificacion"),
})
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    private Integer idPersona;
    @NotNull(message = "El nombre es requerido")
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
    @NotNull(message = "El genero es requerido")
    @Column(name = "genero", nullable = false)
    private java.lang.Character genero;
    @NotNull(message = "La edad es requerida")
    @Column(name = "edad", nullable = false)
    private Integer edad;
    @NotNull(message = "La identificacion es requerida")
    @Column(name = "identificacion", nullable = false, length = 15, unique = true)
    private String identificacion;
    @NotNull(message = "El direccion es requerido")
    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;
    @NotNull(message = "El telefono es requerido")
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;
    public Persona() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
