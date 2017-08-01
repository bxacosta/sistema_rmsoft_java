/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.component.UsuarioConverter;
import com.rmsoft.entity.Persona;
import com.rmsoft.entity.RolUsuario;
import com.rmsoft.entity.TipoDocumento;
import com.rmsoft.entity.Usuario;
import com.rmsoft.model.Rol;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.repository.PersonaRepository;
import com.rmsoft.repository.QUsuarioRepository;
import com.rmsoft.repository.RolUsuarioRepository;
import com.rmsoft.repository.TipoDocumentoRepository;
import com.rmsoft.repository.UsuarioRepository;
import com.rmsoft.service.UsuarioService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author bryan
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{
    
    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    @Qualifier("qUsuarioRepository")
    private QUsuarioRepository qUsuarioRepository;
    
    @Autowired
    @Qualifier("tipoDocumentoRepository")
    private TipoDocumentoRepository tipoDocumentoRepository;
    
    @Autowired
    @Qualifier("personaRepository")
    private PersonaRepository personaRepository;
    
    @Autowired
    @Qualifier("rolUsuarioRepository")
    private RolUsuarioRepository rolUsuarioRepository;
    
    @Autowired
    @Qualifier("usuarioConverter")
    private UsuarioConverter usuarioConverter;
    
    
    private static final Log LOG = LogFactory.getLog(UsuarioServiceImpl.class);
    
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(nombreUsuario);
        List<GrantedAuthority> autorities =  buildRol(usuario.getRolUsuario());
        return buildUser(usuario, autorities);
    }
    
    private User buildUser(Usuario usuario, List<GrantedAuthority> rol) {
        return new User(usuario.getUsuario(), usuario.getPassword(), usuario.getHabilitado(), true, true, true, rol);
    }
    
    private List<GrantedAuthority> buildRol(Set<RolUsuario> rol){
        Set<GrantedAuthority> auths = new HashSet<>();
        for (RolUsuario rol_usuario : rol) {
            auths.add(new SimpleGrantedAuthority(rol_usuario.getNombre()));
        }
        return new ArrayList<GrantedAuthority>(auths);
    }
    
    private final BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    
    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioModel) {

        Usuario usuario = usuarioRepository.save(new Usuario(usuarioModel.getUsuario(), pe.encode(usuarioModel.getPassword()), usuarioModel.getHabilitado()));

        if (existeUsuarioId(usuario.getId())) {
            TipoDocumento td = tipoDocumentoRepository.findByNombre(usuarioModel.getTipoDocumento());

            personaRepository.save(new Persona(usuarioModel.getNombre().toLowerCase(), usuarioModel.getApellido().toLowerCase(), usuarioModel.getNumDocumento(), usuarioModel.getDireccion(), usuarioModel.getImagen(), usuario, td));

            rolUsuarioRepository.save(new RolUsuario(usuarioModel.getRol(), usuario));
            
            return usuarioConverter.convertUsuarioToUsarioModel(usuario);
        }
        return null;
    }
    
    @Override
    public void editarUsuario(UsuarioModel usuario, UsuarioModel nuevo) {
        
        if (!nuevo.getPassword().equals(usuario.getPassword())) {
            cambiarPassword(usuario, nuevo.getPassword());
        }
        
        LOG.info("FROM editarUsuario ANTES DE EDITAR --NUEVO-- : " + nuevo.toString());
        LOG.info("FROM editarUsuario ANTES DE EDITAR --ANTERIOR -- : " + usuario.toString());
        
        Usuario user = usuarioRepository.findById(usuario.getId());

        TipoDocumento td = tipoDocumentoRepository.findByNombre(nuevo.getTipoDocumento());
        
        Persona per = personaRepository.findByIdUsuario(user);
        personaRepository.save(new Persona(per.getId(),nuevo.getNombre().toLowerCase(), nuevo.getApellido().toLowerCase(), nuevo.getNumDocumento(), nuevo.getDireccion(), nuevo.getImagen(), user, td));

        RolUsuario ru = rolUsuarioRepository.findByIdUsuario(user);
        rolUsuarioRepository.save(new RolUsuario(ru.getId(),nuevo.getRol().toLowerCase(), user));
        
        //if (!nuevo.getUsuario().equals(usuario.getUsuario())) {
            usuarioRepository.save(new Usuario(usuario.getId(), nuevo.getUsuario(), user.getPassword(), nuevo.getHabilitado()));
        //}
    }
    
    @Override
    public void cambiarPassword(UsuarioModel usuario, String password) {
       usuarioRepository.save(new Usuario(usuario.getId(), usuario.getUsuario(), pe.encode(password), usuario.getHabilitado()));
    }
    
    @Override
    public Boolean existeUsuarioId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario) != null;
    }
    
    @Override
    public Boolean existeUsuarioNombre(String nombreUsuario) {
        return qUsuarioRepository.buscarUsuario(nombreUsuario) != null;
    }
    
    @Override
    public UsuarioModel buscarPorId(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        if (usuario != null) {
            return usuarioConverter.convertUsuarioToUsarioModel(usuario);
        }
        return null;
    }

    @Override
    public UsuarioModel buscarPorNombre(String nombreUsuario) {
        Usuario usuario = qUsuarioRepository.buscarUsuario(nombreUsuario);
        if (usuario != null) {
            return usuarioConverter.convertUsuarioToUsarioModel(usuario);
        }
        return null;
    }

    @Override
    public List<UsuarioModel> listarUsers() {
        List<UsuarioModel> lum = new ArrayList<>();
        List<Usuario> u = usuarioRepository.findAll();
        
        UsuarioModel um;
        Rol rol = new Rol();
        
        for (Usuario usaurio : u) {
            um = usuarioConverter.convertUsuarioToUsarioModel(usaurio);    
            if (um.getRol().equalsIgnoreCase(rol.getUser())) {
                lum.add(um);
            }
        }

        Collections.sort(lum, new Comparator<UsuarioModel>() {
            @Override
            public int compare(UsuarioModel o1, UsuarioModel o2) {
                return o1.getApellido().compareTo(o2.getApellido());
            }
        });
        
        return lum;
    }

    @Override
    public List<UsuarioModel> listarTodos() {
        List<UsuarioModel> lum = new ArrayList<>();
        List<Usuario> u = usuarioRepository.findAll();
       
        for (Usuario usaurio : u) {
            lum.add(usuarioConverter.convertUsuarioToUsarioModel(usaurio));
        }
        
        return lum;
    }

    @Override
    public int contar() {
        Long num = usuarioRepository.count();
        return num.intValue();
    }

    @Override
    public UsuarioModel getUltimo() {
        List<Usuario> u = usuarioRepository.findAll();        
        if (u.isEmpty()) {
            return new UsuarioModel();
        } else {
            return usuarioConverter.convertUsuarioToUsarioModel(u.get(0));
        }
    }
    
}
