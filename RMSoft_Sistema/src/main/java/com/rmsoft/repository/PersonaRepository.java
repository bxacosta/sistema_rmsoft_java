/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.rmsoft.entity.Persona;
import com.rmsoft.entity.Usuario;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Serializable>{
    
    public abstract Persona findByIdUsuario(Usuario usuario);
    
}
