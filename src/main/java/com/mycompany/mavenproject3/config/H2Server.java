package com.mycompany.mavenproject3.config;
import com.mycompany.mavenproject3.Modo;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class H2Server {
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWORD = "";


	private Modo modoConexion;

	private String url;
	private Server tcpServer = null;


	public H2Server(Modo modoConexion){
		this.modoConexion = modoConexion;
		this.url = determinarUrl();

	}


	private String determinarUrl(){

        return switch (modoConexion) {
            case EMBEBIDO -> "jdbc:h2:./data/4719_4733";
            case SERVIDOR_TCP -> "jdbc:h2:tcp://localhost/./data/4719_4733";
        };

	}


	public void inicializar() {

		if(modoConexion == Modo.SERVIDOR_TCP)
			encenderServidor();

		crearBaseDeDatos();
		crearTablas();


	}

		private void crearBaseDeDatos() {
		try (Connection conexion = DriverManager.getConnection(url, DB_USER, DB_PASSWORD)) {
				System.out.print("BASE DE DATOS CREADA O ACCEDIDA EXITOSAMENTE ...\n\n");
			} catch (SQLException e) {
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
		return DriverManager.getConnection(url , DB_USER, DB_PASSWORD);
	}

	private void encenderServidor(){
		Scanner scanner = new Scanner(System.in);


		try {
				tcpServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
				System.out.print("SERVIDOR ENCENDIDO EXITOSAMENTE EN PUERTO 9092\n\n");
		} catch (Exception e) {
				System.err.println("FALLO AL ENCENDER SERVIDOR TCP: " + e.getMessage());
			}
	}

	public void terminar(){
		System.out.println("FIN DEL PROGRAMA....");

		if(modoConexion == Modo.SERVIDOR_TCP)
			cerrarServidor();

	}

	private void cerrarServidor() {
			try {
				if (tcpServer != null && tcpServer.isRunning(false))
					tcpServer.stop();
				System.out.println("SERVIDOR CERRADO EXITOSAMENTE....");

			} catch (Exception e) {
				System.err.println("FALLO AL CERRAR SERVIDOR TCP: " + e.getMessage());
			}
	}


}

