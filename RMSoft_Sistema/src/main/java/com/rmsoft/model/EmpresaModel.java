/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

import com.rmsoft.constant.ExpValidacionConstant;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author bryan
 */
public class EmpresaModel {
    
    private Integer id;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_BASICO)
    private String nombre;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String denominacion;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String introduccion;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.NUMEROS)
    private String telefono;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.NUMEROS)
    private String celular;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String pais;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String ciudad;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.EMAIL)
    private String email;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String direccion;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_BASICO)
    private String tituloServicio;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_BASICO)
    private String tituloContacto;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String detalleServicio;
    
    @NotEmpty()
    private String mapa;

    public EmpresaModel() {
    }

    public EmpresaModel(Integer id, String nombre, String denominacion, String introduccion, String telefono, String celular, String pais, String ciudad, String email, String direccion, String tituloServicio, String tituloContacto, String detalleServicio, String mapa) {
        this.id = id;
        this.nombre = nombre;
        this.denominacion = denominacion;
        this.introduccion = introduccion;
        this.telefono = telefono;
        this.celular = celular;
        this.pais = pais;
        this.ciudad = ciudad;
        this.email = email;
        this.direccion = direccion;
        this.tituloServicio = tituloServicio;
        this.tituloContacto = tituloContacto;
        this.detalleServicio = detalleServicio;
        this.mapa = mapa;
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

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
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

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    @Override
    public String toString() {
        return "EmpresaModel{" + "id=" + id + ", nombre=" + nombre + ", denominacion=" + denominacion + ", introduccion=" + introduccion + ", telefono=" + telefono + ", celular=" + celular + ", pais=" + pais + ", ciudad=" + ciudad + ", email=" + email + ", direccion=" + direccion + ", tituloServicio=" + tituloServicio + ", tituloContacto=" + tituloContacto + ", detalleServicio=" + detalleServicio + ", mapa=" + mapa + '}';
    }
}
