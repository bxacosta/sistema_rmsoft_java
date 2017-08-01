/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.Rol;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.service.TipoDocumentoService;
import com.rmsoft.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema", "usuario", "rol"})
@RequestMapping("/usuario")
public class UsuarioController {
    
    private static final Log LOG = LogFactory.getLog(UsuarioController.class);
    
    final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Usuarios";
    private final String PARAMETRO_OPCION = "active_usuario";
    private int accion = 0;
    private int idUsuarioMostrar = 0;
    private boolean nuevo = false;
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    
    @Autowired
    @Qualifier("tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;
    
    @GetMapping("")
    public String mostrarVistaUsuario(Model model) {
        if (!model.containsAttribute("dat_sistema") || !model.containsAttribute("usuario") || !model.containsAttribute("rol")) {
            return "redirect:/login/logincorrecto";
        }
        
        model = generalAtributeModel(model);
        
        if (usuarioService.contar() == 0 && nuevo == false) {
            return VistaConstant.HABITACION;
        }
        
        UsuarioModel usuarioM = new UsuarioModel();
        
        if (idUsuarioMostrar == 0) {
            usuarioM = usuarioService.getUltimo();
        } else {
            usuarioM = usuarioService.buscarPorId(idUsuarioMostrar);
        }
        
        if (nuevo) {
            usuarioM = new UsuarioModel();
        }
        
        model.addAttribute("usuarioModel", usuarioM);
        nuevo = false;
        
        return VistaConstant.USUARIO;
    }
    
    @GetMapping("/ver")
    public String accionVer(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionVer() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/usuario";
        }
        
        accion = 0;
        idUsuarioMostrar = id;
        return "redirect:/usuario";
    }
    
    @GetMapping("/editar")
    public String accionEditar(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionEditar() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/usuario";
        }
        
        accion = 1;
        idUsuarioMostrar = id;
        return "redirect:/usuario";
    }
    
    @GetMapping("/nuevo")
    public String accionNuevo() {
        accion = 2;
        nuevo = true;
        return "redirect:/usuario";
    }
    
    @GetMapping("/cancelar")
    public String accionCancelar() {
        accion = 0;
        return "redirect:/usuario";
    }
    
    @GetMapping("/guardarusuario")
    public String redirectUsuario() {
        accion = 0;
        return "redirect:/usuario";
    }
    
    @PostMapping("/guardarusuario")
    public  ModelAndView guardarUsuario(@Valid @ModelAttribute(name = "usuarioModel") UsuarioModel userModel,
            BindingResult bindingResult, 
            RedirectAttributes redir) {
        
        LOG.info("METHOD guardarUsuario() INICIO -- USUARIO: " + userModel.toString());
        
        ModelAndView mav = generalAtributeMAV();
        mav.setViewName(VistaConstant.USUARIO);
        accion = 1;
        boolean nuevo = false;
        
        if (userModel.getId() == null) {
           nuevo = true; 
        }
        
        UsuarioModel datosUsuario = new UsuarioModel();
        
        if (!nuevo) {
            datosUsuario = usuarioService.buscarPorId(userModel.getId());
        }
        
        
        // SI el usuario no ha escrito nada en los campos de password
        if (userModel.getPassword().isEmpty() && !nuevo) {
            userModel.setPassword(datosUsuario.getPassword());
        }
        
        if (bindingResult.hasErrors()) {
            mav.addObject("error", 1);
            LOG.info("TIENES ERRORES DE VALUDADCION");
        } else {
            boolean cambiar = true;
            LOG.info("DATOS USUARIO: " + userModel.toString());
            if (!userModel.getUsuario().equals(datosUsuario.getUsuario())) {
                LOG.info("EL CAMPO EMAIL HA SIDO CAMBIADO");
                if (usuarioService.existeUsuarioNombre(userModel.getUsuario())) {
                    LOG.info("YA EXISTE OTRO USUARIO CON ESE EMAIL");
                    mav.addObject("error", 3);
                    cambiar = false;
                } else {
                    cambiar =  true;
                }
            }
            if (cambiar) {
                if (nuevo) {
                    usuarioService.crearUsuario(userModel);
                } else {
                    usuarioService.editarUsuario(datosUsuario, userModel);
                }
                redir.addFlashAttribute("guardado", true);
                LOG.info("DATOS USUARIO FINAL: " + userModel.toString());
                accion = 0;
                return new ModelAndView("redirect:/usuario");
            }
        }
        return mav;
    }
    
    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute("accion", accion);
        model.addAttribute("listusuarios", usuarioService.listarUsers());
        model.addAttribute("listallusuarios", usuarioService.listarTodos());
        model.addAttribute("tpDocumento", tipoDocumentoService.listarTodos());
        
        List<String> listRol = new ArrayList<>();
        Rol r = new Rol();
        listRol.add(r.getAdmin());
        listRol.add(r.getResep());
        listRol.add(r.getUser());
        model.addAttribute("listRol", listRol);
        return model;
    }
    
    
    private ModelAndView generalAtributeMAV() {
        ModelAndView mav = new ModelAndView();
        
        mav.addObject(PARAMETRO_TITULO, TITULO_PAGINA);
        mav.addObject(PARAMETRO_OPCION, true);
        mav.addObject("accion", accion);
        mav.addObject("listusuarios", usuarioService.listarUsers());
        mav.addObject("listallusuarios", usuarioService.listarTodos());
        mav.addObject("tpDocumento", tipoDocumentoService.listarTodos());
        
        List<String> listRol = new ArrayList<>();
        Rol r = new Rol();
        listRol.add(r.getAdmin());
        listRol.add(r.getResep());
        listRol.add(r.getUser());
        mav.addObject("listRol", listRol);
        
        return mav;
    }
}
