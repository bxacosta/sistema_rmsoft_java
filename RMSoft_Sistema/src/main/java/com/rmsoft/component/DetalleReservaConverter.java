/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.entity.DetalleReserva;
import com.rmsoft.model.DetalleReservaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("detalleReservaConverter")
public class DetalleReservaConverter {
    
    @Autowired
    @Qualifier("habitacionConverter")
    private HabitacionConverter habitacionConverter;
    
    public DetalleReservaModel convertDetalleReservaToDetalleReservaModel(DetalleReserva detalleReserva) {
        
        DetalleReservaModel dr = new DetalleReservaModel();
        
        dr.setHabitacion(habitacionConverter.convertHabitacionToHabitacionModel(detalleReserva.getHabitacion()));
        dr.setPrecioHabitacion(detalleReserva.getPrecioHabitacion());
        dr.setSubTotalHabitacion(detalleReserva.getSubTotalHabitacion());
        
        return dr;
    }
}
 
