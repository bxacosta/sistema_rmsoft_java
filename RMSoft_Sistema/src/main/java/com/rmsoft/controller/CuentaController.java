/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.ImageConstant;
import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.service.TipoDocumentoService;
import com.rmsoft.service.UsuarioService;
import com.rmsoft.service.impl.ImagenServiceImpl;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema", "usuario", "rol"})
@RequestMapping("/cuenta")
public class CuentaController {
    
    private static final Log LOG = LogFactory.getLog(CuentaController.class);
    
    private final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Principal";
    private final String PARAMETRO_OPCION = "active_cuenta";
    private final String absolutePathImgUseer = ImageConstant.ABSOLUTE_PATH.concat("/").concat(ImageConstant.USERS);
    private int accion = 0;
    
    private UsuarioModel usuarioM;
    private Integer idUsuario;
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    
    @Autowired
    @Qualifier("imagenService")
    private ImagenServiceImpl imagenService;
    
    @Autowired
    @Qualifier("tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;
    
    @GetMapping("")
    public String mostrarVistaCuenta(Model model) {
        if (!model.containsAttribute("dat_sistema") || !model.containsAttribute("usuario") || !model.containsAttribute("rol")) {
            return "redirect:/login/logincorrecto";
        }
        model = generalAtributeModel(model);
        
        User us = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        usuarioM = usuarioService.buscarPorNombre(us.getUsername());
        
        if (usuarioM == null) {
           usuarioM = usuarioService.buscarPorId(idUsuario);
        }
        model.addAttribute("usuarioModel", usuarioM);
        model.addAttribute("usuario", usuarioM);
        
        return VistaConstant.CUENTA;
    }
    
    @GetMapping("/editar")
    public String accionEditar() {
        accion = 1;
        return "redirect:/cuenta";
    }
    
    @GetMapping("/cancelar")
    public String accionCancelar() {
        accion = 0;
        return "redirect:/cuenta";
    }
    
    @GetMapping("/editarmiusuario")
    public String redirectCuenta() {
        accion = 0;
        return "redirect:/cuenta";
    }
    
    @PostMapping("/editarmiusuario")
    public ModelAndView editarUsuario(@Valid @ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel, 
            BindingResult bindingResult, 
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2,
            RedirectAttributes redir) {
        
        LOG.info("DATOS USUARIO POST INICIO" + usuarioM.toString());
        
        ModelAndView mav = generalAtributeMAV();
        mav.setViewName(VistaConstant.CUENTA);
        
        accion = 1;
        
        /**********Coindiciones para deicidir si se cambia la imagen************/
        MultipartFile file = null;
        if (file1.isEmpty()) {
            if (!file2.isEmpty()) {
                file = file2;
            }
        } else if (file2.isEmpty()) {
            if (!file1.isEmpty()) {
                file = file1;
            }
        } else {
            file = file1;
        }
        boolean canbiarImagen = false;
        if (!file1.isEmpty()) {
            canbiarImagen = true;
        }
        /***********************************************************************/
        
        // SI el usuario no ha escrito nada en los campos de password
        if (usuarioModel.getPassword().isEmpty() || usuarioModel.getPasswordConfirm().isEmpty()) {
            usuarioModel.setPassword(usuarioM.getPassword());
        }
        
        if (bindingResult.hasErrors()) {
            mav.addObject("error", 1);
            LOG.info("TIENES ERRORES DE VALUDADCION");
        } else {
            boolean cambiar = true;
            if (!usuarioModel.getPassword().equals(usuarioM.getPassword())) {
                LOG.info("EL CAMPO CLAVE HA SIDO CAMBIADO");
                BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
                if (!pe.matches(usuarioModel.getPasswordConfirm(), usuarioM.getPassword())) {
                    LOG.info("EL PASSWORD ANTERIOR Y ACTUAL NO COINCIDEN");
                    mav.addObject("error", 2);
                    cambiar = false;
                } else {
                    cambiar = true;
                }
            }
            if (!usuarioModel.getUsuario().equals(usuarioM.getUsuario())) {
                LOG.info("EL CAMPO EMAIL HA SIDO CAMBIADO");
                if (usuarioService.existeUsuarioNombre(usuarioModel.getUsuario())) {
                    LOG.info("YA EXISTE OTRO USUARIO CON ESE EMAIL");
                    mav.addObject("error", 3);
                    cambiar = false;
                } else {
                    idUsuario = usuarioM.getId();
                    cambiar = true;
                }
            } 
            if (canbiarImagen) {
                String nuevaImagen = imagenService.guardarImagen(file, absolutePathImgUseer);
                if (nuevaImagen != null) {
                    usuarioModel.setImagen(nuevaImagen);
                    if (!(usuarioM.getImagen().equals(ImageConstant.DEFAULT_ADMIN_IMAGE) || usuarioM.getImagen().equals(ImageConstant.DEFAULT_USER_IMAGE))) {
                       imagenService.eliminarImagen(usuarioM.getImagen(), absolutePathImgUseer); 
                    }
                } else {
                    mav.addObject("error", 4);
                    cambiar = false;
                }
            }
            if (cambiar) {
                if (!usuarioModel.getHabilitado().equals(usuarioM.getHabilitado())) {
                    usuarioModel.setHabilitado(usuarioM.getHabilitado());
                }
                usuarioService.editarUsuario(usuarioM, usuarioModel);
                redir.addFlashAttribute("editado", true);
                
                accion = 0;
           
                LOG.info("DATOS USUARIO DESPUES DE EDITAR " + usuarioModel.toString());
                return new ModelAndView("redirect:/cuenta");
            } 
        }
        LOG.info("DATOS USUARIO FINAL INICIO" + usuarioM.toString());
        return mav;
    }
    
    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute("accion", accion);
        model.addAttribute("tpDocumento", tipoDocumentoService.listarTodos());
        return model;
    }
    
    private ModelAndView generalAtributeMAV() {
        ModelAndView mav = new ModelAndView();
        mav.addObject(PARAMETRO_TITULO, TITULO_PAGINA);
        mav.addObject(PARAMETRO_OPCION, true);
        mav.addObject("accion", accion);
        mav.addObject("tpDocumento", tipoDocumentoService.listarTodos());
        return mav;
    }
}
