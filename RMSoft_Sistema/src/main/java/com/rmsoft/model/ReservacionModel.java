/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class ReservacionModel {
    
    private Integer id;
    
    private String estado;

    private String modo;
    
    private String fechaArribo;
    
    private String fechaSalida;

    private Integer usuarioEncargado;
    
    private String fecha;
    
    private Double subTotal;

    private Integer valorDescuento;

    private Double total;
    
    private EmpresaModel empresa = new EmpresaModel();
    
    private UsuarioModel usuario = new UsuarioModel();
    
    private List<DetalleReservaModel> detReserva = new ArrayList<>();
        

    public ReservacionModel(){
        
    }

    public ReservacionModel(Integer id, String estado, String modo, String fechaArribo, String fechaSalida, Integer usuarioEncargado, String fecha, Double subTotal, Integer valorDescuento, Double total, EmpresaModel empresa, UsuarioModel usuario, List<DetalleReservaModel> detReserva) {
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
        this.detReserva = detReserva;
    }

    public ReservacionModel(String estado, String modo, String fechaArribo, String fechaSalida, Integer usuarioEncargado, String fecha, Double subTotal, Integer valorDescuento, Double total, EmpresaModel empresa, UsuarioModel usuario, List<DetalleReservaModel> detReserva) {
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
        this.detReserva = detReserva;
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

    public String getFechaArribo() {
        return fechaArribo;
    }

    public void setFechaArribo(String fechaArribo) {
        this.fechaArribo = fechaArribo;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getUsuarioEncargado() {
        return usuarioEncargado;
    }

    public void setUsuarioEncargado(Integer usuarioEncargado) {
        this.usuarioEncargado = usuarioEncargado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public List<DetalleReservaModel> getDetReserva() {
        return detReserva;
    }

    public void setDetReserva(List<DetalleReservaModel> detReserva) {
        this.detReserva = detReserva;
    }

    @Override
    public String toString() {
        return "ReservacionModel{" + "id=" + id + ", estado=" + estado + ", modo=" + modo + ", fechaArribo=" + fechaArribo + ", fechaSalida=" + fechaSalida + ", usuarioEncargado=" + usuarioEncargado + ", fecha=" + fecha + ", subTotal=" + subTotal + ", valorDescuento=" + valorDescuento + ", total=" + total + ", empresa=" + empresa.getId() + ", usuario=" + usuario.getId() + ", detReserva=" + detReserva.size() + '}';
    }

}
