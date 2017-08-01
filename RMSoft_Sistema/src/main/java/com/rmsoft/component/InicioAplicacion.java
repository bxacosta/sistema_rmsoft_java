/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.component;

import com.rmsoft.constant.ImageConstant;
import com.rmsoft.entity.Descuento;
import com.rmsoft.entity.Empresa;
import com.rmsoft.entity.Habitacion;
import com.rmsoft.entity.TipoDocumento;
import com.rmsoft.model.EstadoHabitacion;
import com.rmsoft.model.Rol;
import com.rmsoft.model.UsuarioModel;
import com.rmsoft.repository.DescuentoRepository;
import com.rmsoft.repository.EmpresRepository;
import com.rmsoft.repository.HabitacionRepository;
import com.rmsoft.repository.TipoDocumentoRepository;
import com.rmsoft.repository.UsuarioRepository;
import com.rmsoft.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author bryan
 */
@Component("inicioAplicacion")
public class InicioAplicacion {
  
    @Autowired
    @Qualifier("empresaRepository")
    private EmpresRepository er;
    
    
    @Autowired
    @Qualifier("tipoDocumentoRepository")
    private TipoDocumentoRepository tdr;
    
    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usr;
    
    @Autowired
    @Qualifier("habitacionRepository")
    private HabitacionRepository hr;
    
    @Autowired
    @Qualifier("descuentoRepository")
    private DescuentoRepository dr;
    
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService uss;
    
    
    public void crearDatosBase(){
        if (er.count() == 0) {
            Empresa em = new Empresa(
                    "libertad", 
                    "hotel", 
                    "243634", 
                    "0923456732", 
                    "ecuador", 
                    "quito", 
                    "infohotel@gmail.com", 
                    "Av. America E2-542 y Av. Universitaria, a pocos metros del Seminario Mayor.", 
                    "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3989.793825258404!2d-78.50422578583151!3d-0.200770899853514!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x91d59a8c54484dc1%3A0xd181ba6389c436e2!2sUniversidad+Central+del+Ecuador!5e0!3m2!1ses-419!2sec!4v1501610293124", 
                    "Ven y disfruta de tus mejores vacaciones en la playa hospedándose en nuestro hostal, registrarte e ingresa a nuestro sistema y reserva una habitaciones.", 
                    "Reserva y Aquiler de Habitaciones",
                    "Información de Contacto", 
                    "Hotel Libertad es su mejor elección a la hora de escoger un lugar para hospedarse ya sea que este de vacaciones o de visita, nuestro personal está dispuesto a ofrecerle la mejor atención y otorgarle un servicio de calidad, además contamos con un gran número de habitaciones equipadas con todas las comodidades que usted necesite y a los mejores precios. Contamos también con un restaurante con un delicioso menú con los mejores platos típicos de la zona."
            );
            er.save(em);
        }
        
        if (dr.count() == 0) {
            Descuento des = new Descuento(
                    "desactivar",
                    0,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            dr.save(des);
        }
        
        
        // Crea los tipo de documentos
        if (tdr.count() == 0) {
            TipoDocumento td1 = new TipoDocumento(1, "Cedula");
            TipoDocumento td2 = new TipoDocumento(2, "Pasaporte");
            tdr.save(td1);
            tdr.save(td2);   
        }
        // Crea usuarios
        if (usr.count() == 0) {
            Rol rol = new Rol();
            UsuarioModel uma = new UsuarioModel();
            uma.setUsuario("admin@admin.com");
            uma.setPassword("admin");
            uma.setHabilitado(true);
            uma.setNombre("admin");
            uma.setApellido("admin");
            uma.setRol(rol.getAdmin());
            uma.setImagen(ImageConstant.DEFAULT_ADMIN_IMAGE);
            uss.crearUsuario(uma);
            
            UsuarioModel umr = new UsuarioModel();
            umr.setUsuario("resep@resep.com");
            umr.setPassword("resep");
            umr.setHabilitado(true);
            umr.setNombre("resep");
            umr.setApellido("resep");
            umr.setRol(rol.getResep());
            umr.setImagen(ImageConstant.DEFAULT_USER_IMAGE);
            uss.crearUsuario(umr);
            
            UsuarioModel umu = new UsuarioModel();
            umu.setUsuario("user@user.com");
            umu.setPassword("user");
            umu.setHabilitado(true);
            umu.setNombre("user");
            umu.setApellido("user");
            umu.setRol(rol.getUser());
            umu.setImagen(ImageConstant.DEFAULT_USER_IMAGE);
            uss.crearUsuario(umu);
        }
        
        //Crear habitaciones
        if (hr.count() == 0) {
            EstadoHabitacion eh = new EstadoHabitacion();
            hr.save(new Habitacion("N100", 3, "simple", eh.getDisponible(), "primer piso", 24.60, 6.20, "Habitacion de Prueba", ImageConstant.DEFAULT_ROOM_IMAGE));
            hr.save(new Habitacion("N101", 3, "doble", eh.getDisponible(), "primer piso", 26.00, null, "Habitacion de Prueba", ImageConstant.DEFAULT_ROOM_IMAGE));
            hr.save(new Habitacion("S203", 3, "triple", eh.getDisponible(), "segundo piso", 18.40, null, "Habitacion de Prueba", ImageConstant.DEFAULT_ROOM_IMAGE));
            hr.save(new Habitacion("W204", 3, "simple", eh.getDisponible(), "tercer piso", null, 5.70, "Habitacion de Prueba", ImageConstant.DEFAULT_ROOM_IMAGE));
        }
    }
}
