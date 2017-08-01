/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.EstadoHabitacion;
import com.rmsoft.model.HabitacionModel;
import com.rmsoft.service.HabitacionService;
import com.rmsoft.service.ReservacionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/principal")
public class PrincipalController {
    
    private static final Log LOG = LogFactory.getLog(PrincipalController.class);
    
    private final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Principal";
    private final String PARAMETRO_OPCION = "active_principal";
    
    @Autowired
    @Qualifier("habitacionService")
    private HabitacionService habitacionService;
    
    @Autowired
    @Qualifier("reservacionService")
    private ReservacionService reservacionService;
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String mostrarVistaPrincipal(Model model) {
        if (!model.containsAttribute("usuario") || !model.containsAttribute("usuario") || !model.containsAttribute("usuario")) {
            return "redirect:/login/logincorrecto";
        }
        model = generalAtributeModel(model);
        
        if (habitacionService.contar() == 0) {
            model.addAttribute("existe", false);
            return VistaConstant.PIRNCIPAL;
        }
        model.addAttribute("existe", true);
        
        List<HabitacionModel> listHabitaciones = habitacionService.listarHabilitadas(true);
        Date fecha = new Date();
        
        for (int i = 0; i < listHabitaciones.size(); i++) {
            listHabitaciones.get(i).setReservaciones(reservacionService.getReservacionesDeHabitacionDesde(listHabitaciones.get(i).getId(), fecha));
        }
        
        model.addAttribute("listHabitaciones", listHabitaciones);
        
        return VistaConstant.PIRNCIPAL;
    }

    @PreAuthorize("hasAuthority('administrador') or hasAuthority('resepcionista')")
    @PostMapping("/editarestado")
    public String editarEstado(@ModelAttribute(name = "habitacionModel") HabitacionModel habModel,
            @RequestParam(name = "idHabitacion", required = false) Integer idHabitacion) {
        
        LOG.info("METHOD editarEstado() -- ID ROOM FROM VIEW: " + idHabitacion + " -- ESTADO FROM VIEW: " + habModel.getEstado());
        
        HabitacionModel am = habitacionService.buscarPorId(idHabitacion);
        am.setEstado(habModel.getEstado());
        
        LOG.info("METHOD editarEstado() -- HABITACION MODEL: " + am.toString());
        habitacionService.editarHabitacion(am);
       
        return "redirect:/principal";
    }
    
    
    
    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        
        List<String> estados = new ArrayList<>();
        EstadoHabitacion eh = new EstadoHabitacion();
        estados.add(eh.getDisponible());
        estados.add(eh.getOcupada());
        estados.add(eh.getMantenimiento());
        estados.add(eh.getAnulada());
       
        model.addAttribute("estados", estados);
        model.addAttribute("habModel", new HabitacionModel());
        
        return model;
    }
} 
