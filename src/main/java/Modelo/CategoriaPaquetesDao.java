/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Paquete donde se encuentra esta clase
package Modelo;

// Importación de clases necesarias para trabajar con base de datos y listas
import Conexion.ClaseConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para manejar operaciones CRUD sobre la tabla CategoriaPaquete.
 * Encapsula el acceso a la base de datos para esta entidad.
 */
public class CategoriaPaquetesDao {

    // Objeto conexión para interactuar con la base de datos
    private Connection conexion;

    // Constructor que recibe la conexión como parámetro
    public CategoriaPaquetesDao() {
        this.conexion = ClaseConexion.obtenerConexion();
    }

    // Método para obtener todas las filas de la tabla CategoriaPaquete
    public List<CategoriasPaquetes> obtenerTodas() {
        List<CategoriasPaquetes> lista = new ArrayList<>(); // Lista donde se almacenan los resultados
        String sql = "SELECT * FROM CategoriaPaquete"; // Consulta SQL para seleccionar todo

        // try-with-resources asegura que los recursos se cierren automáticamente
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Iteramos sobre cada fila devuelta por la consulta
            while (rs.next()) {
                CategoriasPaquetes cp = new CategoriasPaquetes(); // Se crea un nuevo objeto
                cp.setCodigoPaquete(rs.getInt("codigo_paquete")); // Se asigna el código
                cp.setNombreCategoria(rs.getString("nombre_categoria")); // Se asigna el nombre
                lista.add(cp); // Se añade a la lista
            }

        } catch (SQLException e) {
            // Mensaje de error si falla la consulta
            System.out.println("Error al obtener categorías de paquete: " + e.getMessage());
        }

        return lista; // Se devuelve la lista
    }

    // Método para obtener una categoría específica por su ID
    public CategoriasPaquetes obtenerPorId(int id) {
        CategoriasPaquetes cp = null;
        String sql = "SELECT * FROM CategoriaPaquete WHERE codigo_paquete = ?"; // Consulta con parámetro

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id); // Se establece el ID en la consulta
            // Ejecuta la consulta SQL (SELECT) previamente preparada y devuelve los resultados en un objeto ResultSet.
           // Este objeto permite recorrer los datos obtenidos fila por fila desde la base de datos.
            ResultSet rs = stmt.executeQuery(); 

            // Si hay un resultado, se crea el objeto con los datos obtenidos
            if (rs.next()) {
                cp = new CategoriasPaquetes();
                cp.setCodigoPaquete(rs.getInt("codigo_paquete"));
                cp.setNombreCategoria(rs.getString("nombre_categoria"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categoría por ID: " + e.getMessage());
        }

        return cp; // Se devuelve el resultado (puede ser null si no se encontró)
    }

    // Método para insertar una nueva categoría en la base de datos
    public boolean insertar(CategoriasPaquetes cp) {
        String sql = "INSERT INTO CategoriaPaquete (nombre_categoria) VALUES (?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cp.getNombreCategoria()); // Se establece el valor a insertar
            return stmt.executeUpdate() > 0; // Retorna true si al menos una fila fue insertada

        } catch (SQLException e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar una categoría existente en la base de datos
    public boolean actualizar(CategoriasPaquetes cp) {
        String sql = "UPDATE CategoriaPaquete SET nombre_categoria = ? WHERE codigo_paquete = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cp.getNombreCategoria()); // Nuevo nombre
            stmt.setInt(2, cp.getCodigoPaquete()); // ID a actualizar
            return stmt.executeUpdate() > 0; // decuelve true si se actualizó

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar una categoría por su ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM CategoriaPaquete WHERE codigo_paquete = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id); // Se pasa el ID a eliminar
            return stmt.executeUpdate() > 0; // devuelve true si al menos una fila fue eliminada

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
}
