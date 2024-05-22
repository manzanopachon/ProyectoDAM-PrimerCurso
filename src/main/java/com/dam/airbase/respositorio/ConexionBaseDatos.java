package com.dam.airbase.respositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBaseDatos {
	
// ------------------ Conexion a Tu base de datos Descomenta la de los compañeros ------
// ------------------ Revisen en cada COMMIT -------------------------------------------
	protected static final String URL = "jdbc:mariadb://localhost:3306/";
    protected static final String USER = "root";

    // protected static final String BBDD = "baseaerea"; // Nombre BBDD Consta
    // protected static final String PASS = "1945"; // Contraseña Consta

    // protected static final String BBDD = "baseaerea"; // Nombre BBDD Mauri
    // protected static final String PASS = "changeme"; // Contraseña Mauri

    protected static final String BBDD = "pruebabaseaerea"; // Nombre BBDD FaLy
    protected static final String PASS = "changeme"; // Contraseña FaLy

    public Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(URL+BBDD, USER, PASS);
            //System.out.println("Conectado exitosamente a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se ha encontrado la base de datos.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    public void desconectar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                //System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }

    public void crearBaseDeDatosYTablas(Connection conexion) {
        try (Statement stmt = conexion.createStatement()) {
            // Crear base de datos si no existe
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + BBDD);
            stmt.executeUpdate("USE " + BBDD);

            // Crear tablas
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Avion (codigo VARCHAR(10) PRIMARY KEY, nombre VARCHAR(100), horasVuelo INT, operativo BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Piloto (codigo VARCHAR(10) PRIMARY KEY, nombre VARCHAR(100), horasVuelo INT, operativo BOOLEAN, admin BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Incidencias (numeroIncidencia VARCHAR(50) PRIMARY KEY, codigoReferenciaAvion VARCHAR(50),codigoReferenciaPiloto VARCHAR(50), descripcion VARCHAR(255), operativo BOOLEAN, Fecha DATE, FOREIGN KEY (codigoReferenciaAvion) REFERENCES Avion(codigo) ON DELETE CASCADE, FOREIGN KEY (codigoReferenciaPiloto) REFERENCES Piloto(codigo) ON DELETE CASCADE)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Ruta (idRuta INT PRIMARY KEY AUTO_INCREMENT, paisOrigen VARCHAR(100), paisDestino VARCHAR(100), h INT, codigoAvion VARCHAR(50), codigoPiloto VARCHAR(50), FOREIGN KEY (codigoAvion) REFERENCES Avion(codigo), FOREIGN KEY (codigoPiloto) REFERENCES Piloto(codigo))");

            //System.out.println("Base de datos y tablas creadas correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos y las tablas.");
            e.printStackTrace();
        }
    }
}
