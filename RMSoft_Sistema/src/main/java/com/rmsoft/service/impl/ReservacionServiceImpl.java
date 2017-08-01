/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.component.HabitacionConverter;
import com.rmsoft.component.ReservacionConverter;
import com.rmsoft.entity.DetalleReserva;
import com.rmsoft.entity.Habitacion;
import com.rmsoft.entity.Reservacion;
import com.rmsoft.model.DetalleReservaModel;
import com.rmsoft.model.HabitacionModel;
import com.rmsoft.model.ReservacionModel;
import com.rmsoft.repository.DetalleReservaRepository;
import com.rmsoft.repository.QDetalleReservaRepository;
import com.rmsoft.repository.QReservacionRepository;
import com.rmsoft.repository.ReservacionRepository;
import com.rmsoft.service.HabitacionService;
import com.rmsoft.service.ReservacionService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author bryan
 */
@Service("reservacionService")
public class ReservacionServiceImpl implements ReservacionService{
    
    private static final Log LOG = LogFactory.getLog(ReservacionServiceImpl.class);
    
    @Autowired
    @Qualifier("habitacionService")
    private HabitacionService habitacionService;
    
    @Autowired
    @Qualifier("reservacionRepository")
    private ReservacionRepository reservacionRepository;
    
    @Autowired
    @Qualifier("detalleReservaRepository")
    private DetalleReservaRepository detalleReservaRepository;
    
    @Autowired
    @Qualifier("reservacionConverter")
    private ReservacionConverter reservacionConverter;
    
    @Autowired
    @Qualifier("habitacionConverter")
    private HabitacionConverter habitacionConverter;
    
    @Autowired
    @Qualifier("qReservacionRepository")
    private QReservacionRepository qReservacionRepository;
    
    @Autowired
    @Qualifier("qDetalleReservaRepository")
    private QDetalleReservaRepository qDetalleReservaRepository;
    
    @Override
    public ReservacionModel calcular(ReservacionModel rmodel, List<DetalleReservaModel> listDetReserva) {
        
        LOG.info("DESDE ReservacionServiceImpl() --- DATOS MODEL: " + rmodel.toString());
        
        String[] partesFechaArribo;
        String[] partesFechaSalida;
        
        String fechaArribo;
        String fechaSalida;
        
         //2017-07-11T23:23
        if (!rmodel.getFechaArribo().isEmpty() && !rmodel.getFechaSalida().isEmpty()) {
            partesFechaArribo = rmodel.getFechaArribo().split("T");
            partesFechaSalida = rmodel.getFechaSalida().split("T");
            
            fechaArribo = partesFechaArribo[0].concat(" ").concat(partesFechaArribo[1]);        
            fechaSalida = partesFechaSalida[0].concat(" ").concat(partesFechaSalida[1]);  
            
            LOG.info("FECHAS ARRIBO Y SALIDA: " + fechaArribo + " ---- " +  fechaSalida);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            Date arribo;
            Date salida;
            try {
                arribo = dateFormat.parse(fechaArribo);
                salida = dateFormat.parse(fechaSalida);
            } catch (ParseException ex) {
                return null;
            }
            
            String modo = rmodel.getModo();
            
            Date fechaSistema = new Date();
            
            // Valida que la fecha seleccionada este despues de la fecha actual
            if (arribo.after(fechaSistema) && salida.after(arribo)) {
                LOG.info("ENTRA VALIDACION");
                if (modo.equalsIgnoreCase("dia")) {
                    
                    double dias = daysBetween(arribo.getTime(),salida.getTime());
                    
                    for (int i = 0; i < listDetReserva.size(); i++) {
                        String precioDia = listDetReserva.get(i).getHabitacion().getPrecioDia();
                        
                        if (precioDia != null) {
                            listDetReserva.get(i).setPrecioHabitacion(Double.parseDouble(precioDia));
                            listDetReserva.get(i).setSubTotalHabitacion((double)Math.round(dias) * Double.parseDouble(precioDia));
                            if (listDetReserva.get(i).getSubTotalHabitacion() == 0) {
                                listDetReserva.get(i).setSubTotalHabitacion(Double.parseDouble(precioDia));
                            }
                        } else {
                            return null;
                        }
                    }
                    LOG.info("ENTRO A RESERVA POR DIA ---- # DET RESERVA: " + listDetReserva.toString());
                }
                if (modo.equalsIgnoreCase("hora")) {
                    double horas = hoursBetween(arribo.getTime(),salida.getTime());
                    
                    for (int i = 0; i < listDetReserva.size(); i++) {
                        String precioHora = listDetReserva.get(i).getHabitacion().getPrecioHora();
                        
                        if (precioHora != null) {
                            listDetReserva.get(i).setPrecioHabitacion(Double.parseDouble(precioHora));
                            listDetReserva.get(i).setSubTotalHabitacion((double)Math.round(horas) * Double.parseDouble(precioHora));
                            if (listDetReserva.get(i).getSubTotalHabitacion() == 0) {
                                listDetReserva.get(i).setSubTotalHabitacion(Double.parseDouble(precioHora));
                            }
                        } else {
                            return null;
                        }
                    }
                    LOG.info("ENTRO A RESERVA POR HORA ---- # DET RESERVA: " + listDetReserva.toString());
                }
                rmodel.setDetReserva(listDetReserva);
                return rmodel;
            }
        }
        LOG.info("EJECUTA TODA LA APP");
        return null;
    }

    @Override
    public ReservacionModel crearReserva(ReservacionModel rmodel) {
        
        Reservacion reservacion = reservacionRepository.save(reservacionConverter.convertReservacionModelToReservacion(rmodel));
        
        List<DetalleReservaModel> listDR = rmodel.getDetReserva();
        Habitacion habitacion;

        for (int i = 0; i < listDR.size(); i++) {
            habitacion = habitacionConverter.convertHabitacionModelToHabitacion(listDR.get(i).getHabitacion());
            detalleReservaRepository.save(new DetalleReserva(reservacion, habitacion, listDR.get(i).getPrecioHabitacion() , listDR.get(i).getSubTotalHabitacion()));
        }
        return reservacionConverter.convertReservacionToReservacionModel(reservacion);
    }

    @Override
    public void editarReserva(ReservacionModel rmodel) {
        
        Reservacion res = reservacionRepository.findById(rmodel.getId());
        
        res.setEstado(rmodel.getEstado());
        reservacionRepository.save(res);
    }
       
    private double daysBetween(long t1, long t2) {
        return (double) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }
    
    private double hoursBetween(long t1, long t2) {
        return (double) ((t2 - t1) / (1000 * 60 * 60));
    }

    @Override
    public List<ReservacionModel> listarTodo(boolean ordenar) {
        List<ReservacionModel> listrm = new ArrayList<>();
        List<Reservacion> listr = reservacionRepository.findAll();
        
        for (Reservacion reservacion : listr) {
            listrm.add(reservacionConverter.convertReservacionToReservacionModel(reservacion));
        }
        
        if (ordenar) {
           Collections.sort(listrm, new Comparator<ReservacionModel>(){
               @Override
               public int compare(ReservacionModel o1, ReservacionModel o2) {
                   return o1.getFecha().compareTo(o2.getFecha());
               }
            }); 
        }
        
        return listrm;
    }

    @Override
    public List<ReservacionModel> listarDeUsuaio(Integer idUsuario, boolean ordenar) {
        List<ReservacionModel> listrm = new ArrayList<>();
        List<Reservacion> listr = reservacionRepository.findAll();
        
        for (int i = 0; i < listr.size(); i++) {
            if (listr.get(i).getUsuario().getId().equals(idUsuario)) {
                listrm.add(reservacionConverter.convertReservacionToReservacionModel(listr.get(i)));
            }
        }
        
        if (ordenar) {
           Collections.sort(listrm, new Comparator<ReservacionModel>(){
               @Override
               public int compare(ReservacionModel o1, ReservacionModel o2) {
                   return o1.getFecha().compareTo(o2.getFecha());
               }
            }); 
        }
        
        return listrm;
    }

    @Override
    public ReservacionModel getUltimo() {
        List<Reservacion> res = reservacionRepository.findAll();
        if (contar() == 0) {
            return new ReservacionModel();
        } else {
            return reservacionConverter.convertReservacionToReservacionModel(res.get(contar() - 1));
        }
    }

    @Override
    public Integer contar() {
        Long cant = reservacionRepository.count();
        return cant.intValue();
    }

    @Override
    public ReservacionModel buscarPorId(Integer id) {
        Reservacion res = reservacionRepository.findById(id);
        
        return reservacionConverter.convertReservacionToReservacionModel(res);
    }

    @Override
    public List<ReservacionModel> getReservacionesDeHabitacionDesde(Integer idHabitacion, Date fecha) {
        List<ReservacionModel> listReservasModel = new ArrayList<>();
        
        List<Reservacion> listReservas = qReservacionRepository.getReservacionesDepuesDe(fecha);
        
        List<DetalleReserva> listDetReserv = new ArrayList<>();
        for (int i = 0; i < listReservas.size(); i++) {
            listDetReserv = qDetalleReservaRepository.buscarReservaciones(listReservas.get(i));
            for (int j = 0; j < listDetReserv.size(); j++) {
                if (listDetReserv.get(j).getHabitacion().getId() == idHabitacion) {
                    listReservasModel.add(reservacionConverter.convertReservacionToReservacionModel(listReservas.get(i)));
                }
            }
        }
        return listReservasModel;
    }

    @Override
    public List<HabitacionModel> getHabitacionesReservadasEntre(Date fechaArribo, Date fechaSalida) {
        return null;
    }

    
}
