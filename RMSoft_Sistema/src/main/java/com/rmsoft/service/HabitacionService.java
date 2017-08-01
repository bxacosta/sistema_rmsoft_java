/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import com.rmsoft.model.HabitacionModel;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface HabitacionService {
    
    public abstract HabitacionModel guardarHabitacion(HabitacionModel habitacionModel);
    
    public abstract void editarHabitacion(HabitacionModel habitacionModel);
    
    public abstract void eliminarHabitacion(HabitacionModel habitacionModel);
    
    public abstract void eliminarHabitacionPorId(Integer id);
    
    public abstract HabitacionModel getUltimo();
    
    public abstract HabitacionModel buscarPorId(Integer id);
    
    public abstract Integer contar();
    
    public abstract List<HabitacionModel> listarTodo(boolean ordenar);
    
    public abstract List<HabitacionModel> listarHabilitadas(boolean ordenar);
    
}
