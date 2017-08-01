/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service;

import com.rmsoft.model.UsuarioModel;
import java.util.List;

/**
 *
 * @author bryan
 */
public interface UsuarioService {
    
    public abstract UsuarioModel crearUsuario(UsuarioModel usuarioModel); 
    
    public abstract void editarUsuario(UsuarioModel usuario, UsuarioModel actual); 
    
    public abstract void cambiarPassword(UsuarioModel usuario, String password); 
    
    public abstract UsuarioModel buscarPorNombre(String usuario);
    
    public abstract UsuarioModel buscarPorId(Integer id);
    
    public abstract Boolean existeUsuarioNombre(String nombreUsuario);
    
    public abstract Boolean existeUsuarioId(Integer idUsuario);
    
    public abstract List<UsuarioModel> listarUsers();
    
    public abstract List<UsuarioModel> listarTodos();
    
    public abstract int contar();
    
    public abstract UsuarioModel getUltimo();
}
