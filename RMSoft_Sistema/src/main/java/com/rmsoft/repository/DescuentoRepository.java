/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.rmsoft.entity.Descuento;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("descuentoRepository")
public interface DescuentoRepository extends JpaRepository<Descuento, Serializable>{
    
    public abstract Descuento findById(Integer idDescuento);
}
