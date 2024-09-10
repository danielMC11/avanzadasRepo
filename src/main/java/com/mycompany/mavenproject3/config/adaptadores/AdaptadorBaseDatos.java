package com.mycompany.mavenproject3.config.adaptadores;

import java.sql.Connection;
import java.sql.SQLException;

public interface AdaptadorBaseDatos {
    void inicializar();
    Connection obtenerConexion() throws ClassNotFoundException, SQLException;

    void crearTablas();

    void terminar();
}
