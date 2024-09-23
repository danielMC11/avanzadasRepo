package com.mycompany.mavenproject3.persistencia.municipio;

import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.base.Municipio;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamentoArchivoPlano;

import java.util.ArrayList;
import java.util.List;

import static com.mycompany.mavenproject3.util.CSVUtil.*;
import static com.mycompany.mavenproject3.util.ContadorIdUtil.getNextId;

public class PersistenciaMunicipioArchivoPlano implements PersistenciaMunicipio{

	private PersistenciaDepartamentoArchivoPlano persistenciaDepartamentoArchivoPlano;

	public PersistenciaMunicipioArchivoPlano() {
		this.persistenciaDepartamentoArchivoPlano = new PersistenciaDepartamentoArchivoPlano();
	}

	@Override
	public Municipio guardar(Municipio municipio) {
		try {
			String rutaArchivo = "./data/ArchivosPlanos/municipio.csv";
			if(validarArchivo(rutaArchivo)){
				escribirLinea(rutaArchivo, "ID,Nombre,departamentoId");
			}

			Integer id = getNextId("municipio");
			escribirLinea(rutaArchivo, id + "," + municipio.getNombre() + "," + municipio.getDepartamento().getId().toString());

			System.out.println("Municipio guardado exitosamente en " + rutaArchivo);

		} catch (Exception e) {
			System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void actualizar(Integer id, String campo, Object valor) {
		String rutaArchivo = "./data/ArchivosPlanos/municipio.csv";
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
			System.out.println("Municipio editado exitosamente en " + rutaArchivo);

		} catch (Exception e) {
			System.out.println("Error al editar: " + e.getMessage());
		}
	}

	@Override
	public Municipio buscarPorId(Integer id) {
		Municipio municipio = new Municipio();
		String rutaArchivo = "./data/ArchivosPlanos/municipio.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);

			for (String linea : lineas.subList(1, lineas.size())) {
				if (verificarIdLinea(linea, id)) {
					List<String> campos = obtenerListaCampos(linea);
					municipio.setId(Integer.parseInt(campos.get(0)));
					municipio.setNombre(campos.get(1));
					municipio.setDepartamento(persistenciaDepartamentoArchivoPlano.buscarPorId(Integer.parseInt(campos.get(2))));
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error al obtener: " + e.getMessage());
		}

		return municipio;
	}

	@Override
	public List<Municipio> buscarTodos() {
		List<Municipio> municipios = new ArrayList<>();
		String rutaArchivo = "./data/ArchivosPlanos/municipio.csv";
		try {
			List<String> lineas = obtenerLineas(rutaArchivo);

			for (String linea : lineas.subList(1, lineas.size())) {
					Municipio municipio = new Municipio();
					List<String> campos = obtenerListaCampos(linea);
					municipio.setId(Integer.parseInt(campos.get(0)));
					municipio.setNombre(campos.get(1));
					municipio.setDepartamento(persistenciaDepartamentoArchivoPlano.buscarPorId(Integer.parseInt(campos.get(2))));
					municipios.add(municipio);
			}

		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		}

		return municipios;
	}

	@Override
	public void eliminarPorId(Integer id) {
		String rutaArchivo = "./data/ArchivosPlanos/municipio.csv";
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
			System.out.println("Municipio eliminado exitosamente en " + rutaArchivo);


		} catch (Exception e) {
			System.out.println("Error al eliminar: " + e.getMessage());
		}
	}
}
