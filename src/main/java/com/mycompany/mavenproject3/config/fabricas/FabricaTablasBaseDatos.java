package com.mycompany.mavenproject3.config.fabricas;

import com.mycompany.mavenproject3.config.adaptadores.AdaptadorTablas;
import com.mycompany.mavenproject3.config.adaptadores.H2AdaptadorTablas;

public class FabricaTablasBaseDatos {
	public static AdaptadorTablas obtenerAdaptador(String tipoBaseDatos){

		return switch (tipoBaseDatos){
			case "H2"  -> new H2AdaptadorTablas();
			default -> throw new IllegalArgumentException("Tipo Base de datos no soportado");
		};
	}
}
