package com.mycompany.mavenproject3.administradorRegistros;

import com.mycompany.mavenproject3.base.Pais;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.dao.baseDeDatos.pais.PaisDAOImpl;

import java.util.List;

import static com.mycompany.mavenproject3.Util.leerValorPorConsola;
import static com.mycompany.mavenproject3.Util.mostrarMenu;

public class AdministradorRegistroPais {
	private final PaisDAOImpl paisDAO;

	public AdministradorRegistroPais(H2Server db) {
		this.paisDAO = new PaisDAOImpl(db);
	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU PAIS",
				List.of("Crear Pais", "Editar Pais", "Obtener Pais",
					"Listar Paises", "Eliminar Pais","Guardar pais en archivo plano","Ver los paises registrados en el archivo plano","Salir"
				)
			);

			switch (opcion) {
				case 1: crearPais();break;
				case 2: editarPais(); break;
				case 3: obtenerPais(); break;
				case 4: listarPaises();break;
				case 5: eliminarPais(); break;
				case 6: guardarPaisArchivo(); break;
				case 7: AdministradorDeArchivos.leerArchivoPlano("Paises.txt");
				case 8: return;
				default: break;
			}

		}
	}

	public  void guardarPaisArchivo(){
		String nombreArchivo = "Paises.txt";

		Integer paisId = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		String nombrePais = (String) leerValorPorConsola("Nombre pais: ", String.class);

		Pais pais = new Pais(paisId,nombrePais);
		AdministradorDeArchivos.escribirEnArchivo(pais,nombreArchivo);
		System.out.println("\n------El archivo se guardo en "+nombreArchivo+".txt exitosamente\n");
	}
	public void crearPais(){
		String nombrePais = (String) leerValorPorConsola("Nombre pais: ", String.class);
		Pais pais = new Pais(nombrePais);
		paisDAO.guardar(pais);
		System.out.print("\n.......Pais creado exitosamente\n");
	}

	public void editarPais(){
		Integer id = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		String nuevoNombre = (String) leerValorPorConsola("Nuevo nombre: ", String.class);

		paisDAO.actualizar(id, "nombre", nuevoNombre);
		System.out.print("\n.......Pais actualizado exitosamente\n");
	}

	public void obtenerPais(){
		Integer id = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		Pais pais = paisDAO.buscarPorId(id);
		System.out.println(pais);
	}

	public void listarPaises(){
		for(Pais pais : paisDAO.buscarTodos()){
			System.out.println(pais);
		}
	}

	public void eliminarPais(){
		Integer id = (Integer) leerValorPorConsola("Id pais: ", Integer.class);
		paisDAO.eliminarPorId(id);
		System.out.print("\n.......Pais eliminado exitosamente\n");
	}
}
