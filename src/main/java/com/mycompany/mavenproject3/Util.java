package com.mycompany.mavenproject3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
	public static int mostrarMenu(String titulo, List<String> opciones) {
		System.out.println("\n==== "+titulo+" ====");
		for (int i = 0; i < opciones.size(); i++) {
			System.out.println((i + 1) + ". " + opciones.get(i));
		}
		System.out.print("=========================================\n\n");
		int opcion = 0;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Opcion: ");
			String input = reader.readLine();
			opcion = Integer.parseInt(input);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return opcion;
	}

	public static void mostrarInfo(String titulo, List<String> campos, List<String> valores){
		List<String> opciones = new ArrayList<>();
		for(int i=0; i<campos.size();i++){
			opciones.add(campos.get(i) + ": " + valores.get(i));
		}
		System.out.println("\n==== "+titulo+" ====");
		for (int i = 0; i < opciones.size(); i++) {
			System.out.println((i + 1) + ". " + opciones.get(i));
		}
		System.out.print("=========================================\n\n");
	}

	public static int menuEdicion(String titulo, List<String> campos, List<String> valores){
		List<String> opciones = new ArrayList<>();
		for(int i=0; i<campos.size();i++){
			opciones.add(campos.get(i) + ": " + valores.get(i));
		}
		opciones.add("Salir");
		int opcion = mostrarMenu(titulo, opciones);

		return opcion;
	}



	public static Object leerValorPorConsola(String mensaje, Class<?> type) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(mensaje);
		String input = scanner.nextLine();

		try {
			if (type == Integer.class) {
				return Integer.parseInt(input);
			} else if (type == Double.class) {
				return Double.parseDouble(input);
			} else if (type == String.class) {
				return input;
			}  else {
				throw new IllegalArgumentException("Unsupported type: " + type.getSimpleName());
			}
		} catch (NumberFormatException e) {
			if (type == Integer.class || type == Double.class) {
				System.out.println("Invalid input for type: " + type.getSimpleName());
			}
			return null;
		}
	}


}
