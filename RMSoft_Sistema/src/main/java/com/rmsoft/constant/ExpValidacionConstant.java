/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.constant;

/**
 *
 * @author bryan
 */
public class ExpValidacionConstant {
    private static final String ACENTOS = "ñÑáéíóúÁÉÍÓÚ";
    private static final String ABC_LAT = "A-Za-z" + ACENTOS;
    
    public static final String ENTERO_POSITIVO = "([0-9]{1,8})?";
    
    public static final String PRECIO = "((([0-9]{1,10})(.|,))?[0-9]{1,10})?";
    
    public static final String TEXTO_BASICO = "([" + ABC_LAT + " ]{2,}" + "([!.,;:_\\-" + ABC_LAT + " ]*)?)?";
    
    public static final String TEXTO_ALFA_N = "([" + ABC_LAT + "0-9 ]{2,}" + "([$%!.,;:_\\-" + ABC_LAT + "0-9 ]*)?)?";
    
    public static final String TEXTO_LIMPIO = "([" + ABC_LAT + " ]{2,})?";
    
    public static final String EMAIL = "(^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$)?";
    
    public static final String NUMEROS = "([0-9]+)?";
}
