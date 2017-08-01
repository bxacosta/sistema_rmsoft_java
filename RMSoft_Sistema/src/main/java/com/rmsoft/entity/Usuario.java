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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private Integer id;
    
    @Column(name = "usuario_usuario", unique = true, nullable = false, length = 45)
    private String usuario;
    
    @Column(name = "password_usuario", length = 60, nullable = false)
    private String password;
    
    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUsuario")
    private Set<RolUsuario> rolUsuario = new HashSet<RolUsuario>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<Reservacion> reservacion = new HashSet<Reservacion>();

    public Usuario() {
    }

    public Usuario(String usuario, String password, Boolean habilitado) {
        this.usuario = usuario;
        this.password = password;
        this.habilitado = habilitado;
    }

    public Usuario(Integer id, String usuario, String password, Boolean habilitado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.habilitado = habilitado;
    }
    
    public Usuario(Integer id, String usuario, String password, Boolean habilitado, Set<RolUsuario> rol) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.habilitado = habilitado;
        this.rolUsuario = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<RolUsuario> getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Set<RolUsuario> rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Set<Reservacion> getReservacion() {
        return reservacion;
    }

    public void setReservacion(Set<Reservacion> reservacion) {
        this.reservacion = reservacion;
    }
}