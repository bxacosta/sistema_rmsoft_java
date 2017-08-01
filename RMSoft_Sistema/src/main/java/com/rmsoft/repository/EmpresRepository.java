/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.rmsoft.entity.Empresa;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("empresaRepository")
public interface EmpresRepository extends JpaRepository<Empresa, Serializable>{
    
    public abstract Empresa findById(Integer idEmpresa);
    
}
