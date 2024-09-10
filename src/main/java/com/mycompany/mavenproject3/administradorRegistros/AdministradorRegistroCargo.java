package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.cargo.PersistenciaCargoBD;

import java.util.List;

import static com.mycompany.mavenproject3.util.MenuUtil.*;


public class AdministradorRegistroCargo {

	private final PersistenciaCargoBD cargoDAO;
//	private final PersistenciaCargo persistencia;

	public AdministradorRegistroCargo(AdaptadorBaseDatos db) {

		this.cargoDAO = new PersistenciaCargoBD(db);
		//this.persistencia = new PersistenciaCargoArchivoPlano();
	}

	public void menu() {
		while (true) {
			int opcion = mostrarMenu("MENU CARGO",
				List.of("Crear cargo", "Editar cargo","Obtener Cargo",
					"Listar cargos", "Eliminar cargo","Guardar cargo en archivo plano","Ver los cargos registrados en el archivo plano", "Salir"
				)
			);
/*
			switch (opcion) {
				case 1:	crearCargo(); break;
				case 2: editarCargo(); break;
				case 3:	obtenerCargo(); break;
				case 4: listarCargos(); break;
				case 5: eliminarCargo(); break;
				case 6: return;
				default: break;
			}*/

		}
	}
/*
	public void crearCargo(){
		String nombreCargo = (String) leerValorPorConsola("Nombre cargo: ", String.class);
		Cargo cargo = new Cargo(nombreCargo);
		cargoDAO.guardar(cargo);
		System.out.print("\n.......Cargo creado exitosamente\n");
	}


	public void editarCargo(){
		Integer id = (Integer) leerValorPorConsola("Id cargo: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);
		cargoDAO.actualizar(id, "nombre", nuevoNombre);
		System.out.print("\n.......Cargo actualizado exitosamente\n");
	}

	public void obtenerCargo(){
		Integer id = (Integer) leerValorPorConsola("Id cargo: ", Integer.class);
		Cargo cargo = cargoDAO.buscarPorId(id);
		System.out.println(cargo);
	}

	public void listarCargos(){
		for(Cargo cargo : cargoDAO.buscarTodos()){
			System.out.println(cargo);
		}
	}

	public void eliminarCargo(){
		Integer id = (Integer) leerValorPorConsola("Id cargo: ", Integer.class);
		cargoDAO.eliminarPorId(id);
		System.out.print("\n.......Cargo eliminado exitosamente\n");
	}


*/
}
