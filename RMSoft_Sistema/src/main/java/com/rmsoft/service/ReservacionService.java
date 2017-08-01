/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import com.rmsoft.model.DetalleReservaModel;
import com.rmsoft.model.HabitacionModel;
import com.rmsoft.model.ReservacionModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface ReservacionService {
    
    public abstract ReservacionModel calcular(ReservacionModel rmodel, List<DetalleReservaModel> listDetReserva);
    
    public abstract ReservacionModel crearReserva(ReservacionModel rmodel); 
    
    public abstract void editarReserva(ReservacionModel rmodel); 
    
    public abstract List<ReservacionModel> listarTodo(boolean ordendar);
    
    public abstract List<ReservacionModel> listarDeUsuaio(Integer idUsuario, boolean ordenar);
    
    public abstract ReservacionModel getUltimo();
    
    public abstract Integer contar();
    
    public abstract ReservacionModel buscarPorId(Integer id);
    
    public abstract List<ReservacionModel> getReservacionesDeHabitacionDesde(Integer idHabitacion, Date fecha);
    
    public abstract List<HabitacionModel> getHabitacionesReservadasEntre(Date fechaArribo, Date fechaSalida);
}
