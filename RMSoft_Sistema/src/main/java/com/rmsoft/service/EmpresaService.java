/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import com.rmsoft.model.EmpresaModel;

/**
 *
 * @author bryan
 */
public interface EmpresaService {
    
    public abstract EmpresaModel getPrimeroEmpresa();
    
    public abstract void editarEmpresa(EmpresaModel empresa);
    
}
