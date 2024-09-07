package com.mycompany.mavenproject3.dao.archivos;

import com.mycompany.mavenproject3.administradorRegistros.AdministradorDeArchivos;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

public class ArchivoPlano {
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




}
