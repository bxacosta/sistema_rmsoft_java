/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.entity.DetalleReserva;
import com.rmsoft.entity.Empresa;
import com.rmsoft.entity.Reservacion;
import com.rmsoft.entity.Usuario;
import com.rmsoft.model.DetalleReservaModel;
import com.rmsoft.model.EstadoReserva;
import com.rmsoft.model.ReservacionModel;
import com.rmsoft.repository.EmpresRepository;
import com.rmsoft.repository.QDetalleReservaRepository;
import com.rmsoft.repository.ReservacionRepository;
import com.rmsoft.repository.UsuarioRepository;
import com.rmsoft.service.UsuarioService;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("reservacionConverter")
public class ReservacionConverter {
    
    private static final Log LOG = LogFactory.getLog(ReservacionConverter.class);  
    
    @Autowired
    @Qualifier("empresaRepository")
    private EmpresRepository empresRepository;
    
    @Autowired
    @Qualifier("empresaConverter")
    private EmpresaConverter empresaConverter;
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    
    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;
    
    
    @Autowired
    @Qualifier("reservacionRepository")
    private ReservacionRepository reservacionRepository;
  
    @Autowired
    @Qualifier("qDetalleReservaRepository")
    private QDetalleReservaRepository qDetalleReservaRepository;
    
    @Autowired
    @Qualifier("detalleReservaConverter")
    private DetalleReservaConverter detalleReservaConverter;

    
    public ReservacionModel convertReservacionToReservacionModel(Reservacion reservacion) {
        ReservacionModel rModel = new ReservacionModel();
        List<DetalleReserva> listDR = qDetalleReservaRepository.buscarReservaciones(reservacion);
        
        List<DetalleReservaModel> listDRM = new ArrayList<>();
        
        if (!listDR.isEmpty()) {
            for (int i = 0; i < listDR.size(); i++) {
                listDRM.add(detalleReservaConverter.convertDetalleReservaToDetalleReservaModel(listDR.get(i)));
            }
        }
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.00", simbolos);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        rModel.setId(reservacion.getId());
        rModel.setEstado(reservacion.getEstado());
        rModel.setModo(reservacion.getModo());
        rModel.setFechaArribo(dateFormat.format(reservacion.getFechaArribo()));
        rModel.setFechaSalida(dateFormat.format(reservacion.getFechaSalida()));
        rModel.setUsuarioEncargado(reservacion.getUsuarioEncargado());
        rModel.setFecha(dateFormat.format(reservacion.getFecha()));
        rModel.setSubTotal(Double.parseDouble(format.format(reservacion.getSubTotal())));
        rModel.setValorDescuento(reservacion.getValorDescuento());
        rModel.setTotal(Double.parseDouble(format.format(reservacion.getTotal())));
        rModel.setEmpresa(empresaConverter.convertEmpresaToEmpresaModel(reservacion.getEmpresa()));
        rModel.setUsuario(usuarioService.buscarPorId(reservacion.getUsuario().getId()));
        rModel.setDetReserva(listDRM);
        
        return rModel;
    }
    
    public Reservacion convertReservacionModelToReservacion(ReservacionModel reservacionModel) {
        EstadoReserva esr = new EstadoReserva();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Date arribo;
        Date salida;
        Date fecha = new Date();
        try {
            arribo = dateFormat.parse(reservacionModel.getFechaArribo());
            salida = dateFormat.parse(reservacionModel.getFechaSalida());
        } catch (ParseException ex) {
            return null;
        }
        
        Empresa empresa = empresRepository.findById(reservacionModel.getEmpresa().getId());
        Usuario usuario = usuarioRepository.findById(reservacionModel.getUsuario().getId());
        
        Reservacion resep = new Reservacion(
                reservacionModel.getId(), 
                esr.getPorPagar(), 
                reservacionModel.getModo(), 
                arribo, 
                salida, 
                null, 
                fecha, 
                reservacionModel.getSubTotal(), 
                reservacionModel.getValorDescuento(),
                reservacionModel.getTotal(), 
                empresa, 
                usuario);
        
        return reservacionRepository.save(resep);
    }
    
}
