/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bryan
 */
@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue
    @Column(name = "id_habitacion")
    private Integer id;
    
    @Column(name = "numero", nullable = false)
    private String numero;
    
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "estado", nullable = false)
    private String estado;
    
    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;
    
    @Column(name = "precio_dia")
    private Double precioDia;
    
    @Column(name = "precio_hora")
    private Double precioHora;
    
    @Column(name = "descripccion")
    private String descripccion;
    
    @Column(name = "imagen")
    private String imagen;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "habitacion")
    private Set<DetalleReserva> detalleReserva = new HashSet<DetalleReserva>();
    
    public Habitacion() {
        
    }

    public Habitacion(Integer id, String numero, Integer capacidad, String tipo, String estado, String ubicacion, Double precioDia, Double precioHora, String descripccion, String imagen) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.precioDia = precioDia;
        this.precioHora = precioHora;
        this.descripccion = descripccion;
        this.imagen = imagen;
    }

    public Habitacion(String numero, Integer capacidad, String tipo, String estado, String ubicacion, Double precioDia, Double precioHora, String descripccion, String imagen) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.precioDia = precioDia;
        this.precioHora = precioHora;
        this.descripccion = descripccion;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Double precioDia) {
        this.precioDia = precioDia;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", capacidad=" + capacidad + ", tipo=" + tipo + ", estado=" + estado + ", ubicacion=" + ubicacion + ", precioDia=" + precioDia + ", precioHora=" + precioHora + ", descripccion=" + descripccion + ", imagen=" + imagen + '}';
    }
    
    
}
