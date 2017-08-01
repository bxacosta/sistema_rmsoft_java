/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.entity.TipoDocumento;
import com.rmsoft.repository.TipoDocumentoRepository;
import com.rmsoft.service.TipoDocumentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author bryan
 */
@Service("tipoDocumentoService")
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
    
    @Autowired
    @Qualifier("tipoDocumentoRepository")
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumento> listarTodos() {
        return tipoDocumentoRepository.findAll();
    }
    
    
}
