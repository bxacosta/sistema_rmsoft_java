/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmsoft.service.impl;

import com.rmsoft.service.ImagenService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author bryan
 */
@Service("imagenService")
public class ImagenServiceImpl  implements ImagenService{

    @Override
    public String guardarImagen(MultipartFile file, String path) {
        Path dir = Paths.get(path);
        
        if (Files.exists(dir)) {
            try {
                String[] parts = file.getContentType().split("/");
                String tipo = parts[0];
                String extencion = parts[1];
                if (tipo.equals("image") && (extencion.equals("png") || extencion.equals("jpeg") || extencion.equals("jpg"))) {
                    String token = randomString(40);
                    String nombre = token.concat(".").concat(extencion);
                    Files.copy(file.getInputStream(), dir.resolve(nombre));
                    return nombre;
                }
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
    
    @Override
    public boolean eliminarImagen(String nombre, String path) {
        String file = path + "/" + nombre;
        Path dir = Paths.get(file);
        try {
            Files.delete(dir);
            if (!Files.exists(dir)) {
                return true;
            }
        } catch (IOException ex) {
            return false;
        }
        return false;
    }

    private static String randomString(int length) {
        char[] charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        
        Random random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(charSet.length);
            result[i] = charSet[randomCharIndex];
        }
        return new String(result);
    }

}
