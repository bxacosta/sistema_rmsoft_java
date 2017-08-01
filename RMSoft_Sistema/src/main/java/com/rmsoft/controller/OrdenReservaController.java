/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.controller;

import com.rmsoft.constant.VistaConstant;
import com.rmsoft.entity.Descuento;
import com.rmsoft.model.DetalleReservaModel;
import com.rmsoft.model.EstadoHabitacion;
import com.rmsoft.model.EstadoReserva;
import com.rmsoft.model.HabitacionModel;
import com.rmsoft.model.ReservacionModel;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.repository.DescuentoRepository;
import com.rmsoft.service.EmpresaService;
import com.rmsoft.service.HabitacionService;
import com.rmsoft.service.ReservacionService;
import com.rmsoft.service.UsuarioService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author bryan
 */
@Controller
@SessionAttributes({"dat_sistema", "reservaModel", "usuario", "rol"})
@RequestMapping("/orden")
public class OrdenReservaController {
    
    private static final Log LOG = LogFactory.getLog(OrdenReservaController.class);
    
    @Autowired
    @Qualifier("empresaService")
    private EmpresaService empresaService;
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    
    @Autowired
    @Qualifier("habitacionService")
    private HabitacionService habitacionService;
    
    @Autowired
    @Qualifier("reservacionService")
    private ReservacionService reservacionService;
    
    @Autowired
    @Qualifier("descuentoRepository")
    private DescuentoRepository descuentoRepository;
    
    final String PARAMETRO_TITULO = "titulo_pagina";
    private final String TITULO_PAGINA = "Orden";
    private final String PARAMETRO_OPCION = "active_orden";
    
    private boolean hayUsuario = false;
    
    private final EstadoReserva esr = new EstadoReserva();
    private Integer idUsuarioOrden = null;
    private ReservacionModel reservaModel = new ReservacionModel();
    private UsuarioModel usuarioReserva = new UsuarioModel();
    private List<Integer> listIdHabitacion = new ArrayList<>();
    private List<DetalleReservaModel> listDetReservacion = new ArrayList<>();
    private Integer numHabitaciones = 0;
    
    @GetMapping("")
    public String mostrarVistaOrden(Model model) {
        if (!model.containsAttribute("dat_sistema") || !model.containsAttribute("usuario") || !model.containsAttribute("rol")) {
            return "redirect:/login/logincorrecto";
        }
        model = generalAtributeModel(model);
        model.addAttribute("hayusuario", hayUsuario);
        
        LOG.info("METHOD mostrarVistaOrden()  --  DATOS RESERVA: " + reservaModel.toString());
        
        LOG.info("VARIABLE ID USUARIO: " + idUsuarioOrden);
        
        //Inicializar el reservaModel
        reservaModel.setEstado(esr.getAbierta());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
        Date fechaSistema = new Date();

        reservaModel.setFecha(dateFormat.format(fechaSistema));
        /*********************************/
        
        model.addAttribute("usuarioReserva", usuarioReserva);
        model.addAttribute("reservaModel", reservaModel);
        model.addAttribute("listDetReserva", listDetReservacion);
        model.addAttribute("numHabitaciones", numHabitaciones);
        
        return VistaConstant.ORDEN;
    }
    
    @GetMapping("/agregarroom")
    public String agregarRoom(@RequestParam(name = "idHabitacion", required = false) Integer idHabitacion,
            @RequestParam(name = "idUsuario", required = false) Integer idUsuario,
            RedirectAttributes redir) {
        
        LOG.info("METHOD agregarRoom()  --  ID HABITACION FROM VIEW: " + idHabitacion + " -- ID USAURIO FROM VIEW: " + idUsuario);
        UsuarioModel userm;
        
        
        if (idUsuario != null) {
            idUsuarioOrden = idUsuario;
            hayUsuario =  true;
            
            userm  = usuarioService.buscarPorId(idUsuario);
            
            if (userm.getNumDocumento() == null || userm.getDireccion() == null ) {
                redir.addFlashAttribute("incompleto", true); 
                return "redirect:/principal";
            } else {
                if (userm.getNumDocumento().isEmpty() || userm.getDireccion().isEmpty()) {
                    redir.addFlashAttribute("incompleto", true); 
                    return "redirect:/principal";
                }
            }
            
            usuarioReserva = userm;
        }
        if (idHabitacion != null) {            
            if (!listIdHabitacion.contains(idHabitacion)) {
                listIdHabitacion.add(idHabitacion);
                
                HabitacionModel a = habitacionService.buscarPorId(idHabitacion);
                DetalleReservaModel drm = new DetalleReservaModel(a, 0.0, 0.0);
                listDetReservacion.add(drm);
                
                redir.addFlashAttribute("agregada", 1);
            } else {
                redir.addFlashAttribute("agregada", 2);
            }
            
        }
        return "redirect:/principal";
    }
    
    @GetMapping("/agregarcliente")
    public String agregarIdCliente(@RequestParam(name = "idUsuario", required = false) Integer idUsuario) {
        
        LOG.info("METHOD agregarIdCliente() -- ID FORM VIEW: " + idUsuario);
        
        if (idUsuario != null) {
            idUsuarioOrden = idUsuario;
            hayUsuario =  true;
            
            usuarioReserva = usuarioService.buscarPorId(idUsuario);
        }
        return "redirect:/orden";
    }
    
    
    
    @PostMapping("/calcular")
    public String accionCalcular(@ModelAttribute(name = "reservaModel") ReservacionModel rModel, 
            RedirectAttributes redir) {
        
        LOG.info("METODO CALCULAR");

        reservaModel.setModo(rModel.getModo());
        reservaModel.setFechaArribo(rModel.getFechaArribo());
        reservaModel.setFechaSalida(rModel.getFechaSalida());
        
        if (listIdHabitacion.isEmpty()) {
            redir.addFlashAttribute("error", 4);
            return "redirect:/orden"; 
        }
        
        if (rModel.getFechaSalida().isEmpty() || rModel.getFechaArribo().isEmpty() ) {
           redir.addFlashAttribute("error", 1);
           return "redirect:/orden"; 
        }
        
        ReservacionModel resModel = reservacionService.calcular(rModel, listDetReservacion);
         
        if (resModel == null) {
            redir.addFlashAttribute("error", 2);
            return "redirect:/orden";
        } else {
            
            reservaModel = resModel;
            listDetReservacion = resModel.getDetReserva();
            numHabitaciones = listDetReservacion.size();
            Double sbt = 0.0;
            
            for (int i = 0; i < listDetReservacion.size(); i++) {
                sbt += listDetReservacion.get(i).getSubTotalHabitacion();
            }
            reservaModel.setSubTotal(sbt);
            
            Descuento datosDescuento = descuentoRepository.findAll().get(0);
            Date fechanow = new Date();
            
            Integer descuento = -1;
            LOG.info("ANTES DE ENTRAR A LAS VALIDACIONES");
            if (datosDescuento.getEstado().equalsIgnoreCase("activar") && (datosDescuento.getProcentaje() > 0 || datosDescuento.getProcentaje() != null) ) {
                LOG.info("LA VADICACION DE ACTIVO MAYOR --- " + datosDescuento.getEstado() + "  " + datosDescuento.getProcentaje());
                if (fechanow.after(datosDescuento.getFechaInicio()) && datosDescuento.getFechaFin().after(fechanow)) {
                    LOG.info("PASA LA VALIDACION DE FECHAS");
                    if (datosDescuento.getCondicion() != null) {
                        LOG.info("LA CONDICION ES: " +  datosDescuento.getCondicion());
                        switch  (datosDescuento.getCondicion()) {
                            case "todos":
                                descuento = datosDescuento.getProcentaje();
                                break;
                            case "mayor":
                                if (datosDescuento.getValorCondicion() > 0) {
                                    if (sbt > datosDescuento.getValorCondicion()) {
                                        descuento = datosDescuento.getProcentaje();
                                    }
                                } else {
                                    descuento = -1;
                                }
                                break;
                            case "menor":
                                if (datosDescuento.getValorCondicion() > 0) {
                                    if (sbt < datosDescuento.getValorCondicion()) {
                                        descuento = datosDescuento.getProcentaje();
                                    }
                                } else {
                                    descuento = -1;
                                }
                                break;
                        }
                    }
                }
            }
            if (descuento > 0) {
                reservaModel.setValorDescuento(descuento);
                Double total = sbt - ((double)descuento*sbt)/100;
                reservaModel.setTotal(total);
            } else {
                reservaModel.setValorDescuento(0);
                reservaModel.setTotal(sbt);
            }
        }
        return "redirect:/orden";
    }
    
    @GetMapping("/borrar")
    public String accionBorrar(@RequestParam(name = "idl", required = false) Integer idl) {
        
        LOG.info("METHOD accionBorrar()  --  ID HABITACION FROM VIEW: " + idl);
        
        for (int i = 0; i < listDetReservacion.size(); i++) {
            if (listDetReservacion.get(i).getHabitacion().getId().equals(idl)) {
                listDetReservacion.remove(i);
            }
        }
        listIdHabitacion.remove(idl);
        
        return "redirect:/orden";
    }
    
    @GetMapping("/cancelar")
    public String accionCancelar() {
      
        resertOrden();
        
        hayUsuario = false;
        return "redirect:/orden";
    }
    
    
    @PostMapping("/crearreserva")
    public String accionCrearReserva(@ModelAttribute(name = "reservaModel") ReservacionModel rModel, 
            RedirectAttributes redir) {
        
        LOG.info("METHOD accionCrearReserva()  --  DATOS RESERVA: " + reservaModel.toString());
        
        if (listIdHabitacion.isEmpty()) {
            redir.addFlashAttribute("error", 4);
            return "redirect:/orden"; 
        }
        
        if (idUsuarioOrden == null) {
            redir.addFlashAttribute("error", 3);
            return "redirect:/orden";
        }
        
        reservaModel.setEmpresa(empresaService.getPrimeroEmpresa());
        reservaModel.setUsuario(usuarioService.buscarPorId(idUsuarioOrden));

        EstadoHabitacion esth = new EstadoHabitacion();
        for (int i = 0; i < listDetReservacion.size(); i++) {
            if (!listDetReservacion.get(i).getHabitacion().getEstado().equalsIgnoreCase(esth.getDisponible())) {
                redir.addFlashAttribute("error", 5);
                return "redirect:/orden";
            }
        }
        
        String farribo = estructurarFecha(reservaModel.getFechaArribo());
        String fsalida = estructurarFecha(reservaModel.getFechaSalida());
        
        reservaModel.setFechaArribo(farribo);
        reservaModel.setFechaSalida(fsalida);
        
        reservacionService.crearReserva(reservaModel);
        
        redir.addFlashAttribute("guardado", true);
        resertOrden();
        
        return "redirect:/reservacion";
    }
    
    private Model generalAtributeModel(Model model) {
        model.addAttribute(PARAMETRO_TITULO, TITULO_PAGINA);
        model.addAttribute(PARAMETRO_OPCION, true);
        model.addAttribute("listusuarios", usuarioService.listarUsers());
        model.addAttribute("listallusuarios", usuarioService.listarTodos());
        return model;
    }
    
    private void resertOrden() {
        idUsuarioOrden = null;
        reservaModel = new ReservacionModel();
        usuarioReserva = new UsuarioModel();
        listIdHabitacion = new ArrayList<>();
        listDetReservacion = new ArrayList<>();
        hayUsuario = false;
    }
    
    private String estructurarFecha(String fecha) {
        String[] partes = fecha.split("T");
        
        String[] partesFecha = partes[0].split("-");
        
        String fechaCompleta = partesFecha[2] + "-" + partesFecha[1] + "-" + partesFecha[0] + " " + partes[1];
        
        return fechaCompleta;
    }
}
