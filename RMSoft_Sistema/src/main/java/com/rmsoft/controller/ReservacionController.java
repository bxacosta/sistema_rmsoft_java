/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.ReservacionModel;
import com.rmsoft.model.Rol;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.service.UsuarioService;
import com.rmsoft.service.impl.ReservacionServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema", "usuario", "rol"})
@RequestMapping("/reservacion")
public class ReservacionController {
    
    private static final Log LOG = LogFactory.getLog(ReservacionController.class);
    
    @Autowired
    @Qualifier("reservacionService")
    private ReservacionServiceImpl reservacionService;
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    
    final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Reservaciones";
    private final String PARAMETRO_OPCION = "active_reservacion";
    private int idReservaMostrar = 0;
    private int accion = 0;
    private List<ReservacionModel> listRese = new ArrayList<>();
    
    @GetMapping("")
    public String mostrarVistaReservacion(Model model) {
        if (!model.containsAttribute("dat_sistema") || !model.containsAttribute("usuario") || !model.containsAttribute("rol")) {
            return "redirect:/login/logincorrecto";
        }
        model = generalAtributeModel(model);
        
        User usuario = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioModel usuarioModel = usuarioService.buscarPorNombre(usuario.getUsername()); 
        
        Rol rol = new Rol();
        if (usuarioModel.getRol().equals(rol.getUser())) {
            listRese = reservacionService.listarDeUsuaio(usuarioModel.getId(), true);
        } else {
            listRese = reservacionService.listarTodo(true);
        }
        
        model.addAttribute("listReservaciones", listRese);
        
        ReservacionModel resModel = new ReservacionModel();
        
        if (listRese.size() > 0 ) {
            if (idReservaMostrar == 0) {
                resModel = listRese.get(listRese.size() - 1);
            } else {
                resModel = reservacionService.buscarPorId(idReservaMostrar);
            }
        } else {
            resModel = null;
        }
        
        model.addAttribute("reservacion", resModel);
        model.addAttribute("editReservacionModel", resModel);
        
        return VistaConstant.RESERVACION;
    }
    
    @GetMapping("/ver")
    public String accionVer(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionVer() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/reservacion";
        }
        idReservaMostrar = id;
        accion = 0;
        return "redirect:/reservacion";
    }
    
    @GetMapping("/cancelar")
    public String accionCancelar() {
        accion = 0;
        return "redirect:/reservacion";
    }

    @GetMapping("/reporte")
    public String accionImprimir(@RequestParam(name = "id", required = false) Integer id, Model model) {
        LOG.info("METHOD accionVer() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/reservacion";
        }
        model.addAttribute(PARAMETRO_TITULO, "Reporte");
        
        ReservacionModel resM = reservacionService.buscarPorId(id);
        model.addAttribute("reservacion", resM);
        return VistaConstant.REPORTE;
    }
    
    @GetMapping("/editar")
    public String accionEditar(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionVer() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/reservacion";
        }
        idReservaMostrar = id;
        accion = 1;
        return "redirect:/reservacion";
    }
    
    @PostMapping("/editarestado")
    public String editarEstado(@ModelAttribute(name = "editReservacionModel") ReservacionModel resModel) {
        
        
        
        ReservacionModel resMo = reservacionService.buscarPorId(resModel.getId());
        resMo.setEstado(resModel.getEstado());
        
        LOG.info("METHOD editarEstado() -- DATOS RESERVACION: " + resMo.toString());
        
        reservacionService.editarReserva(resMo);
        
        accion = 0;
        return "redirect:/reservacion";
    }
    
    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute("accion", accion);
        return model;
    }
}
