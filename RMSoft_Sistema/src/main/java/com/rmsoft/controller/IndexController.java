/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.DatosSIstema;
import com.rmsoft.model.EmpresaModel;
import com.rmsoft.service.EmpresaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema"})
public class IndexController {
    
    private static final Log LOG = LogFactory.getLog(IndexController.class);

    @Autowired
    @Qualifier("empresaService")
    private EmpresaService empresaService;
    
    private final String PARAMETRO_TITULO = "titulo_pagina";
    private final String PARAMETRO_EMPRESA = "empresa";
    private final String PARAMETRO_DATOS_SISTEMA = "dat_sistema";

    @GetMapping("/")
    public String mostrarVistaInicio(Model model) {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            return "redirect:/principal";
        }
        EmpresaModel em = empresaService.getPrimeroEmpresa();
        String TITULO_PAGINA = em.getDenominacion() + " " + em.getNombre();
        
        DatosSIstema ds = new DatosSIstema(em);
        
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_EMPRESA, em);
        model.addAttribute(PARAMETRO_DATOS_SISTEMA, ds);
        
        return VistaConstant.INICIO;
    }
}
