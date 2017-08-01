/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.rmsoft.entity.Habitacion;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("habitacionRepository")
public interface HabitacionRepository extends JpaRepository<Habitacion, Serializable>{
    
    public abstract Habitacion findById(Integer idHabitacion);
    
}
