package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.controlador.ControladorDepartamento;
import com.mycompany.mavenproject3.controlador.ControladorPais;
import com.mycompany.mavenproject3.persistencia.departamento.PersistenciaDepartamentoBD;

import java.util.List;


import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroDepartamento {
	private final ControladorDepartamento controladorDepartamento;
	private final ControladorPais controladorPais;

	public AdministradorRegistroDepartamento(H2Server db) {
		this.controladorDepartamento = new ControladorDepartamento(db);
		this.controladorPais = new ControladorPais(db);
	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU DEPARTAMENTO",
				List.of("Crear Departamento", "Editar Departamento", "Obtener Departamento",
					"Listar Departamentos", "Eliminar Departamento", "Salir"
				)
			);

			switch (opcion) {
				case 1: crearDepartamento();break;
				case 2: editarDepartamento(); break;
				case 3: obtenerDepartamento(); break;
				case 4: listarDepartamentos();break;
				case 5: eliminarDepartamento(); break;
				case 6: return;
				default: break;
			}

		}
	}

	public void crearDepartamento(){

		String nombreDepartamento = (String) leerValorPorConsola("Nombre departamento: ", String.class);
		Integer paisId = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		Departamento departamento = new Departamento(nombreDepartamento, controladorPais.obtenerPais(paisId));

		controladorDepartamento.crearDepartamento(departamento);

		System.out.print("\n.......Departamento creado exitosamente\n");
	}


	public void editarDepartamento(){
		Integer id = (Integer) leerValorPorConsola("Id departamento: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);

		controladorDepartamento.editarDepartamento(id, "Nombre", nuevoNombre);
		System.out.print("\n.......Departamento actualizado exitosamente\n");
	}

	public void obtenerDepartamento(){
		Integer id = (Integer) leerValorPorConsola("Id departamento: ", Integer.class);
		Departamento departamento = controladorDepartamento.obtenerDepartamento(id);
		System.out.println(departamento);
	}

	public void listarDepartamentos(){
		for(Departamento departamento : controladorDepartamento.listarDepartamentos()){
			System.out.println(departamento);
		}
	}

	public void eliminarDepartamento(){
		Integer id = (Integer) leerValorPorConsola("Id departamento: ", Integer.class);
		controladorDepartamento.eliminarDepartamento(id);
		System.out.print("\n.......Departamento eliminado exitosamente\n");
	}


}
