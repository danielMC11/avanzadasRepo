package com.mycompany.mavenproject3.administradorRegistros;


import com.mycompany.mavenproject3.administradorRegistros.binario.AdministradorDeArchivos;
import com.mycompany.mavenproject3.base.Departamento;
import com.mycompany.mavenproject3.base.Municipio;
import com.mycompany.mavenproject3.base.Pais;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.persistencia.municipio.MunicipioDAOImpl;

import java.util.List;

import static com.mycompany.mavenproject3.util.ConsolaUtil.*;
import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class AdministradorRegistroMunicipio {
	private final MunicipioDAOImpl municipioDAO;

	public AdministradorRegistroMunicipio(AdaptadorBaseDatos db) {
		this.municipioDAO = new MunicipioDAOImpl(db);
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
		Municipio municipio = new Municipio(nombreMunicipio, municipioDAO.departamentoDAO.buscarPorId(departamentoId));
		municipioDAO.guardar(municipio);
		System.out.print("\n.......Municipio creado exitosamente\n");
	}


	public void editarMunicipio(){
		Integer id = (Integer) leerValorPorConsola("Id municipio: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);

		municipioDAO.actualizar(id, "nombre", nuevoNombre);
		System.out.print("\n.......Municipio actualizado exitosamente\n");
	}

	public void obtenerMunicipio(){
		Integer id = (Integer) leerValorPorConsola("Id municipio: ", Integer.class);
		Municipio municipio = municipioDAO.buscarPorId(id);
		System.out.println(municipio);
	}

	public void listarMunicipios(){
		for(Municipio municipio : municipioDAO.buscarTodos()){
			System.out.println(municipio);
		}
	}

	public void eliminarMunicipio(){
		Integer id = (Integer) leerValorPorConsola("Id municipio: ", Integer.class);
		municipioDAO.eliminarPorId(id);
		System.out.print("\n.......Municipio eliminado exitosamente\n");
	}
}
