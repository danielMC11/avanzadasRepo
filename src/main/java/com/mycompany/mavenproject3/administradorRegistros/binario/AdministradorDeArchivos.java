package com.mycompany.mavenproject3.administradorRegistros.binario;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

public class AdministradorDeArchivos {

    private static final Logger registro = Logger.getLogger(AdministradorDeArchivos.class.getName());


    public static void escribirEnArchivoBinario(Object objecto, String nombreArchivo)throws IOException{

        Objects.requireNonNull(objecto, "El objeto no puede ser nulo");
        Objects.requireNonNull(nombreArchivo,"El nombre del archivo no puede ser nulo");
        try(FileOutputStream fos = new FileOutputStream(nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(objecto);
            System.out.println("Objeto guardado exitosamente en: " + nombreArchivo);
        }
    }

    public static Object cargarObjetoDesdeArchivoBinario(String nombreArchivo) throws IOException, ClassNotFoundException{
        Objects.requireNonNull(nombreArchivo, "El nombre del archivo no puede ser nulo");

        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }

}
