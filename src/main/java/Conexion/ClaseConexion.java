
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eeste
 */
public class ClaseConexion {
    //informacion del pasword y demas 
    
   
    private static final String user = "Zaray";
    private static final String database = "ProyectoDomiexpro";
    private static final String password = "12345678";
    private static final String url= "jdbc:mysql://localhost:3306/"+database;
    


    public static Connection obtenerConexion() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            System.out.println(" Conexión establecida exitosamente");
        } catch (Exception ex) {
            System.err.println(" Error al establecer conexión:");
            ex.printStackTrace(); 
        }
        return cn;
    } 
}
