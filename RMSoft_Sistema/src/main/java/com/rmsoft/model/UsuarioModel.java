/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

import com.rmsoft.constant.ExpValidacionConstant;
import com.rmsoft.constant.ImageConstant;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author bryan
 */
public class UsuarioModel {
    
    private Integer id;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.EMAIL)
    private String usuario;
    
    
    private Boolean habilitado; 
    
    private String password;
    
    private String passwordConfirm;
    
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String rol;
    
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String tipoDocumento;
    
    @Pattern(regexp = ExpValidacionConstant.NUMEROS)
    private String numDocumento;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String nombre;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String apellido;
    
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String direccion;
    
    private String imagen;
    
    public UsuarioModel() {
        Rol r = new Rol();
        this.rol = r.getUser();
        this.habilitado = true;
        this.imagen = ImageConstant.DEFAULT_USER_IMAGE;
    }

    public UsuarioModel(Integer id, String usuario, Boolean habilitado, String password, String passwordConfirm, String rol, String tipoDocumento, String numDocumento, String nombre, String apellido, String direccion, String imagen) {
        this.id = id;
        this.usuario = usuario;
        this.habilitado = habilitado;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.rol = rol;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public UsuarioModel(String usuario, Boolean habilitado, String password, String passwordConfirm, String rol, String tipoDocumento, String numDocumento, String nombre, String apellido, String direccion, String imagen) {
        this.usuario = usuario;
        this.habilitado = habilitado;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.rol = rol;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.imagen = imagen;
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

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" + "id=" + id + ", usuario=" + usuario + ", habilitado=" + habilitado + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", rol=" + rol + ", tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", imagen=" + imagen + '}';
    }
}
