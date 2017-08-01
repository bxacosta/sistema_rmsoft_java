/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.ImageConstant;
import com.rmsoft.constant.VistaConstant;
import com.rmsoft.model.EstadoHabitacion;
import com.rmsoft.model.HabitacionModel;
import com.rmsoft.service.HabitacionService;
import com.rmsoft.service.impl.ImagenServiceImpl;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/habitacion")
public class HabitacionController {
    
    private static final Log LOG = LogFactory.getLog(HabitacionController.class);
    
    private final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Habitación";
    private final String PARAMETRO_OPCION = "active_habitacion";
    private final String absolutePathImgRooms = ImageConstant.ABSOLUTE_PATH.concat("/").concat(ImageConstant.ROOMS);
    private int accion = 0;
    private int idHabitacionMostrar = 0;
    private boolean nuevo = false;
    
    @Autowired
    @Qualifier("habitacionService")
    private HabitacionService habitacionService;
    
    @Autowired
    @Qualifier("imagenService")
    private ImagenServiceImpl imagenService;
    
    @PreAuthorize("hasAuthority('administrador') or hasAuthority('resepcionista')")
    @GetMapping("")
    public String mostrarVistaHabitacion(Model model) {
        if (!model.containsAttribute("dat_sistema") || !model.containsAttribute("usuario") || !model.containsAttribute("rol")) {
            return "redirect:/login/logincorrecto";
        }
        model = generalAtributeModel(model);
        
        List<HabitacionModel> listHabitaciones = habitacionService.listarTodo(true);
        
        model.addAttribute("listHabitaciones", listHabitaciones);
        
        if (habitacionService.contar() == 0 && nuevo == false) {
            return VistaConstant.HABITACION;
        }
        
        HabitacionModel habitacionM = new HabitacionModel();
        
        if (idHabitacionMostrar == 0) {
            habitacionM = habitacionService.getUltimo();
        } else {
            habitacionM = habitacionService.buscarPorId(idHabitacionMostrar);
        }
        
        if (nuevo) {
            habitacionM = new HabitacionModel();
        }
        
        model.addAttribute("habitacionModel", habitacionM);
        nuevo = false;
        
        return VistaConstant.HABITACION;
    }
    
    @PreAuthorize("hasAuthority('administrador') or hasAuthority('resepcionista')")
    @GetMapping("/ver")
    public String accionVer(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionVer() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/habitacion";
        }
        
        accion = 0;
        idHabitacionMostrar = id;
        return "redirect:/habitacion";
    }
    
    @PreAuthorize("hasAuthority('administrador') or hasAuthority('resepcionista')")
    @GetMapping("/editar")
    public String accionEditar(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionEditar() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/habitacion";
        }
        
        accion = 1;
        idHabitacionMostrar = id;
        return "redirect:/habitacion";
    }
    
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/nuevo")
    public String accionNuevo() {
        accion = 2;
        nuevo = true;
        return "redirect:/habitacion";
    }
    
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/eliminar")
    public String accionEliminar(@RequestParam(name = "id", required = false) Integer id) {
        LOG.info("METHOD accionEliminar() --- ID FROM VIWE: " + id);
        if (id == null) {
            return "redirect:/habitacion";
        }
        accion = 0;
        idHabitacionMostrar = 0;
        habitacionService.eliminarHabitacionPorId(id);
        return "redirect:/habitacion";
    }
    
    @GetMapping("/cancelar")
    public String accionCancelar() {
        accion = 0;
        return "redirect:/habitacion";
    }
    
    @GetMapping("/guardarhabitacion")
    public String redirectHabitacion() {
        accion = 0;
        return "redirect:/habitacion";
    }
    
    @PostMapping("/guardarhabitacion")
    public  ModelAndView guardarHabitacion(@Valid @ModelAttribute(name = "habitacionModel") HabitacionModel habitacionModel,
            BindingResult bindingResult, 
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2,
            RedirectAttributes redir) {
        
        LOG.info("METHOD guardarHabitacion() -- HABITACION: " + habitacionModel.toString());
        
        ModelAndView mav = generalAtributeMAV();
        mav.setViewName(VistaConstant.HABITACION);
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
        
        if (bindingResult.hasErrors()) {
            mav.addObject("error", 1);
            LOG.info("TIENES ERRORES DE VALUDADCION");
        } else {
            boolean cambiar = true;
            String imagenAnterior = habitacionModel.getImagen();
            if (canbiarImagen) {
                String nuevaImagen = imagenService.guardarImagen(file, absolutePathImgRooms);
                if (nuevaImagen != null) {
                    habitacionModel.setImagen(nuevaImagen);
                    if (!imagenAnterior.equals(ImageConstant.DEFAULT_ROOM_IMAGE)) {
                       imagenService.eliminarImagen(imagenAnterior, absolutePathImgRooms); 
                    }
                    redir.addFlashAttribute("nueva_imagen", true);
                } else {
                    mav.addObject("error", 2);
                    cambiar = false;
                }
            }
            if (cambiar) {
                habitacionService.guardarHabitacion(habitacionModel);
                redir.addFlashAttribute("guardado", true);
                
                accion = 0;
           
                LOG.info("DATOS HABITACION DESPUES DE EDITAR " + habitacionModel.toString());
                return new ModelAndView("redirect:/habitacion");
            } 
        }
        
        return mav;
    }
    
    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute("accion", accion);
        
        List<String> estados = new ArrayList<>();
        EstadoHabitacion eh = new EstadoHabitacion();
        estados.add(eh.getDisponible());
        estados.add(eh.getOcupada());
        estados.add(eh.getMantenimiento());
        estados.add(eh.getAnulada());
        
        model.addAttribute("estados", estados);
        return model;
    }
    
    private ModelAndView generalAtributeMAV() {
        ModelAndView mav = new ModelAndView();
        mav.addObject(PARAMETRO_TITULO, TITULO_PAGINA);
        mav.addObject(PARAMETRO_OPCION, true);
        mav.addObject("accion", accion);
        
        List<String> estados = new ArrayList<>();
        EstadoHabitacion eh = new EstadoHabitacion();
        estados.add(eh.getDisponible());
        estados.add(eh.getOcupada());
        estados.add(eh.getMantenimiento());
        estados.add(eh.getAnulada());
        
        mav.addObject("estados", estados);
        mav.addObject("existe", true);
        return mav;
    }
}
