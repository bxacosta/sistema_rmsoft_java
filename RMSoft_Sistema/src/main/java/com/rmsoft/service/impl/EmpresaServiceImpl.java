/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.component.EmpresaConverter;
import com.rmsoft.entity.Empresa;
import com.rmsoft.model.EmpresaModel;
import com.rmsoft.repository.EmpresRepository;
import com.rmsoft.service.EmpresaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author bryan
 */
@Service("empresaService")
public class EmpresaServiceImpl implements EmpresaService{
    
    private static final Log LOG = LogFactory.getLog(EmpresaServiceImpl.class);
    
    @Autowired
    @Qualifier("empresaRepository")
    private EmpresRepository empresaRepository;
    
    @Autowired
    @Qualifier("empresaConverter")
    private EmpresaConverter empresaConverter;

    @Override
    public EmpresaModel getPrimeroEmpresa() {
        Empresa empresa = empresaRepository.findAll().get(0);
        if (empresa != null) {    
            return empresaConverter.convertEmpresaToEmpresaModel(empresa);
        }
        return null;
    }
    
    
    @Override
    public void editarEmpresa(EmpresaModel empresa) {
        empresaRepository.save(empresaConverter.convertEmpresaModelToEmpresa(empresa));
    }
}
