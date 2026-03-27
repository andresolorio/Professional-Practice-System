/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.lis.professionalpracticesystem.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class ValidateConnection {
    public void testConnection() {
        try (Connection con = DatabaseConnection.getConnection()) {
            if (con != null) {
                System.out.println("Conexion establecida con exito por Administrador_General.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
    }
}
