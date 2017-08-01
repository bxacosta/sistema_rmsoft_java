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

/**
 *
 * @author bryan
 */
@Entity
@Table(name = "detalle_reserva")
public class DetalleReserva {
    
    @Id
    @GeneratedValue
    @Column(name = "id_detalle_reserva")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion")
    private Reservacion reservacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;
    
    @Column(name = "precio_habitacion")
    private Double precioHabitacion;
    
    @Column(name = "sub_total_habitacion")
    private Double subTotalHabitacion;

    public DetalleReserva() {
        
    }
    
    public DetalleReserva(Integer id, Reservacion reservacion, Habitacion habitacion, Double precioHabitacion, Double subTotalHabitacion) {
        this.id = id;
        this.reservacion = reservacion;
        this.habitacion = habitacion;
        this.precioHabitacion = precioHabitacion;
        this.subTotalHabitacion = subTotalHabitacion;
    }

    public DetalleReserva(Reservacion reservacion, Habitacion habitacion, Double precioHabitacion, Double subTotalHabitacion) {
        this.reservacion = reservacion;
        this.habitacion = habitacion;
        this.precioHabitacion = precioHabitacion;
        this.subTotalHabitacion = subTotalHabitacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Double getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(Double precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public Double getSubTotalHabitacion() {
        return subTotalHabitacion;
    }

    public void setSubTotalHabitacion(Double subTotalHabitacion) {
        this.subTotalHabitacion = subTotalHabitacion;
    }
}
