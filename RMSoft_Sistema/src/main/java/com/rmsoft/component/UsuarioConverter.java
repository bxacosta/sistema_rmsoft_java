/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.entity.Persona;
import com.rmsoft.entity.RolUsuario;
import com.rmsoft.entity.TipoDocumento;
import com.rmsoft.entity.Usuario;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.repository.PersonaRepository;
import com.rmsoft.repository.RolUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("usuarioConverter")
public class UsuarioConverter {
    
    @Autowired
    @Qualifier("rolUsuarioRepository")
    private RolUsuarioRepository rolUsuarioRepository;
    
    @Autowired
    @Qualifier("personaRepository")
    private PersonaRepository personaRepository;
    
    public UsuarioModel convertUsuarioToUsarioModel(Usuario usuario) {
        UsuarioModel um = new UsuarioModel();
        
        um.setId(usuario.getId());
        um.setUsuario(usuario.getUsuario());
        um.setHabilitado(usuario.getHabilitado());
        um.setPassword(usuario.getPassword());

        RolUsuario rol = rolUsuarioRepository.findByIdUsuario(usuario);
        um.setRol(rol.getNombre());

        Persona per = personaRepository.findByIdUsuario(usuario);
        um.setNombre(per.getNombre());
        um.setApellido(per.getApellido());
        um.setNumDocumento(per.getNumDocumento());
        um.setDireccion(per.getDireccion());
        um.setImagen(per.getImagen());
        
        TipoDocumento tp = per.getTipoDocumento();
        if (tp != null) {
            um.setTipoDocumento(per.getTipoDocumento().getNombre());
        }
        return um;
    }
}
