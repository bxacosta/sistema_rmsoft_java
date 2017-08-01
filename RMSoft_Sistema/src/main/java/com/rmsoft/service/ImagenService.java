/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author bryan
 */
public interface ImagenService {
    
    public abstract String guardarImagen(MultipartFile file, String path);
    
    public abstract boolean eliminarImagen(String nombre, String path);
 
}
