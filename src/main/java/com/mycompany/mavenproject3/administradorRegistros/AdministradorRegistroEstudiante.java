package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.base.*;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.controlador.ControladorEstudiante;
import com.mycompany.mavenproject3.controlador.ControladorMunicipio;
import com.mycompany.mavenproject3.controlador.ControladorPersona;
import com.mycompany.mavenproject3.persistencia.estudiante.PersistenciaEstudianteBD;
import com.mycompany.mavenproject3.persistencia.persona.PersistenciaPersonaBD;

import java.util.*;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroEstudiante {
	private final ControladorEstudiante controladorEstudiante;
	private final ControladorMunicipio controladorMunicipio;
	private final ControladorPersona controladorPersona;

	public AdministradorRegistroEstudiante(H2Server db) {

		this.controladorEstudiante = new ControladorEstudiante(db);
		this.controladorMunicipio = new ControladorMunicipio(db);
		this.controladorPersona = new ControladorPersona(db);
	}

	public void menu() {
		while (true) {
			int opcion = mostrarMenu("MENU ESTUDIANTE",
				List.of("Crear estudiante", "Editar informacion personal",
					"Editar informacion academica","Obtener estudiante",
					"Listar estudiantes", "Eliminar estudiante","Salir"
				)
			);

			switch (opcion) {
				case 1: crearEstudiante();break;
				case 2: editarEstudianteInformacionPersonal();break;
				case 3: editarEstudianteInformacionAcademica();break;
				case 4: obtenerEstudiante();break;
				case 5: listarEstudiantes(); break;
				case 6: eliminarEstudiante(); break;
				case 7: return;
				default: break;
			}

		}
	}


	public void crearEstudiante(){
		String nombres = (String) leerValorPorConsola("Nombres estudiante: ", String.class);
		String apellidos = (String) leerValorPorConsola("Apellidos estudiante: ", String.class);
		String calle = (String) leerValorPorConsola("Calle: ", String.class);
		String carrera = (String) leerValorPorConsola("Carrera: ", String.class);
		String coordenada = (String) leerValorPorConsola("Coordenada: ", String.class);
		String descripcion = (String) leerValorPorConsola("Descripcion: ", String.class);
		Integer municipioId = (Integer) leerValorPorConsola("Id Municipio: ", Integer.class);
		String codigo = (String) leerValorPorConsola("Codigo: ", String.class);
		String programa = (String) leerValorPorConsola("Programa: ", String.class);
		Double promedio = (Double) leerValorPorConsola("Promedio: ", Double.class);


		Municipio municipio = controladorMunicipio.obtenerMunicipio(municipioId);
		Direccion direccion = new Direccion(calle, carrera, coordenada, descripcion,municipio);
		Estudiante estudiante = new Estudiante(nombres, apellidos, direccion, codigo, programa, promedio);
		controladorEstudiante.crearEstudiante(estudiante);
		System.out.print("\n.......Estudiante creado exitosamente\n");
	}



	public void obtenerEstudiante(){
		Integer id = (Integer) leerValorPorConsola("Id de estudiante: ", Integer.class);
		Estudiante estudiante = controladorEstudiante.obtenerEstudiante(id);

		List<String> campos = new ArrayList<>(List.of(
			"ID", "nombres", "apellidos", "calle", "carrera", "coordenada", "descripcion", "municipio",
			"codigo", "programa", "promedio"));
		List<String> valores = new ArrayList<>(
			List.of( estudiante.getId().toString(),
				estudiante.getNombres(), estudiante.getApellidos(), estudiante.getDireccion().getCalle(),
				estudiante.getDireccion().getCarrera(), estudiante.getDireccion().getCoordenada(),
				estudiante.getDireccion().getDescripcion(), estudiante.getDireccion().getMunicipio().getNombre(),
				estudiante.getCodigo(), estudiante.getPrograma(), estudiante.getPromedio().toString()
			)
		);
		mostrarInfo("Informacion Estudiante", campos, valores);
	}


	public void editarEstudianteInformacionPersonal(){
		Integer id = (Integer) leerValorPorConsola("Id de estudiante: ", Integer.class);

		while(true) {
			Estudiante estudiante = controladorEstudiante.obtenerEstudiante(id);
			List<String> campos = new ArrayList<>(List.of(
				"nombres", "apellidos", "calle", "carrera", "coordenada", "descripcion"));
			List<String> valores = new ArrayList<>(
				List.of(
					estudiante.getNombres(), estudiante.getApellidos(), estudiante.getDireccion().getCalle(),
					estudiante.getDireccion().getCarrera(), estudiante.getDireccion().getCoordenada(),
					estudiante.getDireccion().getDescripcion()
				)
			);

			int opcion = menuEdicion("Editar Informacion Personal Estudiante", campos, valores);

			if (opcion == campos.size() + 1)
				return;
			if(opcion < 1 || opcion > campos.size() + 1)
				continue;
			Object valor = leerValorPorConsola("Nuevo valor: ", String.class);

			Integer personaId = controladorEstudiante.obtenerIdPersona(id);
			controladorPersona.editarPersona(personaId, campos.get(opcion - 1), valor);

			System.out.print("\n.......Estudiante actualizado exitosamente\n");
		}
	}

	public void editarEstudianteInformacionAcademica() {

		Integer id = (Integer) leerValorPorConsola("Id de estudiante: ", Integer.class);

		while(true) {
				Estudiante estudiante = controladorEstudiante.obtenerEstudiante(id);
				List<String> campos = new ArrayList<>(List.of("codigo", "programa", "promedio"));
				List<String> valores = new ArrayList<>(
					List.of(
						estudiante.getCodigo(), estudiante.getPrograma(), estudiante.getPromedio().toString()
					)
				);

				int opcion = menuEdicion("Editar Informacion Academica Estudiante", campos, valores);

				if (opcion == campos.size() + 1)
					return;
				if(opcion < 1 || opcion > campos.size() + 1)
					continue;
				Object valor = leerValorPorConsola("Nuevo valor: ", String.class);


				controladorEstudiante.editarEstudiante(id, campos.get(opcion - 1), valor);
			}
	}

	public void listarEstudiantes(){
		List<Estudiante> estudiantes = controladorEstudiante.listarEstudiantes();
		List<String> campos = new ArrayList<>(List.of(
			"ID", "nombres", "apellidos", "calle", "carrera", "coordenada", "descripcion",
			"municipio", "codigo", "programa", "promedio"));
		for(Estudiante estudiante : estudiantes){
		List<String> valores = new ArrayList<>(
			List.of( estudiante.getId().toString(),
				estudiante.getNombres(), estudiante.getApellidos(), estudiante.getDireccion().getCalle(),
				estudiante.getDireccion().getCarrera(), estudiante.getDireccion().getCoordenada(),
				estudiante.getDireccion().getDescripcion(), estudiante.getDireccion().getMunicipio().getNombre(),
				estudiante.getCodigo(), estudiante.getPrograma(), estudiante.getPromedio().toString()
			)
		);
			mostrarInfo("Informacion Estudiante", campos, valores);
		}
	}

	public void eliminarEstudiante(){
		Integer id = (Integer) leerValorPorConsola("Id de estudiante: ", Integer.class);
		controladorEstudiante.eliminarEstudiante(id);
		System.out.print("\n.......Estudiante eliminado exitosamente\n");
	}


}
