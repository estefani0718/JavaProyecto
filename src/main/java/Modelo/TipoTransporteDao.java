/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexion.ClaseConexion;
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
public class TipoTransporteDao {

    private Connection conexion;

    public TipoTransporteDao() {
        conexion = ClaseConexion.obtenerConexion();
    }

    public boolean insertar(TipoTransporte transporte) {
        String sql = "INSERT INTO TipoTransporte (placa, nombre_transporte, modelo_vehiculo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, transporte.getPlaca());
            stmt.setString(2, transporte.getNombre_transporte());
            stmt.setString(3, transporte.getModelo_vehiculo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar tipo transporte: " + e.getMessage());
            return false;
        }
    }

    public List<TipoTransporte> obtenerTodos() {
        List<TipoTransporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoTransporte";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tipo transporte: " + e.getMessage());
        }

        return lista;
    }

    public TipoTransporte obtenerPorPlaca(String placa) {
        String sql = "SELECT * FROM TipoTransporte WHERE placa = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener transporte por placa: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizar(TipoTransporte transporte) {
        String sql = "UPDATE TipoTransporte SET nombre_transporte = ?, modelo_vehiculo = ? WHERE placa = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, transporte.getNombre_transporte());
            stmt.setString(2, transporte.getModelo_vehiculo());
            stmt.setString(3, transporte.getPlaca());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo transporte: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String placa) {
        String sql = "DELETE FROM TipoTransporte WHERE placa = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo transporte: " + e.getMessage());
            return false;
        }
    }

    // Mapeo del ResultSet a objeto
    private TipoTransporte mapear(ResultSet rs) throws SQLException {
        TipoTransporte transporte = new TipoTransporte();
        transporte.setPlaca(rs.getString("placa"));
        transporte.setNombre_transporte(rs.getString("nombre_transporte"));
        transporte.setModelo_vehiculo(rs.getString("modelo_vehiculo"));
        return transporte;
    }
}