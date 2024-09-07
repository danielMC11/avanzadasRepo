package com.mycompany.mavenproject3.agrupamiento;


import com.mycompany.mavenproject3.administradorRegistros.AdministradorDeArchivos;
import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroDepartamento;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroMunicipio;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroPais;

import java.util.List;

import static com.mycompany.mavenproject3.Util.mostrarMenu;


public class Locaciones {
	private final AdministradorRegistroPais crudPais;
	private final AdministradorRegistroDepartamento crudDepartamento;

	private final AdministradorRegistroMunicipio crudMunicipio;

	public Locaciones(H2Server db) {
		this.crudPais = new AdministradorRegistroPais(db);
		this.crudDepartamento = new AdministradorRegistroDepartamento(db);
		this.crudMunicipio = new AdministradorRegistroMunicipio(db);
	}
	public void menuArchivosPlanos(Object object, String nombreArchivo) {
		while (true) {
			int opcion = mostrarMenu("MENU ARCHIVOS PLANOS",
					List.of("Escribir en archivo plano", "Leer Archivo plano", "Salir"
					)
			);
			switch (opcion) {
				case 1:
					AdministradorDeArchivos.escribirEnArchivo(object,nombreArchivo);
					break;
				case 2:
					crudDepartamento.menu();
					break;
				case 3:
					crudMunicipio.menu();
					break;
				case 4:
					return;
				default:
					break;
			}
		}
	}
	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU LOCACIONES",
				List.of("Menu Pais", "Menu departamento", "Menu municipio", "Salir"
				)
			);
			switch (opcion) {
				case 1: crudPais.menu();break;
				case 2: crudDepartamento.menu();break;
				case 3: crudMunicipio.menu(); break;
				case 4: return;
				default: break;
			}
		}

	}


}
