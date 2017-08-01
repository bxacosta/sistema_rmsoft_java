/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.model;

/**
 *
 * @author bryan
 */
public class EstadoHabitacion {
    
    private final String disponible = "disponible";
    private final String ocupada = "ocupada";
    private final String mantenimiento = "mantenimiento";
    private final String anulada = "anulada";

    public String getDisponible() {
        return disponible;
    }

    public String getOcupada() {
        return ocupada;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public String getAnulada() {
        return anulada;
    }

}
