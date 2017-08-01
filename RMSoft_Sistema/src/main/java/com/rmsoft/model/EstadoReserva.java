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
public class EstadoReserva {
    private final String abierta = "abierta";
    private final String porPagar = "por pagar";
    private final String pagada = "pagada";
    private final String anulada = "anulada";

    public String getAbierta() {
        return abierta;
    }

    public String getPorPagar() {
        return porPagar;
    }

    public String getPagada() {
        return pagada;
    }

    public String getAnulada() {
        return anulada;
    }
}
