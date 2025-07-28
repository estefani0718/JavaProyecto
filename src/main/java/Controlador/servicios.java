/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ClaseConexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author eeste
 */
/**
 * Clase principal de configuración para los servicios REST.
 * 
 * La anotación @ApplicationPath("api") indica que todos los endpoints
 * (servicios) estarán disponibles a partir del path base "/api".
 * 
 * Esta clase extiende de javax.ws.rs.core.Application y no necesita
 * métodos adicionales si solo se usará para definir el path base de los servicios.
 */
@ApplicationPath("api")
public class servicios extends Application {
}
