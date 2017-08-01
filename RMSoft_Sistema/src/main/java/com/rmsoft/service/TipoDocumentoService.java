/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import com.rmsoft.entity.TipoDocumento;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface TipoDocumentoService {
    
    public abstract List<TipoDocumento> listarTodos();
}
