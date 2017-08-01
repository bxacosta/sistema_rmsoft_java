/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

import com.rmsoft.constant.ExpValidacionConstant;
import com.rmsoft.constant.ImageConstant;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author bryan
 */
public class HabitacionModel {

    private Integer id;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String numero;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.ENTERO_POSITIVO)
    private String capacidad;
    
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String tipo;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_LIMPIO)
    private String estado;
    
    @NotEmpty()
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String ubicacion;
    
    @Pattern(regexp = ExpValidacionConstant.PRECIO)
    private String precioDia;
    
    @Pattern(regexp = ExpValidacionConstant.PRECIO)
    private String precioHora;
    
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String descripccion;
    
    @Pattern(regexp = ExpValidacionConstant.TEXTO_ALFA_N)
    private String imagen;
    
    private List<ReservacionModel> reservaciones = new ArrayList<>();
    
    public HabitacionModel() {
        this.imagen = ImageConstant.DEFAULT_ROOM_IMAGE;
    }

    public HabitacionModel(Integer id, String numero, String capacidad, String tipo, String estado, String ubicacion, String precioDia, String precioHora, String descripccion, String imagen) {
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

    public HabitacionModel(String numero, String capacidad, String tipo, String estado, String ubicacion, String precioDia, String precioHora, String descripccion, String imagen) {
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

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
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

    public String getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(String precioDia) {
        this.precioDia = precioDia;
    }

    public String getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(String precioHora) {
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

    public List<ReservacionModel> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<ReservacionModel> reservaciones) {
        this.reservaciones = reservaciones;
    }

    @Override
    public String toString() {
        return "HabitacionModel{" + "id=" + id + ", numero=" + numero + ", capacidad=" + capacidad + ", tipo=" + tipo + ", estado=" + estado + ", ubicacion=" + ubicacion + ", precioDia=" + precioDia + ", precioHora=" + precioHora + ", descripccion=" + descripccion + ", imagen=" + imagen + '}';
    }
   
}
