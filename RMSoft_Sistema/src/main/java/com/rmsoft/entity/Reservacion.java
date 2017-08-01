/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bryan
 */
@Entity
@Table(name = "reservacion")
public class Reservacion {

    @Id
    @GeneratedValue
    @Column(name = "id_reservacion")
    private Integer id;
    
    @Column(name = "estado_reservacion", nullable = false)
    private String estado;
    
    @Column(name = "modo_reservacion", nullable = false)
    private String modo;
    
    @Column(name = "fecha_arribo_reservacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaArribo;
    
    @Column(name = "fecha_salida_reservacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    
    @Column(name = "usuario_encargado_reservacion")
    private Integer usuarioEncargado;
    
    @Column(name = "fecha_reservacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Column(name = "sub_total_reservacion", nullable = false)
    private Double subTotal;
    
    @Column(name = "valor_descuento_reservacion")
    private Integer valorDescuento;

    @Column(name = "total_reservacion", nullable = false)
    private Double total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservacion")
    private Set<DetalleReserva> detalleReserva = new HashSet<DetalleReserva>();

    public Reservacion() {
    }

    public Reservacion(Integer id, String estado, String modo, Date fechaArribo, Date fechaSalida, Integer usuarioEncargado, Date fecha, Double subTotal, Integer valorDescuento, Double total, Empresa empresa, Usuario usuario) {
        this.id = id;
        this.estado = estado;
        this.modo = modo;
        this.fechaArribo = fechaArribo;
        this.fechaSalida = fechaSalida;
        this.usuarioEncargado = usuarioEncargado;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.valorDescuento = valorDescuento;
        this.total = total;
        this.empresa = empresa;
        this.usuario = usuario;
    }

    public Reservacion(String estado, String modo, Date fechaArribo, Date fechaSalida, Integer usuarioEncargado, Date fecha, Double subTotal, Integer valorDescuento, Double total, Empresa empresa, Usuario usuario) {
        this.estado = estado;
        this.modo = modo;
        this.fechaArribo = fechaArribo;
        this.fechaSalida = fechaSalida;
        this.usuarioEncargado = usuarioEncargado;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.valorDescuento = valorDescuento;
        this.total = total;
        this.empresa = empresa;
        this.usuario = usuario;
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

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public Date getFechaArribo() {
        return fechaArribo;
    }

    public void setFechaArribo(Date fechaArribo) {
        this.fechaArribo = fechaArribo;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getUsuarioEncargado() {
        return usuarioEncargado;
    }

    public void setUsuarioEncargado(Integer usuarioEncargado) {
        this.usuarioEncargado = usuarioEncargado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(Integer valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<DetalleReserva> getDetalleReserva() {
        return detalleReserva;
    }

    public void setDetalleReserva(Set<DetalleReserva> detalleReserva) {
        this.detalleReserva = detalleReserva;
    } 
}
