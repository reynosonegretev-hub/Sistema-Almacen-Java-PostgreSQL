/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandromontesmoreno
 */
public class ConexionBD {

    private static Connection conexion = null;

    /**
     * Establece la conexión con la BD
     *
     * @return el estado de la conexión, true=conexión exitosa, false=error en
     * conexión
     */
    public static boolean establecerConexion() {
        boolean estado = true;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            estado = false;
            System.out.println("No se encontró el driver:" + ex);
        }
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdaplicacion", "edmp", "edmp");
        } catch (SQLException ex) {
            estado = false;
            System.out.println("No se pudo establecer la conexión con la BD:" + ex);
        }
        return estado;
    }

    /**
     * Cierra la conexión con la BD
     */
    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConexion() {
        return conexion;
    }

}