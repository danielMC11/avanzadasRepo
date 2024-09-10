package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.administradorRegistros.binario.AdministradorDeArchivos;
import com.mycompany.mavenproject3.base.*;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.empleado.EmpleadoDAOImpl;
import com.mycompany.mavenproject3.persistencia.persona.PersonaDAOImpl;

import java.util.ArrayList;
import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroEmpleado {
	private final EmpleadoDAOImpl empleadoDAO;
	private final PersonaDAOImpl personaDAO;

	private final AdministradorRegistroCargo crudCargo;

	public AdministradorRegistroEmpleado(AdaptadorBaseDatos db) {
		this.empleadoDAO = new EmpleadoDAOImpl(db);
		this.personaDAO = new PersonaDAOImpl(db);
		this.crudCargo  = new AdministradorRegistroCargo(db);
	}

	public void menu() {
		while (true) {
			int opcion = mostrarMenu("MENU EMPLEADO",
				List.of("Crear empleado", "Editar informacion personal",
					"Editar informacion laboral", "Obtener empleado",
					"Listar empleados", "Eliminar empleado", "Menu Cargo", "Salir"
				)
			);

			switch (opcion) {
				case 1: crearEmpleado(); break;
				case 2: editarEmpleadoInformacionPersonal(); break;
				case 3: editarEmpleadoInformacionLaboral(); break;
				case 4: obtenerEmpleado(); break;
				case 5: listarEmpleados(); break;
				case 6: eliminarEmpleado(); break;
				case 7:
					crudCargo.menu();
					break;
				case 8:
					return;
				default:
					break;
			}
		}

		}

		public void crearEmpleado(){

			String nombres = (String) leerValorPorConsola("Nombres empleado: ", String.class);
			String apellidos = (String) leerValorPorConsola("Apellidos empleado: ", String.class);
			String calle = (String) leerValorPorConsola("Calle: ", String.class);
			String carrera = (String) leerValorPorConsola("Carrera: ", String.class);
			String coordenada = (String) leerValorPorConsola("Coordenada: ", String.class);
			String descripcion = (String) leerValorPorConsola("Descripcion: ", String.class);
			Integer municipioId = (Integer) leerValorPorConsola("Id Municipio: ", Integer.class);
			Double salario = (Double) leerValorPorConsola("Salario: ", Double.class);
			Integer cargoId = (Integer) leerValorPorConsola("Id Cargo: ", Integer.class);


			Municipio municipio = personaDAO.municipioDAO.buscarPorId(municipioId);
			Direccion direccion = new Direccion(calle, carrera, coordenada, descripcion,municipio);
			Cargo cargo = empleadoDAO.cargoDAO.buscarPorId(cargoId);
			Empleado empleado = new Empleado(nombres, apellidos, direccion, salario, cargo);
			empleadoDAO.guardar(empleado);
			System.out.print("\n.......Empleado creado exitosamente\n");

		}

		public void obtenerEmpleado(){
			Integer id = (Integer) leerValorPorConsola("Id de empleado: ", Integer.class);
			Empleado empleado = empleadoDAO.buscarPorId(id);
			List<String> campos = new ArrayList<>(List.of(
				"ID", "nombres", "apellidos", "calle", "carrera", "coordenada", "descripcion", "municipio",
				"salario", "cargo"));
			List<String> valores = new ArrayList<>(
				List.of( empleado.getId().toString(),
					empleado.getNombres(), empleado.getApellidos(), empleado.getDireccion().getCalle(),
					empleado.getDireccion().getCarrera(), empleado.getDireccion().getCoordenada(),
					empleado.getDireccion().getDescripcion(), empleado.getDireccion().getMunicipio().getNombre(),
					empleado.getSalario().toString(), empleado.getCargo().getNombre()
				)
			);
			mostrarInfo("Informacion Empleado", campos, valores);
		}

	public void editarEmpleadoInformacionPersonal(){
		Integer id = (Integer) leerValorPorConsola("Id de empleado: ", Integer.class);

		while(true) {
			Empleado empleado = empleadoDAO.buscarPorId(id);
			List<String> campos = new ArrayList<>(List.of(
				"nombres", "apellidos", "calle", "carrera", "coordenada", "descripcion"));
			List<String> valores = new ArrayList<>(
				List.of(
					empleado.getNombres(), empleado.getApellidos(), empleado.getDireccion().getCalle(),
					empleado.getDireccion().getCarrera(), empleado.getDireccion().getCoordenada(),
					empleado.getDireccion().getDescripcion()
				)
			);

			int opcion = menuEdicion("Editar Informacion Personal Empleado", campos, valores);

			if (opcion == campos.size() + 1)
				return;
			if(opcion < 1 || opcion > campos.size() + 1)
				continue;
			Object valor = leerValorPorConsola("Nuevo valor: ", String.class);

			Integer personaId = empleadoDAO.obtenerIdPersona(id);
			personaDAO.actualizar(personaId, campos.get(opcion - 1), valor);
		}
	}

	public void editarEmpleadoInformacionLaboral() {

		Integer id = (Integer) leerValorPorConsola("Id de empleado: ", Integer.class);

		while(true) {
			Empleado empleado = empleadoDAO.buscarPorId(id);
			List<String> campos = new ArrayList<>(List.of("salario"));
			List<String> valores = new ArrayList<>(
				List.of(
					empleado.getSalario().toString()
				)
			);

			int opcion = menuEdicion("Editar Informacion Laboral Empleado", campos, valores);

			if (opcion == campos.size() + 1)
				return;
			if(opcion < 1 || opcion > campos.size() + 1)
				continue;
			Object valor = leerValorPorConsola("Nuevo valor: ", String.class);


			empleadoDAO.actualizar(id, campos.get(opcion - 1), valor);
		}
	}

	public void listarEmpleados(){
		List<Empleado> empleados = empleadoDAO.buscarTodos();
		List<String> campos = new ArrayList<>(List.of(
			"ID", "nombres", "apellidos", "calle", "carrera", "coordenada", "descripcion",
			"municipio", "salario", "cargo"));
		for(Empleado empleado : empleados){
			List<String> valores = new ArrayList<>(
				List.of( empleado.getId().toString(),
					empleado.getNombres(), empleado.getApellidos(), empleado.getDireccion().getCalle(),
					empleado.getDireccion().getCarrera(), empleado.getDireccion().getCoordenada(),
					empleado.getDireccion().getDescripcion(), empleado.getDireccion().getMunicipio().getNombre(),
					empleado.getSalario().toString(), empleado.getCargo().getNombre()
				)
			);
			mostrarInfo("Informacion Empleado", campos, valores);
		}
	}

	public void eliminarEmpleado(){
		Integer id = (Integer) leerValorPorConsola("Id de empleado: ", Integer.class);
		empleadoDAO.eliminarPorId(id);
		System.out.print("\n.......Empleado eliminado exitosamente\n");
	}





}
