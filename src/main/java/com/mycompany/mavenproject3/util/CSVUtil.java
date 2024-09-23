package com.mycompany.mavenproject3.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtil {

	public static boolean validarArchivo(String rutaArchivo){
		File archivo = new File( rutaArchivo);
		boolean fileExists = archivo.exists();
		boolean isEmptyFile = fileExists && archivo.length() == 0;
		return !fileExists || isEmptyFile;
	}

	public static List<String> obtenerLineas(String rutaArchivo){
		try {
			File archivo = new File(rutaArchivo);
			if (!archivo.exists())
				throw new Exception("El archivo CSV no existe.");

			List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));

			if (lineas.size() <= 1)
				throw new Exception("El archivo no tiene contenido.");

			return lineas;

		} catch (Exception e){
			System.err.println("ERROR: "  + e.getMessage());
		}

		return null;
	}

	public static boolean verificarIdLinea(String linea, Integer id){
		List<String> datos = new ArrayList<>(Arrays.asList(linea.split(",")));
		return  Integer.parseInt(datos.getFirst()) == id;
	}


	public static int obtenerIndiceCampo(String linea, String campo){
		String[] campos = linea.split(",");
		for (int i = 0; i < campos.length; i++) {
			if (campos[i].trim().equalsIgnoreCase(campo)) {
				return i;
			}
		}
		return -1;
	}

	public static List<String> obtenerListaCampos(String linea){
		return Arrays.asList(linea.split(","));
	}

	public static String reemplazarCampo(String linea, int indiceCampo, Object valor){
		List<String> datos = new ArrayList<>(Arrays.asList(linea.split(",")));
		String nuevoValor = (String) valor;
		datos.set(indiceCampo, nuevoValor);
		return String.join(",", datos);
	}

	public static void escribirLineas(String rutaArchivo, List<String> nuevasLineas){
		try (FileWriter fw = new FileWriter(rutaArchivo);
				 PrintWriter pw = new PrintWriter(fw)) {
			for (String nuevaLinea :  nuevasLineas) {
				pw.println(nuevaLinea);
			}
		} catch (Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}

	}

	public static void escribirLinea(String rutaArchivo, String nuevaLinea){
		try (FileWriter fw = new FileWriter(rutaArchivo, true);
				 PrintWriter pw = new PrintWriter(fw)) {
				pw.println(nuevaLinea);
		} catch (Exception e){
			System.err.println("ERROR: " + e.getMessage());
		}

	}



}
