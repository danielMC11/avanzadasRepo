package com.mycompany.mavenproject3.persistencia.cargo;


public interface PersistenciaCargoArchivoPlano extends PersistenciaCargo{
/*
	@Override
	public Cargo guardar(String entidad, Cargo cargo) {

		try {
			String nombreArchivo = "cargo.csv";
			File archivo = new File("./data/ArchivosPlanos/" + nombreArchivo);
			boolean fileExists = archivo.exists();
			boolean isEmptyFile = fileExists && archivo.length() == 0;

			try (FileWriter fw = new FileWriter("./data/ArchivosPlanos/" + nombreArchivo, true);
					 PrintWriter pw = new PrintWriter(fw)) {

				if (!fileExists || isEmptyFile) {
					pw.println("ID,Nombre");
				}
				Integer id = cargarUltimoId("./data/ArchivosPlanos/" + nombreArchivo);
				pw.println(id + "," + cargo.getNombre());
			}

			System.out.println("Cargo guardado exitosamente en " + "./data/ArchivosPlanos/" + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
		}
		return null;
	}*/

}
