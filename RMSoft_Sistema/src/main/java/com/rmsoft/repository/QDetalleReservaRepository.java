/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.rmsoft.entity.DetalleReserva;
import com.rmsoft.entity.QDetalleReserva;
import com.rmsoft.entity.Reservacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("qDetalleReservaRepository")
public class QDetalleReservaRepository {
    
    private final QDetalleReserva qDetalleReserva = QDetalleReserva.detalleReserva;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<DetalleReserva> buscarReservaciones(Reservacion reservacion) {
        JPAQuery<DetalleReserva> query = new JPAQuery<>(em);
        
        return query.select(qDetalleReserva).from(qDetalleReserva).where(qDetalleReserva.reservacion.eq(reservacion)).fetch();
    }
}
