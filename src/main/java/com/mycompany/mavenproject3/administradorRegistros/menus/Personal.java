package com.mycompany.mavenproject3.administradorRegistros.menus;

import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroEmpleado;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroEstudiante;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;

import java.util.List;


import static com.mycompany.mavenproject3.util.MenuUtil.*;
public class Personal {
	AdministradorRegistroEstudiante administradorRegistroEstudiante;

	AdministradorRegistroEmpleado administradorRegistroEmpleado;

	public Personal(AdaptadorBaseDatos db){

		administradorRegistroEstudiante = new AdministradorRegistroEstudiante(db);
		administradorRegistroEmpleado = new AdministradorRegistroEmpleado(db);
	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU PERSONAL",
				List.of("Menu Estudiante", "Menu Empleado", "Salir"
				)
			);
			switch (opcion) {
				case 1: administradorRegistroEstudiante.menu();break;
				case 2: administradorRegistroEmpleado.menu(); break;
				case 3: return;
				default: break;
			}
		}

	}
}
