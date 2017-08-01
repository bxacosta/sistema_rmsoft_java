/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.component.DescuentoConverter;
import com.rmsoft.entity.Descuento;
import com.rmsoft.model.DescuentoModel;
import com.rmsoft.repository.DescuentoRepository;
import com.rmsoft.service.DescuentoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author bryan
 */
@Service("descuentoService")
public class DescuentoServiceImpl implements DescuentoService{
    
    @Autowired
    @Qualifier("descuentoRepository")
    private DescuentoRepository descuentoRepository;
    
    @Autowired
    @Qualifier("descuentoConverter")
    private DescuentoConverter descuentoConverter;
    
    private static final Log LOG = LogFactory.getLog(DescuentoServiceImpl.class);
    
    @Override
    public DescuentoModel getPrimero() {
        Descuento descuento = descuentoRepository.findAll().get(0);
        if (descuento != null) {    
            return descuentoConverter.convertDescuentoToDescuentoModel(descuento);
        }
        return null;
    }

    @Override
    public void editarDescuento(DescuentoModel descuento) {        
        Descuento dm = descuentoConverter.convertDescuentoModelToDescuento(descuento);
        LOG.info("DESPUES DE ENVIAR A EDITAR A CONVERTIR: " + dm.toString());
        descuentoRepository.save(dm);
    }
    
    
    
}
