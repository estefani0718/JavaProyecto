/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoPaquete;
import Modelo.TipoPaqueteDao;
import Utils.ClaseConexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eeste
 */
public class TipoPaqueteServicios {

    // Método para obtener todos los paquetes activos (no eliminados)
    public List<TipoPaquete> obtenerTodos() {
        try (Connection conn = ClaseConexion.obtenerConexion()) {
            TipoPaqueteDao dao = new TipoPaqueteDao(conn);
            return dao.obtenerTodos();
        } catch (SQLException e) {
            System.out.println("Error en obtenerTodos: " + e.getMessage());
            return null; // Devuelve null si ocurre un error
        }
    }

    // Método para obtener un paquete por su ID
    public TipoPaquete obtenerPorId(int id) {
        try (Connection conn = ClaseConexion.obtenerConexion()) {
            TipoPaqueteDao dao = new TipoPaqueteDao(conn);
            return dao.obtenerPorId(id);
        } catch (SQLException e) {
            System.out.println("Error en obtenerPorId: " + e.getMessage());
            return null; // Devuelve null si ocurre un error
        }
    }

    // Método para insertar un nuevo paquete
    public boolean insertar(TipoPaquete paquete) {
        try (Connection conn = ClaseConexion.obtenerConexion()) {
            TipoPaqueteDao dao = new TipoPaqueteDao(conn);
            return dao.insertar(paquete);
        } catch (SQLException e) {
            System.out.println("Error en insertar: " + e.getMessage());
            return false; // Devuelve false si ocurre un error
        }
    }

    // Método para actualizar un paquete existente
    public boolean actualizar(TipoPaquete paquete) {
        try (Connection conn = ClaseConexion.obtenerConexion()) {
            TipoPaqueteDao dao = new TipoPaqueteDao(conn);
            return dao.actualizar(paquete);
        } catch (SQLException e) {
            System.out.println("Error en actualizar: " + e.getMessage());
            return false; // Devuelve false si ocurre un error
        }
    }

    // Método para eliminar lógicamente un paquete (no se borra físicamente)
    public boolean eliminarLogicamente(int id) {
        try (Connection conn = ClaseConexion.obtenerConexion()) {
            TipoPaqueteDao dao = new TipoPaqueteDao(conn);
            return dao.eliminarLogicamente(id); // Cambia el estado a 3 (eliminado)
        } catch (SQLException e) {
            // Mensaje claro si el paquete tiene relaciones con otras tablas
            System.out.println("Este paquete no se puede eliminar, está relacionado con otras tablas.");
            return false; // Devuelve false si no se puede eliminar
        }
    }
}
