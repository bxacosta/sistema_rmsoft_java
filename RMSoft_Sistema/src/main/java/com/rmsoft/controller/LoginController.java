/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.DatosSIstema;
import com.rmsoft.model.EmpresaModel;
import com.rmsoft.model.Rol;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.service.EmpresaService;
import com.rmsoft.service.UsuarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema", "usuario", "rol"})
@RequestMapping("/login")
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);
    
    private final String PARAMETRO_DATOS_SISTEMA = "dat_sistema";
    private final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Login";
    private final String PARAMETRO_OPCION = "active_login";
    private final String PARAMETRO_ROL = "rol";
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    
    @Autowired
    @Qualifier("empresaService")
    private EmpresaService empresaService;
    
    @GetMapping("")
    public String mostrarVistaLogin(
            @RequestParam(name = "error", required = false) String error, 
            @RequestParam(name = "salir", required = false) String salir, 
            Model model) {
        
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            return "redirect:/principal";
        }
        
        EmpresaModel em = empresaService.getPrimeroEmpresa();
        DatosSIstema ds = new DatosSIstema(em);
        
        model.addAttribute(PARAMETRO_DATOS_SISTEMA, ds);
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute("error", error);
        model.addAttribute("salir", salir);
        
        return VistaConstant.LOGIN;
    }
    
    @GetMapping("/logincorrecto")
    public String loginCorrecto(Model model){
        
        User usuario = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioModel usuarioModel = usuarioService.buscarPorNombre(usuario.getUsername());     
        
        EmpresaModel em = empresaService.getPrimeroEmpresa();
        DatosSIstema ds = new DatosSIstema(em);
        
        Rol rol = new Rol(); 
        
        LOG.info("METHOD loginCorrecto() -- USUARIO: " + usuarioModel.toString());
        
        model.addAttribute(PARAMETRO_ROL, rol);
        model.addAttribute(PARAMETRO_DATOS_SISTEMA, ds);
        model.addAttribute("usuario", usuarioModel);
        return "redirect:/principal";
    }
}
