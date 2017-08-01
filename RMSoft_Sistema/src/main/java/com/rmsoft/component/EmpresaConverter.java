/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.entity.Empresa;
import com.rmsoft.model.EmpresaModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("empresaConverter")
public class EmpresaConverter {
    
    public EmpresaModel convertEmpresaToEmpresaModel(Empresa empresa) {

        EmpresaModel em = new EmpresaModel();

        em.setId(empresa.getId());
        em.setNombre(empresa.getNombre());
        em.setDenominacion(empresa.getDenominacion());
        em.setTelefono(empresa.getTelefono());
        em.setCelular(empresa.getCelular());
        em.setPais(empresa.getPais());
        em.setCiudad(empresa.getCiudad());
        em.setEmail(empresa.getEmail());
        em.setDireccion(empresa.getDireccion());
        em.setMapa(empresa.getMapa());
        em.setIntroduccion(empresa.getIntroduccion());
        em.setTituloServicio(empresa.getTituloServicio());
        em.setTituloContacto(empresa.getTituloContacto());
        em.setDetalleServicio(empresa.getDetalleServicio());
        return em;
    }
    
    public Empresa convertEmpresaModelToEmpresa(EmpresaModel empresaModel) {

        Empresa empresa = new Empresa();

        empresa.setId(empresaModel.getId());
        empresa.setNombre(empresaModel.getNombre());
        empresa.setDenominacion(empresaModel.getDenominacion());
        empresa.setTelefono(empresaModel.getTelefono());
        empresa.setCelular(empresaModel.getCelular());
        empresa.setPais(empresaModel.getPais());
        empresa.setCiudad(empresaModel.getCiudad());
        empresa.setEmail(empresaModel.getEmail());
        empresa.setDireccion(empresaModel.getDireccion());
        empresa.setMapa(empresaModel.getMapa());
        empresa.setIntroduccion(empresaModel.getIntroduccion());
        empresa.setTituloServicio(empresaModel.getTituloServicio());
        empresa.setTituloContacto(empresaModel.getTituloContacto());
        empresa.setDetalleServicio(empresaModel.getDetalleServicio());
        return empresa;
    }
}
