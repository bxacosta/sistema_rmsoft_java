/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.DatosSIstema;
import com.rmsoft.model.DescuentoModel;
import com.rmsoft.model.EmpresaModel;
import com.rmsoft.service.DescuentoService;
import com.rmsoft.service.EmpresaService;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
@SessionAttributes({"dat_sistema", "usuario", "rol"})
@RequestMapping("/configuracion")
@PreAuthorize("hasAuthority('administrador')")
public class ConfiguracionController {
    
    private static final Log LOG = LogFactory.getLog(ConfiguracionController.class);
    
    final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Configuración";
    private final String PARAMETRO_OPCION = "active_configuracion";
    private final String PARAMETRO_EMPRESA = "empresa";
    private final String PARAMETRO_DATOS_SISTEMA = "dat_sistema";
    
    private int accion = 0;
    private EmpresaModel em;
    private DescuentoModel dm;
    
    @Autowired
    @Qualifier("empresaService")
    private EmpresaService empresaService;
    
    @Autowired
    @Qualifier("descuentoService")
    private DescuentoService descuentoService;
    
    @GetMapping("")
    public String mostrarVistaCuenta(Model model) {
        if (!model.containsAttribute("dat_sistema") || !model.containsAttribute("usuario") || !model.containsAttribute("rol")) {
            return "redirect:/login/logincorrecto";
        }

        em = empresaService.getPrimeroEmpresa();
        dm = descuentoService.getPrimero();
        
        model = generalAtributeModel(model);
        model.addAttribute("empresaModel", em);
        model.addAttribute("descuentomodel", dm);
        
        return VistaConstant.CONFIGURACION;
    }
    
    @GetMapping("/editdescuento")
    public String accionEditarDescuento() {
        accion = 1;
        return "redirect:/configuracion";
    }
    
    @GetMapping("/editempresa")
    public String accionEditarEmpresa() {
        accion = 2;
        return "redirect:/configuracion";
    }
    
    @GetMapping("/cancelar")
    public String accionCancelar() {
        accion = 0;
        return "redirect:/configuracion";
    }
    
    @GetMapping({"/editardescuento", "/editarempresa"})
    public String redirectCuenta() {
        accion = 0;
        return "redirect:/configuracion";
    }
    
    @PostMapping("/editarempresa")
    public ModelAndView editarEmpresa(@Valid @ModelAttribute(name = "empresaModel") EmpresaModel empresaModel,
            BindingResult bindingResult, 
            RedirectAttributes redir) {
        
        LOG.info("DATOS EMPRESA DESDE VISTA: " + empresaModel.toString());
        
        ModelAndView mav = generalAtributeMAV();
        mav.setViewName(VistaConstant.CONFIGURACION);
        
        accion = 2;
        
        if (bindingResult.hasErrors()) {
            mav.addObject("error", 1);
            LOG.info("TIENES ERRORES DE VALUDADCION");
        } else {
            empresaService.editarEmpresa(empresaModel);
            
            accion = 0;
            
            redir.addFlashAttribute("editado", true);
            return new ModelAndView("redirect:/configuracion");
        }
        return mav;
    }
    
    @PostMapping("/editardescuento")
    public ModelAndView editarDescuento(@Valid @ModelAttribute(name = "descuentomodel") DescuentoModel descuentoModel,
            BindingResult bindingResult, 
            RedirectAttributes redir) {
        
        LOG.info("DATOS DESCUENTO DESDE VISTA: " + descuentoModel.toString());
        
        ModelAndView mav = generalAtributeMAV();
        mav.setViewName(VistaConstant.CONFIGURACION);
        
        accion = 1;
        
        if (bindingResult.hasErrors()) {
            mav.addObject("error", 1);
            LOG.info("TIENES ERRORES DE VALIDADCION");
        } else {
            LOG.info("DATOS DESCUENTO: " + descuentoModel.toString());
            descuentoService.editarDescuento(descuentoModel);
            accion = 0;
            
            redir.addFlashAttribute("editado", true);
            return new ModelAndView("redirect:/configuracion");
        }
        return mav;
    }

    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute(PARAMETRO_EMPRESA, em);
        
        DatosSIstema ds = new DatosSIstema(em);
        model.addAttribute(PARAMETRO_DATOS_SISTEMA, ds);
        
        model.addAttribute("accion", accion);
        return model;
    }
    
    private ModelAndView generalAtributeMAV() {
        ModelAndView mav = new ModelAndView();
        mav.addObject(PARAMETRO_TITULO, TITULO_PAGINA);
        mav.addObject(PARAMETRO_OPCION, true);
        
        mav.addObject("accion", accion);
        return mav;
    }

}
