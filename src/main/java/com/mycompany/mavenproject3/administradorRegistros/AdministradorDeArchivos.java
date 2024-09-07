package com.mycompany.mavenproject3.administradorRegistros;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

public class AdministradorDeArchivos {

    private static final Logger registro = Logger.getLogger(AdministradorDeArchivos.class.getName());

    public static void escribirEnArchivo(Object objecto, String nombreArchivo){
        Objects.requireNonNull(objecto, "El objeto no puede ser nulo");
        Objects.requireNonNull(nombreArchivo,"El nombre del archivo no puede ser nulo");

        try {
            FileWriter escribir = new FileWriter(nombreArchivo,true);
            escribir.write(objecto.toString());
            escribir.close();
            registro.info("Objeto guardado exitosamente!");
        }catch (IOException exception){
            exception.printStackTrace(System.out);
        }
    }

    public static void escribirEnArchivoBinario(Object objecto, String nombreArchivo)throws IOException{

        Objects.requireNonNull(objecto, "El objeto no puede ser nulo");
        Objects.requireNonNull(nombreArchivo,"El nombre del archivo no puede ser nulo");
        try(FileOutputStream fos = new FileOutputStream(nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(objecto);
            System.out.println("Objeto guardado exitosamente en: " + nombreArchivo);
        }
    }
    public  static void leerArchivoPlano(String nombreArchivo){
        Objects.requireNonNull(nombreArchivo, "El nombre del archivo no puede ser nulo");
        File archivo = new File(nombreArchivo);
        try {

            BufferedReader lineaLeida = new BufferedReader(new FileReader(archivo));
            String lecturaLinea = lineaLeida.readLine();
            while (lecturaLinea!=null){
                System.out.println(lecturaLinea);
                lecturaLinea=lineaLeida.readLine();
            }
            lineaLeida.close();
        }catch (IOException exception){
            exception.printStackTrace(System.out);
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
