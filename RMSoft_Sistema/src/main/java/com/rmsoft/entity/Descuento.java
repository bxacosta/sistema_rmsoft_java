/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bryan
 */
@Entity
@Table(name = "descuento")
public class Descuento {
    
    @Id
    @GeneratedValue
    @Column(name = "id_descuento")
    private Integer id;
    
    @Column(name = "estado_descuento", nullable = false)
    private String estado;
    
    @Column(name = "porcentaje_descuento")
    private Integer procentaje;
    
    @Column(name = "nombre_descuento")
    private String nombre;
    
    @Column(name = "condicion_descuento")
    private String condicion;
    
    @Column(name = "valor_condicion_descuento")
    private Double valorCondicion;
    
    @Column(name = "fecha_inicio_descuento")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Column(name = "fecha_fin_descuento")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    public Descuento() {
        
    }

    public Descuento(Integer id, String estado, Integer procentaje, String nombre, String condicion, Double valorCondicion, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.estado = estado;
        this.procentaje = procentaje;
        this.nombre = nombre;
        this.condicion = condicion;
        this.valorCondicion = valorCondicion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Descuento(String estado, Integer procentaje, String nombre, String condicion, Double valorCondicion, Date fechaInicio, Date fechaFin) {
        this.estado = estado;
        this.procentaje = procentaje;
        this.nombre = nombre;
        this.condicion = condicion;
        this.valorCondicion = valorCondicion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getProcentaje() {
        return procentaje;
    }

    public void setProcentaje(Integer procentaje) {
        this.procentaje = procentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Double getValorCondicion() {
        return valorCondicion;
    }

    public void setValorCondicion(Double valorCondicion) {
        this.valorCondicion = valorCondicion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Descuento{" + "id=" + id + ", estado=" + estado + ", procentaje=" + procentaje + ", nombre=" + nombre + ", condicion=" + condicion + ", valorCondicion=" + valorCondicion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
    
}
