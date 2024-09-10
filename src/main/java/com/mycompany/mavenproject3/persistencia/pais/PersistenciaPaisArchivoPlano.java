package com.mycompany.mavenproject3.persistencia.pais;

import com.mycompany.mavenproject3.base.Pais;

import java.util.ArrayList;
import java.util.List;


import static com.mycompany.mavenproject3.util.CSVUtil.*;
import static com.mycompany.mavenproject3.util.ContadorIdUtil.*;

public class PersistenciaPaisArchivoPlano implements PersistenciaPais{

	@Override
	public Pais guardar(Pais pais) {
		try {
			String rutaArchivo = "./data/ArchivosPlanos/pais.csv";
			if(validarArchivo(rutaArchivo)){
				escribirLinea(rutaArchivo, "ID,Nombre");
			}

			Integer id = getNextId("pais");
			escribirLinea(rutaArchivo, id + "," + pais.getNombre());

			System.out.println("Pais guardado exitosamente en " + rutaArchivo);

		} catch (Exception e) {
			System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor){
		String rutaArchivo = "./data/ArchivosPlanos/pais.csv";
		try {

			List<String> lineas = obtenerLineas(rutaArchivo);
			String encabezado = lineas.getFirst();
			List<String>  nuevasLineas = new ArrayList<>();
			nuevasLineas.add(encabezado);

			int indiceCampo = obtenerIndiceCampo(encabezado, campo);

			for (String linea : lineas.subList(1, lineas.size())) {
				if (verificarIdLinea(linea, id)) {
					String nuevaLinea = reemplazarCampo(linea, indiceCampo, valor);
					nuevasLineas.add(nuevaLinea);
				} else {
					nuevasLineas.add(linea);
				}
			}
			escribirLineas(rutaArchivo, nuevasLineas);
		} catch (Exception e) {
			System.out.println("Error al editar el archivo CSV: " + e.getMessage());
		}
	}

	@Override
	public Pais buscarPorId(Integer id) {
		Pais pais = new Pais();
		String rutaArchivo = "./data/ArchivosPlanos/pais.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);

			for (String linea : lineas.subList(1, lineas.size())) {
				if (verificarIdLinea(linea, id)) {
					List<String> campos = obtenerListaCampos(linea);
					pais.setId(Integer.parseInt(campos.get(0)));
					pais.setNombre(campos.get(1));
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error al editar el archivo CSV: " + e.getMessage());
		}

		return pais;
	}

	@Override
	public List<Pais> buscarTodos() {
		List<Pais> paises = new ArrayList<>();
		String rutaArchivo = "./data/ArchivosPlanos/pais.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);

			for (String linea : lineas.subList(1, lineas.size())) {
					Pais pais = new Pais();
					List<String> campos = obtenerListaCampos(linea);
					pais.setId(Integer.parseInt(campos.get(0)));
					pais.setNombre(campos.get(1));
					paises.add(pais);
			}

		} catch (Exception e) {
			System.out.println("Error al editar el archivo CSV: " + e.getMessage());
		}

		return paises;
	}

	public void eliminarPorId(Integer id){
		String rutaArchivo = "./data/ArchivosPlanos/pais.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);
			String encabezado = lineas.getFirst();
			List<String>  nuevasLineas = new ArrayList<>();
			nuevasLineas.add(encabezado);

			for (String linea : lineas.subList(1, lineas.size())) {
				if (!verificarIdLinea(linea, id)) {
					nuevasLineas.add(linea);
				}
			}

			escribirLineas(rutaArchivo, nuevasLineas);

		} catch (Exception e) {
			System.out.println("Error al editar el archivo CSV: " + e.getMessage());
		}
	}


}
