package com.mycompany.mavenproject3.persistencia.departamento;

import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.persistencia.pais.PersistenciaPaisArchivoPlano;

import java.util.ArrayList;
import java.util.List;

import static com.mycompany.mavenproject3.util.CSVUtil.*;
import static com.mycompany.mavenproject3.util.ContadorIdUtil.getNextId;

public class PersistenciaDepartamentoArchivoPlano  implements PersistenciaDepartamento{

	private PersistenciaPaisArchivoPlano persistenciaPaisArchivoPlano;

	public PersistenciaDepartamentoArchivoPlano(){
		this.persistenciaPaisArchivoPlano = new PersistenciaPaisArchivoPlano();
	}

	@Override
	public Departamento guardar(Departamento departamento) {
		try {
			String rutaArchivo = "./data/ArchivosPlanos/departamento.csv";
			if(validarArchivo(rutaArchivo)){
				escribirLinea(rutaArchivo, "ID,Nombre,paisId");
			}

			Integer id = getNextId("departamento");
			escribirLinea(rutaArchivo, id + "," + departamento.getNombre() + "," + departamento.getPais().getId().toString());

			System.out.println("Departamento guardado exitosamente en " + rutaArchivo);

		} catch (Exception e) {
			System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor) {
		String rutaArchivo = "./data/ArchivosPlanos/departamento.csv";
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
			System.out.println("Error al editar: " + e.getMessage());
		}
	}

	@Override
	public Departamento buscarPorId(Integer id) {
		Departamento departamento = new Departamento();
		String rutaArchivo = "./data/ArchivosPlanos/departamento.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);

			for (String linea : lineas.subList(1, lineas.size())) {
				if (verificarIdLinea(linea, id)) {
					List<String> campos = obtenerListaCampos(linea);
					departamento.setId(Integer.parseInt(campos.get(0)));
					departamento.setNombre(campos.get(1));
					departamento.setPais(persistenciaPaisArchivoPlano.buscarPorId(Integer.parseInt(campos.get(2))));
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error al obtener: " + e.getMessage());
		}

		return departamento;
	}

	@Override
	public List<Departamento> buscarTodos() {
		List<Departamento> departamentos = new ArrayList<>();
		String rutaArchivo = "./data/ArchivosPlanos/departamento.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);

			for (String linea : lineas.subList(1, lineas.size())) {
					Departamento departamento = new Departamento();
					List<String> campos = obtenerListaCampos(linea);
					departamento.setId(Integer.parseInt(campos.get(0)));
					departamento.setNombre(campos.get(1));
					departamento.setPais(persistenciaPaisArchivoPlano.buscarPorId(Integer.parseInt(campos.get(2))));
					departamentos.add(departamento);
			}

		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		}

		return departamentos;
	}

	@Override
	public void eliminarPorId(Integer id) {
		String rutaArchivo = "./data/ArchivosPlanos/departamento.csv";
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
			System.out.println("Error al eliminar: " + e.getMessage());
		}
	}
}
