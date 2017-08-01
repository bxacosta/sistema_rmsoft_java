/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

/**
 *
 * @author bryan
 */
public class DescuentoModel {

    private Integer id;

    private String estado;
    
    private Integer procentaje;
    
    private String nombre;
    
    private String condicion;
    
    private String valorCondicion;
    
    private String fechaInicio;
    
    private String fechaFin;
    
    public DescuentoModel() {
        this.procentaje = 0;
        
    }

    public DescuentoModel(Integer id, String estado, Integer procentaje, String nombre, String condicion, String valorCondicion, String fechaInicio, String fechaFin) {
        this.id = id;
        this.estado = estado;
        this.procentaje = procentaje;
        this.nombre = nombre;
        this.condicion = condicion;
        this.valorCondicion = valorCondicion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public DescuentoModel(String estado, Integer procentaje, String nombre, String condicion, String valorCondicion, String fechaInicio, String fechaFin) {
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

    public String getValorCondicion() {
        return valorCondicion;
    }

    public void setValorCondicion(String valorCondicion) {
        this.valorCondicion = valorCondicion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "DescuentoModel{" + "id=" + id + ", estado=" + estado + ", procentaje=" + procentaje + ", nombre=" + nombre + ", condicion=" + condicion + ", valorCondicion=" + valorCondicion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
}
