/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eeste
 */
/**
 * DAO para manejar las operaciones CRUD de la tabla Estados.
 */
public class EstadosDao {
    private Connection conexion;

    // Constructor que recibe una conexión activa a la base de datos
    public EstadosDao(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todos los registros de la tabla Estados
    public List<Estados> obtenerTodos() {
        List<Estados> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estados";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estados estado = new Estados();
                estado.setIdEstado(rs.getInt("id_estado"));
                estado.setNombreEstado(rs.getString("nombre_estado"));
                estado.setNombreEntidad(rs.getString("nombre_entidad"));
                lista.add(estado);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener estados: " + e.getMessage());
        }

        return lista;
    }

    // Obtener un estado específico por ID
    public Estados obtenerPorId(int id) {
        Estados estado = null;
        String sql = "SELECT * FROM Estados WHERE id_estado = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estado = new Estados();
                estado.setIdEstado(rs.getInt("id_estado"));
                estado.setNombreEstado(rs.getString("nombre_estado"));
                estado.setNombreEntidad(rs.getString("nombre_entidad"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener estado por ID: " + e.getMessage());
        }

        return estado;
    }

    // Insertar un nuevo estado
    public boolean insertar(Estados estado) {
        String sql = "INSERT INTO Estados (nombre_estado, nombre_entidad) VALUES (?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado.getNombreEstado());
            stmt.setString(2, estado.getNombreEntidad());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar estado: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un estado existente
    public boolean actualizar(Estados estado) {
        String sql = "UPDATE Estados SET nombre_estado = ?, nombre_entidad = ? WHERE id_estado = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado.getNombreEstado());
            stmt.setString(2, estado.getNombreEntidad());
            stmt.setInt(3, estado.getIdEstado());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un estado por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Estados WHERE id_estado = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar estado: " + e.getMessage());
            return false;
        }
    }
}
