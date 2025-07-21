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
public class ResidenciaDao {
    private Connection conexion;

    public ResidenciaDao(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Residencia> obtenerTodas() {
        List<Residencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Residencia";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Residencia r = new Residencia();
                r.setCodigoResidencia(rs.getInt("codigo_residencia"));
                r.setNombreMunicipio(rs.getString("nombre_municipio"));
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener residencias: " + e.getMessage());
        }

        return lista;
    }

    public Residencia obtenerPorId(int id) {
        String sql = "SELECT * FROM Residencia WHERE codigo_residencia = ?";
        Residencia r = null;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                r = new Residencia();
                r.setCodigoResidencia(rs.getInt("codigo_residencia"));
                r.setNombreMunicipio(rs.getString("nombre_municipio"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener residencia por ID: " + e.getMessage());
        }

        return r;
    }

    public boolean insertar(Residencia r) {
        String sql = "INSERT INTO Residencia (nombre_municipio) VALUES (?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, r.getNombreMunicipio());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar residencia: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Residencia r) {
        String sql = "UPDATE Residencia SET nombre_municipio = ? WHERE codigo_residencia = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, r.getNombreMunicipio());
            stmt.setInt(2, r.getCodigoResidencia());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar residencia: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Residencia WHERE codigo_residencia = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar residencia: " + e.getMessage());
            return false;
        }
    }
}