/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.Rol;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.service.UsuarioService;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema"})
@RequestMapping("/registro")
public class RegistroController {
    
    private static final Log LOG = LogFactory.getLog(RegistroController.class);
    
    private final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Registro";
    private final String PARAMETRO_OPCION = "active_registro";
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    

    @GetMapping("")
    public String mostrarVistaInicio(Model model) {  
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            return "redirect:/principal";
        }
        if (!model.containsAttribute("dat_sistema")) {
            return "redirect:/";
        }
        
        model = generalAtributeModel(model);
        model.addAttribute("usuarioModel", new UsuarioModel());
        
        return VistaConstant.REGISTRO;
    }
    
    @GetMapping("/crearusuario")
    public String redirectRegistro() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            return "redirect:/principal";
        } 
        return "redirect:/registro";
    }
    
    @PostMapping("/crearusuario")
    public ModelAndView nuevoUsuario(@Valid @ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel, 
            BindingResult bindingResult, 
            RedirectAttributes redir) {
        
        LOG.info("METODO: nuevoUsuario -- PARAMS: " + usuarioModel.toString());
        
        ModelAndView mav = generalAtributeMAV();
        mav.setViewName(VistaConstant.REGISTRO);
        
        if (bindingResult.hasErrors() || usuarioModel.getPassword().isEmpty()) {
            mav.addObject("error", 1);
        } else {
            if (!usuarioModel.getPassword().equals(usuarioModel.getPasswordConfirm())) {
                mav.addObject("error", 2);
            } else if (usuarioService.existeUsuarioNombre(usuarioModel.getUsuario())) {
                mav.addObject("error", 3);
            } else {
                Rol rol = new Rol();
                usuarioModel.setRol(rol.getUser());
                UsuarioModel um = usuarioService.crearUsuario(usuarioModel);
                if (um != null) {
                    redir.addFlashAttribute("creado", true);
                    LOG.info("METODO: nuevoUsuario --NUEVO USUARIO: " + um.toString());
                } else {
                    redir.addFlashAttribute("creado", false);
                }
                return new ModelAndView("redirect:/login");
            }
        }
        return mav;
    }
    
    private Model generalAtributeModel(Model model) {        
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        return model;
    }
    
    private ModelAndView generalAtributeMAV() {
        ModelAndView mav = new ModelAndView();
        mav.addObject(PARAMETRO_TITULO, TITULO_PAGINA);
        mav.addObject(PARAMETRO_OPCION, true);
        return mav;
    }      
}
