/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import com.rmsoft.model.DescuentoModel;

/**
 *
 * @author bryan
 */
public interface DescuentoService {
    
    public abstract DescuentoModel getPrimero();
    
    public abstract void editarDescuento(DescuentoModel empresa);
}
