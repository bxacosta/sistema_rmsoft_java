/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bryan
 */
@Entity
@Table(name = "mensaje")
public class Mensaje {
    
    @Id
    @GeneratedValue
    @Column(name = "id_mensaje")
    private Integer id;
    
    @Column(name = "nombre_emisor_mensaje", nullable = false)
    private String nombreEmisor;
    
    @Column(name = "email_emisor_mensaje", nullable = false)
    private String emailEmisor;
    
    @Column(name = "texto_mensaje", nullable = false)
    private String texto;
    
    @Column(name="fecha_mensaje")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Column(name = "leido_mensaje")
    private Boolean leido;
    
    public Mensaje() {
        
    }
    
    public Mensaje(Integer id, String nombreEmisor, String emailEmisor, String texto, Boolean leido, Date fecha) {
        this.id = id;
        this.nombreEmisor = nombreEmisor;
        this.emailEmisor = emailEmisor;
        this.texto = texto;
        this.leido = leido;
        this.fecha = fecha;
    }

    public Mensaje(String nombreEmisor, String emailEmisor, String texto, Boolean leido, Date fecha) {
        this.nombreEmisor = nombreEmisor;
        this.emailEmisor = emailEmisor;
        this.texto = texto;
        this.leido = leido;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getEmailEmisor() {
        return emailEmisor;
    }

    public void setEmailEmisor(String emailEmisor) {
        this.emailEmisor = emailEmisor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
