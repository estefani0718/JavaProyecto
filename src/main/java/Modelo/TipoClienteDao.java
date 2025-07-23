/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utils.ClaseConexion;
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
 * Clase DAO para manejar operaciones CRUD sobre la tabla TipoCliente.
 */
public class TipoClienteDao {
    private Connection conexion;

    // Constructor que recibe una conexión a la base de datos
    public TipoClienteDao() {
        this.conexion = ClaseConexion.obtenerConexion();
    }
    public int obtenerIdPorNombre(String nombreTipoCliente) throws SQLException {
        String sql = "SELECT codigo_tipoC FROM TipoCliente WHERE tipo_cliente  = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombreTipoCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("codigo_tipoC");
                } else {
                    throw new SQLException("Tipo de cliente no encontrado: " + nombreTipoCliente);
                }
            }
        }
    }
    // Método para obtener todos los registros de TipoCliente
    public List<TipoCliente> obtenerTodos() {
        List<TipoCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoCliente";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoCliente tipo = new TipoCliente();
                tipo.setCodigoTipoC(rs.getInt("codigo_tipoC")); // Mapea la clave primaria
                tipo.setTipoCliente(rs.getString("tipo_cliente")); // Mapea el tipo de cliente
                lista.add(tipo);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener tipos de cliente: " + e.getMessage());
        }

        return lista;
    }

    // Método para obtener un tipo de cliente por su ID
    public TipoCliente obtenerPorId(int id) {
        TipoCliente tipo = null;
        String sql = "SELECT * FROM TipoCliente WHERE codigo_tipoC = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = new TipoCliente();
                tipo.setCodigoTipoC(rs.getInt("codigo_tipoC"));
                tipo.setTipoCliente(rs.getString("tipo_cliente"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener tipo de cliente por ID: " + e.getMessage());
        }

        return tipo;
    }

    // Método para insertar un nuevo tipo de cliente
    public boolean insertar(TipoCliente tipo) {
        String sql = "INSERT INTO TipoCliente (tipo_cliente) VALUES (?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tipo.getTipoCliente());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar tipo de cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar un tipo de cliente existente
    public boolean actualizar(TipoCliente tipo) {
        String sql = "UPDATE TipoCliente SET tipo_cliente = ? WHERE codigo_tipoC = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tipo.getTipoCliente());
            stmt.setInt(2, tipo.getCodigoTipoC());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo de cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un tipo de cliente por su ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM TipoCliente WHERE codigo_tipoC = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo de cliente: " + e.getMessage());
            return false;
        }
    }
}
