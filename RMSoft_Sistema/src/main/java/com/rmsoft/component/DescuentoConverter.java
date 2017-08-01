/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.entity.Descuento;
import com.rmsoft.model.DescuentoModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("descuentoConverter")
public class DescuentoConverter {
    
    public DescuentoModel convertDescuentoToDescuentoModel(Descuento descuento) {
        DescuentoModel dModel = new DescuentoModel();
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.00", simbolos);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        dModel.setId(descuento.getId());
        dModel.setEstado(descuento.getEstado());
        dModel.setProcentaje(descuento.getProcentaje());
        dModel.setNombre(descuento.getNombre());
        dModel.setCondicion(descuento.getCondicion());
        
        if (descuento.getValorCondicion() !=  null) {
            dModel.setValorCondicion(format.format(descuento.getValorCondicion()));
        } else {
            dModel.setValorCondicion(null);
        }  
        if (descuento.getFechaInicio() !=  null) {
            dModel.setFechaInicio(dateFormat.format(descuento.getFechaInicio()));
        } else {
            dModel.setFechaInicio(null);
        } 
        if (descuento.getFechaFin() !=  null) {
            dModel.setFechaFin(dateFormat.format(descuento.getFechaFin()));
        } else {
            dModel.setFechaFin(null);
        } 
        
        return dModel;
    }
    
    public Descuento convertDescuentoModelToDescuento(DescuentoModel dMofdel) {
        Descuento descuento = new Descuento();
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        descuento.setId(dMofdel.getId());
        descuento.setEstado(dMofdel.getEstado());
        descuento.setProcentaje(dMofdel.getProcentaje());
        descuento.setNombre(dMofdel.getNombre());
        descuento.setCondicion(dMofdel.getCondicion());
        
        if (dMofdel.getValorCondicion() != null) {
            if (!dMofdel.getValorCondicion().isEmpty()) {
                descuento.setValorCondicion(Double.parseDouble(dMofdel.getValorCondicion()));
            }
        } else {
            descuento.setValorCondicion(0.0);
        }
        
        Date finicio = null;
        Date ffin = null;
        
        if (dMofdel.getFechaInicio() != null) {
            try {
                finicio = dateFormat.parse(dMofdel.getFechaInicio());
            } catch (ParseException ex) {}
        } 
        if (dMofdel.getFechaFin() != null) {
            try {
                ffin = dateFormat.parse(dMofdel.getFechaFin());
            } catch (ParseException ex) {}
        }
        descuento.setFechaInicio(finicio);
        descuento.setFechaFin(ffin);
        
        return descuento;
    }
}
