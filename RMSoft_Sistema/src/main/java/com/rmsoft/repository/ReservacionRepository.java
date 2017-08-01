/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.rmsoft.entity.Reservacion;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("reservacionRepository")
public interface ReservacionRepository extends JpaRepository<Reservacion, Serializable>{
    
    public abstract Reservacion findById(Integer idReservacion);
}
