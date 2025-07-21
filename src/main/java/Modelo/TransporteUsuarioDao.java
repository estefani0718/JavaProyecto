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
 * DAO para realizar operaciones CRUD sobre la tabla TransporteUsuario.
 */
public class TransporteUsuarioDao {
    private Connection conexion;

    public TransporteUsuarioDao(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todos los registros de TransporteUsuario
    public List<TransporteUsuario> obtenerTodos() {
        List<TransporteUsuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM TransporteUsuario";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TransporteUsuario tu = new TransporteUsuario();
                tu.setCodigoTransporteUsuario(rs.getInt("codigo_TransporteUsuario"));
                tu.setPlaca(rs.getString("placa"));
                tu.setDocumentoUsuario(rs.getLong("documento_usuario"));
                tu.setAniosExperiencia(rs.getInt("anios_experiencia"));
                lista.add(tu);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener transporte de usuario: " + e.getMessage());
        }

        return lista;
    }

    // Obtener un registro específico por ID
    public TransporteUsuario obtenerPorId(int id) {
        TransporteUsuario tu = null;
        String sql = "SELECT * FROM TransporteUsuario WHERE codigo_TransporteUsuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tu = new TransporteUsuario();
                tu.setCodigoTransporteUsuario(rs.getInt("codigo_TransporteUsuario"));
                tu.setPlaca(rs.getString("placa"));
                tu.setDocumentoUsuario(rs.getLong("documento_usuario"));
                tu.setAniosExperiencia(rs.getInt("anios_experiencia"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener transporte de usuario por ID: " + e.getMessage());
        }

        return tu;
    }

    // Insertar un nuevo registro
    public boolean insertar(TransporteUsuario tu) {
        String sql = "INSERT INTO TransporteUsuario (placa, documento_usuario, anios_experiencia) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tu.getPlaca());
            stmt.setLong(2, tu.getDocumentoUsuario());
            stmt.setInt(3, tu.getAniosExperiencia());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar transporte de usuario: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un registro existente
    public boolean actualizar(TransporteUsuario tu) {
        String sql = "UPDATE TransporteUsuario SET placa = ?, documento_usuario = ?, anios_experiencia = ? WHERE codigo_TransporteUsuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tu.getPlaca());
            stmt.setLong(2, tu.getDocumentoUsuario());
            stmt.setInt(3, tu.getAniosExperiencia());
            stmt.setInt(4, tu.getCodigoTransporteUsuario());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar transporte de usuario: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un registro por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM TransporteUsuario WHERE codigo_TransporteUsuario = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar transporte de usuario: " + e.getMessage());
            return false;
        }
    }
}
