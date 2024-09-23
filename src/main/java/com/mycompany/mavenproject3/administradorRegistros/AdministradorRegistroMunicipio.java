package com.mycompany.mavenproject3.administradorRegistros;


import com.mycompany.mavenproject3.base.Municipio;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.controlador.ControladorDepartamento;
import com.mycompany.mavenproject3.controlador.ControladorMunicipio;

import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroMunicipio {
	private final ControladorMunicipio controladorMunicipio;
	private final ControladorDepartamento controladorDepartamento;


	public AdministradorRegistroMunicipio(AdaptadorBaseDatos db) {

		this.controladorMunicipio = new ControladorMunicipio(db);
		this.controladorDepartamento = new ControladorDepartamento(db);

	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU MUNICIPIO",
				List.of("Crear Municipio", "Editar Municipio", "Obtener Municipio",
					"Listar Municipios", "Eliminar Municipio", "Salir"
				)
			);

			switch (opcion) {
				case 1: crearMunicipio();break;
				case 2: editarMunicipio(); break;
				case 3: obtenerMunicipio(); break;
				case 4: listarMunicipios();break;
				case 5: eliminarMunicipio(); break;
				case 6: return;
				default: break;
			}

		}
	}

	public void crearMunicipio(){
		String nombreMunicipio = (String) leerValorPorConsola("Nombre municipio: ", String.class);
		Integer departamentoId = (Integer) leerValorPorConsola("Id departamento: ", Integer.class);
		Municipio municipio = new Municipio(nombreMunicipio, controladorDepartamento.obtenerDepartamento(departamentoId));
		controladorMunicipio.crearMunicipio(municipio);
		System.out.print("\n.......Municipio creado exitosamente\n");
	}


	public void editarMunicipio(){
		Integer id = (Integer) leerValorPorConsola("Id municipio: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);

		controladorMunicipio.editarMunicipio(id, "Nombre", nuevoNombre);
		System.out.print("\n.......Municipio actualizado exitosamente\n");
	}

	public void obtenerMunicipio(){
		Integer id = (Integer) leerValorPorConsola("Id municipio: ", Integer.class);
		Municipio municipio = controladorMunicipio.obtenerMunicipio(id);
		System.out.println(municipio);
	}

	public void listarMunicipios(){
		for(Municipio municipio : controladorMunicipio.listarMunicipios()){
			System.out.println(municipio);
		}
	}

	public void eliminarMunicipio(){
		Integer id = (Integer) leerValorPorConsola("Id municipio: ", Integer.class);
		controladorMunicipio.eliminarMunicipio(id);
		System.out.print("\n.......Municipio eliminado exitosamente\n");
	}
}
