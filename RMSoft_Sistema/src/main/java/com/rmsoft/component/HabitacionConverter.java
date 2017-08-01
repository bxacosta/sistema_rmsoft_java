/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.entity.Habitacion;
import com.rmsoft.model.HabitacionModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("habitacionConverter")
public class HabitacionConverter {
    
    private static final Log LOG = LogFactory.getLog(HabitacionConverter.class);  
    
    public HabitacionModel convertHabitacionToHabitacionModel(Habitacion habitacion) {

        HabitacionModel habitacionModel = new HabitacionModel();
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.00", simbolos);

        habitacionModel.setId(habitacion.getId());
        habitacionModel.setNumero(habitacion.getNumero());
        habitacionModel.setCapacidad(habitacion.getCapacidad().toString());
        habitacionModel.setTipo(habitacion.getTipo());
        habitacionModel.setEstado(habitacion.getEstado());
        habitacionModel.setUbicacion(habitacion.getUbicacion());
        if (habitacion.getPrecioDia() != null) {
            habitacionModel.setPrecioDia(format.format(habitacion.getPrecioDia()));
        }
        if (habitacion.getPrecioHora() != null) {
            habitacionModel.setPrecioHora(format.format(habitacion.getPrecioHora()));
        }
        
        habitacionModel.setDescripccion(habitacion.getDescripccion());
        habitacionModel.setImagen(habitacion.getImagen());
        
        return habitacionModel;
    }
    
    public Habitacion convertHabitacionModelToHabitacion(HabitacionModel habitacionModel) {

        Habitacion habitacion = new Habitacion();

        habitacion.setId(habitacionModel.getId());
        habitacion.setNumero(habitacionModel.getNumero());
        habitacion.setCapacidad(Integer.parseInt(habitacionModel.getCapacidad()));
        habitacion.setTipo(habitacionModel.getTipo().toLowerCase());
        habitacion.setEstado(habitacionModel.getEstado().toLowerCase());
        habitacion.setUbicacion(habitacionModel.getUbicacion().toLowerCase());
        
        if (habitacionModel.getPrecioDia() != null) {
            if (!habitacionModel.getPrecioDia().isEmpty()) {
                habitacion.setPrecioDia(Double.parseDouble(habitacionModel.getPrecioDia().replaceAll(",",".")));
            } else {
               habitacion.setPrecioDia(null); 
            }
            
        }         
        if (habitacionModel.getPrecioHora() != null) {
            if (!habitacionModel.getPrecioHora().isEmpty()) {
                habitacion.setPrecioHora(Double.parseDouble(habitacionModel.getPrecioHora().replaceAll(",",".")));
            } else {
                habitacion.setPrecioHora(null);
            }
        } 
         
        habitacion.setDescripccion(habitacionModel.getDescripccion());
        habitacion.setImagen(habitacionModel.getImagen());
        
        return habitacion;
    }
    
}
