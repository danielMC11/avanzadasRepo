package com.mycompany.mavenproject3.config.fabricas;

import com.mycompany.mavenproject3.config.Tablas;
import com.mycompany.mavenproject3.config.adaptadores.AdaptadorBaseDatos;
import com.mycompany.mavenproject3.config.adaptadores.H2AdaptadorBaseDatos;

public class FabricaBaseDeDatos {
    public static AdaptadorBaseDatos obtenerAdaptador(String tipoBaseDatos){
        return switch (tipoBaseDatos){
            case "H2" -> new H2AdaptadorBaseDatos();
            default -> throw new IllegalArgumentException("Tipo de base de datos no soportado");
        };
    }
}
