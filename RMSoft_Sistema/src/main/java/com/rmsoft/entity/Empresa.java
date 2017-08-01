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
@Table(name = "empresa")
public class Empresa {
    
    @Id
    @GeneratedValue
    @Column(name = "id_empresa")
    private Integer id;
    
    @Column(name = "nombre_empresa", nullable = false)
    private String nombre;
    
    @Column(name = "denominacion_empresa", nullable = false)
    private String denominacion;
    
    @Column(name = "telefono_empresa", nullable = false)
    private String telefono;
    
    @Column(name = "celular_empresa", nullable = false)
    private String celular;
    
    @Column(name = "pais_empresa", nullable = false)
    private String pais;
    
    @Column(name = "ciudad_empresa", nullable = false)
    private String ciudad;
    
    @Column(name = "email_empresa", nullable = false)
    private String email;
    
    @Column(name = "direccion_empresa", nullable = false)
    private String direccion;
    
    @Column(name = "mapa_empresa", nullable = false, columnDefinition = "TEXT")
    private String mapa;
    
    @Column(name = "intro_empresa", nullable = false)
    private String introduccion;
    
    @Column(name = "titulo_servicio_empresa", nullable = false)
    private String tituloServicio;
    
    @Column(name = "titulo_contacto_empresa", nullable = false)
    private String tituloContacto;
    
    @Column(name = "detalle_servicio_empresa", nullable = false, columnDefinition = "TEXT")
    private String detalleServicio;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    private Set<Imagen> imagen = new HashSet<Imagen>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    private Set<Reservacion> reservacion = new HashSet<Reservacion>();

    public Empresa() {
        
    }
    
    public Empresa(Integer id, String nombre, String denominacion, String telefono, String celular, String pais, String ciudad, String email, String direccion, String mapa, String intro, String tituloServicio, String tituloContacto, String detalleServicio) {
        this.id = id;
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.telefono = telefono;
        this.celular = celular;
        this.pais = pais;
        this.ciudad = ciudad;
        this.email = email;
        this.direccion = direccion;
        this.mapa = mapa;
        this.introduccion = intro;
        this.tituloServicio = tituloServicio;
        this.tituloContacto = tituloContacto;
        this.detalleServicio = detalleServicio;
    }

    public Empresa(String nombre, String denominacion, String telefono, String celular, String pais, String ciudad, String email, String direccion, String mapa, String introduccion, String tituloServicio, String tituloContacto, String detalleServicio) {
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.telefono = telefono;
        this.celular = celular;
        this.pais = pais;
        this.ciudad = ciudad;
        this.email = email;
        this.direccion = direccion;
        this.mapa = mapa;
        this.introduccion = introduccion;
        this.tituloServicio = tituloServicio;
        this.tituloContacto = tituloContacto;
        this.detalleServicio = detalleServicio;
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

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public String getTituloServicio() {
        return tituloServicio;
    }

    public void setTituloServicio(String tituloServicio) {
        this.tituloServicio = tituloServicio;
    }

    public String getTituloContacto() {
        return tituloContacto;
    }

    public void setTituloContacto(String tituloContacto) {
        this.tituloContacto = tituloContacto;
    }

    public String getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(String detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public Set<Imagen> getImagen() {
        return imagen;
    }

    public void setImagen(Set<Imagen> imagen) {
        this.imagen = imagen;
    }

    public Set<Reservacion> getReservacion() {
        return reservacion;
    }

    public void setReservacion(Set<Reservacion> reservacion) {
        this.reservacion = reservacion;
    }
}
