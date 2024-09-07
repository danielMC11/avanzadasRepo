/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

Javic Camilo Rojas - 160004733
Daniel Ricardo Montero - 160004719

 */
//
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroEstudiantesInscritos;
import com.mycompany.mavenproject3.administradorRegistros.AdministradorRegistroPersonalInscrito;
import com.mycompany.mavenproject3.agrupamiento.*;
import com.mycompany.mavenproject3.config.*;


import java.util.*;

import static com.mycompany.mavenproject3.Util.mostrarMenu;


/**
 *
 * @author Estudiante_MCA
 */



public class Main {


    public static void main(String[] args) {


			H2Server db = new H2Server(Modo.EMBEBIDO);
			db.inicializar();
			AdministradorRegistroEstudiantesInscritos administradorRegistroEstudiantesInscritos = new AdministradorRegistroEstudiantesInscritos();
			AdministradorRegistroPersonalInscrito administradorRegistroPersonalInscrito= new AdministradorRegistroPersonalInscrito();
			while(true) {
				int opcion = mostrarMenu("MENU Principal",
										List.of("Menu locaciones", "Menu Personal","Menu Estudiantes inscritos",
														"Menu Personal inscritos",
														"Salir"
											)
				);


				switch (opcion) {
					case 1:
						Locaciones loc = new Locaciones(db);
						loc.menu();
						break;
					case 2:
						Personal per = new Personal(db);
						per.menu();
						break;
					case 3:
						administradorRegistroEstudiantesInscritos.menu(); break;
					case 4:administradorRegistroPersonalInscrito.menu(); break;
					case 5:
						db.terminar();
					return;
					default: break;
				}

			}

		}
}


