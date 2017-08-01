/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.rmsoft.entity.QReservacion;
import com.rmsoft.entity.Reservacion;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("qReservacionRepository")
public class QReservacionRepository {
    
    private final QReservacion qReservacion = QReservacion.reservacion;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Reservacion> getReservacionesDepuesDe(Date fecha) {
        JPAQuery<Reservacion> query = new JPAQuery<>(em);
        
        return query.select(qReservacion).from(qReservacion).where(qReservacion.fechaSalida.after(fecha)).fetch();
    }
}
