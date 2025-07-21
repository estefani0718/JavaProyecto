/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Utils.ClaseConexion;
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
 * Por ejemplo, si tienes un servicio en @Path("usuarios"), se podrá acceder desde:
 * http://localhost:8080/tuProyecto/api/usuarios"
 * 
 * Esta clase extiende de javax.ws.rs.core.Application y no necesita
 * métodos adicionales si solo se usará para definir el path base de los servicios.
 * 
 * Ubicada en la carpeta 'controlador' por organización del proyecto.
 */
@ApplicationPath("api")
public class servicios extends Application {
}
