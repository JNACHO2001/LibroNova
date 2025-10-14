
package com.mycompany.biblioteca.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class Conexion {
    
    private static  HikariDataSource pool;
    
     static {
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("db.properties")) {
            
            if (input == null) {
                throw new IOException(" No se encontró el archivo db.properties");
            }

            // 🔹 Cargamos el archivo db.properties
            Properties prop = new Properties();
            prop.load(input);

            // 🔹 Configuración del pool de conexiones
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("db.url"));
            config.setUsername(prop.getProperty("db.user"));
            config.setPassword(prop.getProperty("db.password"));
            
            // Opcionales: puedes ajustar según tu carga
            config.setMaximumPoolSize(10);         // Máx. conexiones simultáneas
           
            // 🔹 Inicializamos el pool
            pool = new HikariDataSource(config);
            System.out.println("Pool de conexiones inicializado correctamente");

        } catch (IOException e) {
            System.err.println(" Error cargando configuración: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" Error inicializando el pool: " + e.getMessage());
        }
    }

    // 🔹 Método público para obtener una conexión
    public static Connection getConnection() throws SQLException {
        if (pool == null) {
            throw new SQLException("El pool de conexiones no está inicializado");
        }
        return pool.getConnection();
    }
    
}
