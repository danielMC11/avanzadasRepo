package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.base.*;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.dao.baseDeDatos.estudiante.EstudianteDAOImpl;
import com.mycompany.mavenproject3.dao.baseDeDatos.persona.PersonaDAOImpl;

import java.util.*;

import static com.mycompany.mavenproject3.Util.*;

public class AdministradorRegistroEstudiante {
	private final EstudianteDAOImpl estudianteDAO;
	private final PersonaDAOImpl personaDAO;

	public AdministradorRegistroEstudiante(H2Server db) {

		this.estudianteDAO = new EstudianteDAOImpl(db);
		this.personaDAO = new PersonaDAOImpl(db);
	}

	public void menu() {
		while (true) {
			int opcion = mostrarMenu("MENU ESTUDIANTE",
				List.of("Crear estudiante", "Editar informacion personal",
					"Editar informacion academica","Obtener estudiante",
					"Listar estudiantes", "Eliminar estudiante", "Guardar Estudiante en archivo plano","Ver los Estudiantos registrados en el archivo plano","Salir"
				)
			);

			switch (opcion) {
				case 1: crearEstudiante();break;
				case 2: editarEstudianteInformacionPersonal();break;
				case 3: editarEstudianteInformacionAcademica();break;
				case 4: obtenerEstudiante();break;
				case 5: listarEstudiantes(); break;
				case 6: eliminarEstudiante(); break;
				case 7: guardarEstudianteArchivo();
				case 8: AdministradorDeArchivos.leerArchivoPlano("Estudiantes.txt");
				case 9: return;
				default: break;
			}

		}
	}

	void guardarEstudianteArchivo(){
		String nombreArchivo = "Estudiantes.txt";

		Integer idEstudiante = (Integer) leerValorPorConsola("Id empleado: ",Integer.class);
		String nombres = (String) leerValorPorConsola("Nombres empleado: ", String.class);
		String apellidos = (String) leerValorPorConsola("Apellidos empleado: ", String.class);
		String codigo = (String) leerValorPorConsola("Codigo: ", String.class);
		String programa = (String) leerValorPorConsola("Programa: ", String.class);
		Double promedio = (Double) leerValorPorConsola("Promedio: ", Double.class);
		String calle = (String) leerValorPorConsola("Calle: ", String.class);
		String carrera = (String) leerValorPorConsola("Carrera: ", String.class);
		String coordenada = (String) leerValorPorConsola("Coordenada: ", String.class);
		String descripcion = (String) leerValorPorConsola("Descripcion: ", String.class);
		Integer municipioId = (Integer) leerValorPorConsola("Id Municipio: ", Integer.class);
		String nombreMunicipio = (String) leerValorPorConsola("Nombre municipio:",String.class);
		Integer idDepartamento = (Integer) leerValorPorConsola("Id Departamento: ", Integer.class);
		String nombreDepartamento = (String) leerValorPorConsola("Nombre departamento: ", String.class);
		Integer paisId = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		String nombrePais = (String) leerValorPorConsola("Nombre pais: ", String.class);

		Pais pais = new Pais(paisId,nombrePais);
		Departamento departamento = new Departamento(idDepartamento,nombreDepartamento,pais);
		Municipio municipio = new Municipio(municipioId,nombreMunicipio,departamento);
		Direccion direccion = new Direccion(calle,carrera,coordenada,descripcion,municipio);
		Estudiante estudiante = new Estudiante(idEstudiante,nombres,apellidos,direccion,codigo,programa,promedio);
		System.out.println("\n------El archivo se guardo en "+nombreArchivo+".txt exitosamente\n");
		AdministradorDeArchivos.escribirEnArchivo(estudiante,nombreArchivo);
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


		Municipio municipio = personaDAO.municipioDAO.buscarPorId(municipioId);
		Direccion direccion = new Direccion(calle, carrera, coordenada, descripcion,municipio);
		Estudiante estudiante = new Estudiante(nombres, apellidos, direccion, codigo, programa, promedio);
		estudianteDAO.guardar(estudiante);
		System.out.print("\n.......Estudiante creado exitosamente\n");
	}



	public void obtenerEstudiante(){
		Integer id = (Integer) leerValorPorConsola("Id de estudiante: ", Integer.class);
		Estudiante estudiante = estudianteDAO.buscarPorId(id);

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
			Estudiante estudiante = estudianteDAO.buscarPorId(id);
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

			Integer personaId = estudianteDAO.obtenerIdPersona(id);
			personaDAO.actualizar(personaId, campos.get(opcion - 1), valor);

			System.out.print("\n.......Estudiante actualizado exitosamente\n");
		}
	}

	public void editarEstudianteInformacionAcademica() {

		Integer id = (Integer) leerValorPorConsola("Id de estudiante: ", Integer.class);

		while(true) {
				Estudiante estudiante = estudianteDAO.buscarPorId(id);
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


				estudianteDAO.actualizar(id, campos.get(opcion - 1), valor);
			}
	}

	public void listarEstudiantes(){
		List<Estudiante> estudiantes = estudianteDAO.buscarTodos();
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
		estudianteDAO.eliminarPorId(id);
		System.out.print("\n.......Estudiante eliminado exitosamente\n");
	}




}
