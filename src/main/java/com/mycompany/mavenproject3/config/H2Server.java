package com.mycompany.mavenproject3.config;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class H2Server {
	private String DB_DRIVER;
	private String DB_USER;
	private String DB_PASSWORD;
	private String URL;


	public H2Server(String DB_DRIVER, String DB_USER, String DB_PASSWORD, String URL) {
		this.DB_DRIVER = DB_DRIVER;
		this.DB_USER = DB_USER;
		this.DB_PASSWORD = DB_PASSWORD;
		this.URL = URL;
	}



	public void inicializar() {
		crearBaseDeDatos();
		crearTablas();
	}

	private void crearBaseDeDatos() {
		try (Connection conn = obtenerConexion()) {
				System.out.print("BASE DE DATOS CREADA O ACCEDIDA EXITOSAMENTE ...\n\n");
			} catch (Exception e) {
				System.err.println("ERROR AL CREAR O ACCEDER A BASE DATOS ... \n" + e.getMessage());
			}
	}



	private void crearTablas(){
		try(
			Connection conn = obtenerConexion();
			Statement stmt = conn.createStatement()
		){
			stmt.execute(Tablas.tablaPais());
			stmt.execute(Tablas.tablaDepartamento());
			stmt.execute(Tablas.tablaMunicipio());
			stmt.execute(Tablas.tablaPersona());
			stmt.execute(Tablas.tablaEstudiante());
			stmt.execute(Tablas.tablaCargo());
			stmt.execute(Tablas.tablaEmpleado());
		} catch (Exception e){
			System.err.println("ERROR CONEXION: " + e.getMessage());
		}

	}

	public Connection obtenerConexion() throws ClassNotFoundException, SQLException{
		Class.forName(DB_DRIVER);
		return DriverManager.getConnection(URL , DB_USER, DB_PASSWORD);
	}


}

