package com.mycompany.mavenproject3.util;

import javax.imageio.IIOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ContadorIdUtil {
	private static final String FILE_PATH = "./data/idArchivos.txt";

	// Obtener el siguiente ID para una clave específica
	public static int getNextId(String key) {
		Map<String, Integer> counters = loadCounters();
		int currentId = counters.getOrDefault(key, 0);
		int nextId = currentId + 1;
		counters.put(key, nextId);
		saveCounters(counters);
		return nextId;
	}

	// Cargar los contadores desde el archivo
	private static Map<String, Integer> loadCounters() {
		Map<String, Integer> map = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("=");
				if (parts.length == 2) {
					String key = parts[0].trim();
					int value = Integer.parseInt(parts[1].trim());
					map.put(key, value);
				}
			}
		} catch (IOException e) {
			// Si el archivo no existe, se crea uno nuevo con los valores iniciales
		}
		return map;
	}

	// Guardar los contadores en el archivo
	private static void saveCounters(Map<String, Integer> counters) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Map.Entry<String, Integer> entry : counters.entrySet()) {
				writer.write(entry.getKey() + "=" + entry.getValue());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
