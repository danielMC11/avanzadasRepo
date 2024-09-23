package com.mycompany.mavenproject3.config.adaptadores;

import com.mycompany.mavenproject3.config.fabricas.FabricaTablasBaseDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2AdaptadorBaseDatos implements AdaptadorBaseDatos{
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    private Connection conn;
    private final AdaptadorTablas h2ScriptSql = FabricaTablasBaseDatos.obtenerAdaptador("H2");

    private final String url;

    public H2AdaptadorBaseDatos() {
        this.url = determinarUrl();
    }

    private String determinarUrl() {
        return "jdbc:h2:./data/4719_4733";
    }
    @Override
    public void inicializar() {
        crearBaseDeDatos();
        crearTablas();
    }
    @Override
    public Connection obtenerConexion() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(url , DB_USER, DB_PASSWORD);
    }
    @Override
    public void crearTablas(){
        try(
                Connection conn = obtenerConexion();
                Statement stmt = conn.createStatement()
        ){

            stmt.execute(h2ScriptSql.crearTablaPais());
            stmt.execute(h2ScriptSql.crearTablaDepartamento());
            stmt.execute(h2ScriptSql.crearTablaMunicipio());
            stmt.execute(h2ScriptSql.crearTablaPersona());
            stmt.execute(h2ScriptSql.crearTablaEstudiante());
            stmt.execute(h2ScriptSql.crearTablaCargo());
            stmt.execute(h2ScriptSql.crearTablaEmpleado());
        } catch (Exception e){
            System.err.println("ERROR CONEXION: " + e.getMessage());
        }
    }
    private void crearBaseDeDatos() {
        try (Connection conn = obtenerConexion()) {
            this.conn = conn; // Guardar la conexión si la necesitas para cerrar después
            System.out.print("BASE DE DATOS CREADA O ACCEDIDA EXITOSAMENTE ...\n\n");
        } catch (Exception e) {
            System.err.println("ERROR AL CREAR O ACCEDER A BASE DATOS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void terminar() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        } else {
            System.out.println("No hay conexiones activas para cerrar.");
        }
        System.out.println("FIN DEL PROGRAMA....");
    }
}
