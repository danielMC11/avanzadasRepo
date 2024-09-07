package com.mycompany.mavenproject3.config;

public class Tablas {

	public static String tablaPais() {
		return "CREATE TABLE IF NOT EXISTS PAIS (" +
			"paisId INT PRIMARY KEY AUTO_INCREMENT, " +
			"nombre VARCHAR(50));";
	}

	public static String tablaDepartamento() {
		return "CREATE TABLE IF NOT EXISTS DEPARTAMENTO (" +
			"departamentoId INT PRIMARY KEY AUTO_INCREMENT, " +
			"nombre VARCHAR(50), " +
			"paisId INT, " +
			"FOREIGN KEY (paisId) REFERENCES PAIS(paisId));";
	}

	public static String tablaMunicipio() {
		return "CREATE TABLE IF NOT EXISTS MUNICIPIO (" +
			"municipioId INT PRIMARY KEY AUTO_INCREMENT, " +
			"nombre VARCHAR(50), " +
			"departamentoId INT, " +
			"FOREIGN KEY (departamentoId) REFERENCES DEPARTAMENTO(departamentoId));";
	}


	public static String tablaPersona(){
		return "CREATE TABLE IF NOT EXISTS PERSONA(" +
			     "personaId INT PRIMARY KEY AUTO_INCREMENT," +
			     "nombres VARCHAR(50),"	+ 	"apellidos VARCHAR(50)," +
					 "calle VARCHAR(50)," + "carrera VARCHAR(50)," +
			     "coordenada VARCHAR(50)," + "descripcion VARCHAR(50)," +
				   "municipioId INT, " +
			     "FOREIGN KEY (municipioId) REFERENCES MUNICIPIO(municipioId));";
	}

	public static String tablaEstudiante(){
		return "CREATE TABLE IF NOT EXISTS ESTUDIANTE(" +
					"estudianteId INT PRIMARY KEY AUTO_INCREMENT," +
					"codigo VARCHAR(50),"	+ 	"programa VARCHAR(50)," +
					"promedio DOUBLE," + "personaId INT, " +
					"FOREIGN KEY (personaId) REFERENCES PERSONA(personaId) ON DELETE CASCADE);";
	}

	public static String tablaCargo() {
		return "CREATE TABLE IF NOT EXISTS CARGO(" +
			"cargoId INT PRIMARY KEY AUTO_INCREMENT, " +
			"nombre VARCHAR(50));";
	}

	public static String tablaEmpleado(){
		return "CREATE TABLE IF NOT EXISTS EMPLEADO(" +
			"empleadoId INT PRIMARY KEY AUTO_INCREMENT," +
			"salario DOUBLE," + "personaId INT, " + "cargoId INT," +
			"FOREIGN KEY (cargoId) REFERENCES CARGO(cargoId) ON DELETE CASCADE,"	+
			"FOREIGN KEY (personaId) REFERENCES PERSONA(personaId) ON DELETE CASCADE);";
	}
}
