/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.rmsoft.entity.DetalleReserva;
import com.rmsoft.entity.Reservacion;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("detalleReservaRepository")
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva, Serializable>{
    
    public abstract DetalleReserva findById(Integer idDetalleReserva);
    
    public abstract DetalleReserva findByReservacion(Reservacion reservacion);
}
