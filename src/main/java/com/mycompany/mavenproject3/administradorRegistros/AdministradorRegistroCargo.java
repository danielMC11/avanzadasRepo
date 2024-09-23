package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.base.Cargo;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.controlador.ControladorCargo;

import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;



public class AdministradorRegistroCargo {

	private final ControladorCargo controladorCargo;


	public AdministradorRegistroCargo(AdaptadorBaseDatos db) {

		this.controladorCargo = new ControladorCargo(db);
	}

	public void menu() {
		while (true) {
			int opcion = mostrarMenu("MENU CARGO",
				List.of("Crear cargo", "Editar cargo","Obtener Cargo",
					"Listar cargos", "Eliminar cargo", "Salir"
				)
			);

			switch (opcion) {
				case 1:	crearCargo(); break;
				case 2: editarCargo(); break;
				case 3:	obtenerCargo(); break;
				case 4: listarCargos(); break;
				case 5: eliminarCargo(); break;
				case 6: return;
				default: break;
			}

		}
	}

	public void crearCargo(){
		String nombreCargo = (String) leerValorPorConsola("Nombre cargo: ", String.class);
		Cargo cargo = new Cargo(nombreCargo);
		controladorCargo.crearCargo(cargo);
		System.out.print("\n.......Cargo creado exitosamente\n");
	}


	public void editarCargo(){
		Integer id = (Integer) leerValorPorConsola("Id cargo: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);
		controladorCargo.editarCargo(id, "nombre", nuevoNombre);
		System.out.print("\n.......Cargo actualizado exitosamente\n");
	}

	public void obtenerCargo(){
		Integer id = (Integer) leerValorPorConsola("Id cargo: ", Integer.class);
		Cargo cargo = controladorCargo.obtenerCargo(id);
		System.out.println(cargo);
	}

	public void listarCargos(){
		for(Cargo cargo : controladorCargo.listarCargos()){
			System.out.println(cargo);
		}
	}

	public void eliminarCargo(){
		Integer id = (Integer) leerValorPorConsola("Id cargo: ", Integer.class);
		controladorCargo.eliminarCargo(id);
		System.out.print("\n.......Cargo eliminado exitosamente\n");
	}


}
