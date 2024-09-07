package com.mycompany.mavenproject3.agrupamiento;

import com.mycompany.mavenproject3.config.H2Server;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroEmpleado;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroEstudiante;

import java.util.List;


import static com.mycompany.mavenproject3.Util.mostrarMenu;

public class Personal {
	AdministradorRegistroEstudiante crudEstudiante;

	AdministradorRegistroEmpleado crudEmpleado;

	public Personal(H2Server db){

		crudEstudiante = new AdministradorRegistroEstudiante(db);
		crudEmpleado = new AdministradorRegistroEmpleado(db);
	}

	public void menu(){
		while(true) {
			int opcion = mostrarMenu("MENU PERSONAL",
				List.of("Menu Estudiante", "Menu Empleado", "Salir"
				)
			);
			switch (opcion) {
				case 1: crudEstudiante.menu();break;
				case 2: crudEmpleado.menu(); break;
				case 3: return;
				default: break;
			}
		}

	}
}
