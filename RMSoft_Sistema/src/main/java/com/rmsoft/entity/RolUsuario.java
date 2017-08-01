/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author bryan
 */
@Entity
@Table(name = "rol_usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre_rol", "id_usuario"}))
public class RolUsuario {

    @Id
    @GeneratedValue
    @Column(name = "id_rol", unique = true)
    private Integer id;

    @Column(name = "nombre_rol", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    public RolUsuario() {

    }

    public RolUsuario(String nombre, Usuario idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    public RolUsuario(Integer id, String nombre, Usuario idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
