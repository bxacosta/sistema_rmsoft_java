/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author bryan
 */
public class DetalleReservaModel {
    
    private HabitacionModel habitacion = new HabitacionModel();
    
    private Double precioHabitacion;
    
    private Double subTotalHabitacion;

    public DetalleReservaModel() {
        
    }

    public DetalleReservaModel(HabitacionModel habitacion, Double precioHabitacion, Double subTotalHabitacion) {
        this.habitacion = habitacion;
        this.precioHabitacion = precioHabitacion;
        this.subTotalHabitacion = subTotalHabitacion;
    }

    public HabitacionModel getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionModel habitacion) {
        this.habitacion = habitacion;
    }

    public Double getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(Double precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public Double getSubTotalHabitacion() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.00", simbolos);        
        return Double.parseDouble(format.format(subTotalHabitacion));
    }

    public void setSubTotalHabitacion(Double subTotalHabitacion) {
        this.subTotalHabitacion = subTotalHabitacion;
    }

    @Override
    public String toString() {
        return "DetalleReservaModel{" + "precioHabitacion=" + precioHabitacion + ", subTotalHabitacion=" + subTotalHabitacion + '}';
    }

    
}
