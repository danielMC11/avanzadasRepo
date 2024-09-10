package com.mycompany.mavenproject3.util;

import java.util.Scanner;

public class ConsolaUtil {
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
