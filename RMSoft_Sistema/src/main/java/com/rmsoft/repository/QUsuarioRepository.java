/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.rmsoft.entity.QUsuario;
import com.rmsoft.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bryan
 */
@Repository("qUsuarioRepository")
public class QUsuarioRepository {
    
    private final QUsuario qUsuario = QUsuario.usuario1;
    
    @PersistenceContext
    private EntityManager em;
    
    public Usuario buscarUsuario(String nombreUsuario) {
        JPAQuery<Usuario> query = new JPAQuery<>(em);
        
        return query.select(qUsuario).from(qUsuario).where(qUsuario.usuario.eq(nombreUsuario)).fetchOne();
    }
    
}
