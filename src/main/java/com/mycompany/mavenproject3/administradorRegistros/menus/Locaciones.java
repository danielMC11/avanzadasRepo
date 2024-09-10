package com.mycompany.mavenproject3.administradorRegistros.menus;


import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroDepartamento;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroMunicipio;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroPais;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;

import java.util.List;

import static com.mycompany.mavenproject3.util.MenuUtil.*;

public class Locaciones {
	private final AdministradorRegistroPais administradorRegistroPais;
	private final AdministradorRegistroDepartamento administradorRegistroDepartamento;

	private final AdministradorRegistroMunicipio administradorRegistroMunicipio;

	public Locaciones(AdaptadorBaseDatos db) {
		this.administradorRegistroPais = new AdministradorRegistroPais(db);
		this.administradorRegistroDepartamento = new AdministradorRegistroDepartamento(db);
		this.administradorRegistroMunicipio = new AdministradorRegistroMunicipio(db);
	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU LOCACIONES",
				List.of("Menu Pais", "Menu departamento", "Menu municipio", "Salir"
				)
			);
			switch (opcion) {
				case 1: administradorRegistroPais.menu();break;
				case 2: administradorRegistroDepartamento.menu();break;
				case 3: administradorRegistroMunicipio.menu(); break;
				case 4: return;
				default: break;
			}
		}

	}


}
