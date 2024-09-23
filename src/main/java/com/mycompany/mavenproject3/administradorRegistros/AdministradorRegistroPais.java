package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.base.Pais;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.controlador.ControladorPais;

import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroPais {
	private final ControladorPais controladorPais;

	public AdministradorRegistroPais(AdaptadorBaseDatos db) {
		this.controladorPais = new ControladorPais(db);
	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU PAIS",
				List.of("Crear Pais", "Editar Pais", "Obtener Pais",
					"Listar Paises", "Eliminar Pais","Salir"
				)
			);

			switch (opcion) {
				case 1: crearPais();break;
				case 2: editarPais(); break;
				case 3: obtenerPais(); break;
				case 4: listarPaises();break;
				case 5: eliminarPais(); break;
				case 6: return;
				default: break;
			}

		}
	}
	public void crearPais(){
		String nombrePais = (String) leerValorPorConsola("Nombre pais: ", String.class);
		Pais pais = new Pais(nombrePais);
		controladorPais.crearPais(pais);
		System.out.print("\n.......Pais creado exitosamente\n");
	}

	public void editarPais(){
		Integer id = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);

		controladorPais.editarPais(id, "Nombre", nuevoNombre);
		System.out.print("\n.......Pais actualizado exitosamente\n");
	}

	public void obtenerPais(){
		Integer id = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		Pais pais = controladorPais.obtenerPais(id);
		System.out.println(pais);
	}

	public void listarPaises(){
		for(Pais pais : controladorPais.listarPaises()){
			System.out.println(pais);
		}
	}

	public void eliminarPais(){
		Integer id = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		controladorPais.eliminarPais(id);
		System.out.print("\n.......Pais eliminado exitosamente\n");
	}
}
