/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

/**
 *
 * @author bryan
 */
public class DatosSIstema {
    
    private final Integer idEmpresa;
    private final String nombreEmpresa;
    private final String inicialesEmpresa;

    public DatosSIstema(EmpresaModel em) {
        this.idEmpresa = em.getId();
        this.nombreEmpresa = em.getNombre();
        this.inicialesEmpresa = (String.valueOf(em.getDenominacion().charAt(0)) + String.valueOf(em.getNombre().charAt(0))).toUpperCase();
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getInicialesEmpresa() {
        return inicialesEmpresa;
    }   
}
