/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.component.HabitacionConverter;
import com.rmsoft.entity.Habitacion;
import com.rmsoft.model.EstadoHabitacion;
import com.rmsoft.model.HabitacionModel;
import com.rmsoft.repository.HabitacionRepository;
import com.rmsoft.service.HabitacionService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
@Service("habitacionService")
public class HabitacionServiceImpl implements HabitacionService{

    private static final Log LOG = LogFactory.getLog(HabitacionServiceImpl.class);
    
    @Autowired
    @Qualifier("habitacionRepository")
    private HabitacionRepository habitacionRepository;
    
    @Autowired
    @Qualifier("habitacionConverter")
    private HabitacionConverter habitacionConverter;
    
    @Override
    public HabitacionModel guardarHabitacion(HabitacionModel habitacionModel) {
        Habitacion habitacion = habitacionRepository.save(habitacionConverter.convertHabitacionModelToHabitacion(habitacionModel));
        
        if (habitacion != null) {
            return habitacionConverter.convertHabitacionToHabitacionModel(habitacion);
        }
        return null;
    }

    @Override
    public void editarHabitacion(HabitacionModel habitacionModel) {
        habitacionRepository.save(habitacionConverter.convertHabitacionModelToHabitacion(habitacionModel));
    }

    @Override
    public void eliminarHabitacion(HabitacionModel habitacionModel) {
        Habitacion habitacion = habitacionRepository.findById(habitacionModel.getId());
        habitacionRepository.delete(habitacion);
    }
    
    @Override
    public void eliminarHabitacionPorId(Integer id) {
        Habitacion habitacion = habitacionRepository.findById(id);
        habitacionRepository.delete(habitacion);
    }

    @Override
    public HabitacionModel buscarPorId(Integer id) {
        
        LOG.info("ID HA BUSCAR --- " + id);
        Habitacion habitacion = habitacionRepository.findById(id);
        
        if (habitacion != null) {
            LOG.info("LA HABITACIN QUE BUSCO SI EXISTE ES DISTINTA DE NULL");
            return habitacionConverter.convertHabitacionToHabitacionModel(habitacion);
        }
        return null;
    }
    
    @Override
    public HabitacionModel getUltimo() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        Long cant = habitacionRepository.count();
        
        if (cant == 0) {
            return new HabitacionModel();
        } else {
            return habitacionConverter.convertHabitacionToHabitacionModel(habitaciones.get(cant.intValue()-1));
        }
    }

    @Override
    public Integer contar() {
        Long cantidad = habitacionRepository.count();
        return cantidad.intValue();
    }

    @Override
    public List<HabitacionModel> listarTodo(boolean ordenar) {
        List<HabitacionModel> listHabitacionModel = new ArrayList<>();
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        
        for (Habitacion habitacion : habitaciones) {
            listHabitacionModel.add(habitacionConverter.convertHabitacionToHabitacionModel(habitacion));
        }
        
        if (ordenar) {
           Collections.sort(listHabitacionModel, new Comparator<HabitacionModel>(){
                @Override
                public int compare(HabitacionModel o1, HabitacionModel o2) {
                    return o1.getNumero().compareTo(o2.getNumero());
                }
            }); 
        }
        return listHabitacionModel;
    } 
    
    @Override
    public List<HabitacionModel> listarHabilitadas(boolean ordenar) {
        List<HabitacionModel> listHabitacionModel = new ArrayList<>();
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        
        EstadoHabitacion eh = new EstadoHabitacion();
        
        for (Habitacion habitacion : habitaciones) {
            if (!habitacion.getEstado().equalsIgnoreCase(eh.getAnulada())) {
                listHabitacionModel.add(habitacionConverter.convertHabitacionToHabitacionModel(habitacion));
            }
        }
        
        if (ordenar) {
           Collections.sort(listHabitacionModel, new Comparator<HabitacionModel>(){
                @Override
                public int compare(HabitacionModel o1, HabitacionModel o2) {
                    return o1.getNumero().compareTo(o2.getNumero());
                }
            }); 
        }
        return listHabitacionModel;
    } 
}
