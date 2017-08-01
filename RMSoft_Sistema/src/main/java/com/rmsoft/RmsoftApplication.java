package com.rmsoft;

import com.rmsoft.component.InicioAplicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RmsoftApplication implements CommandLineRunner{

    @Autowired
    @Qualifier("inicioAplicacion")
    InicioAplicacion inicioAplicacion;
   
    public static void main(String[] args) {
        SpringApplication.run(RmsoftApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        inicioAplicacion.crearDatosBase();
    }
}
