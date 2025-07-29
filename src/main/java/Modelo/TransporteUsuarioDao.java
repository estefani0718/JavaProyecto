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

public class TransporteUsuarioDao {
 private final EstadosDao estadoDao= new EstadosDao();
    /**
     * Lista todos los registros de TransporteUsuario con nombres de estado.
     */
    public List<TransporteUsuarioDto> listar() {
        List<TransporteUsuarioDto> lista = new ArrayList<>();
        String sql = """
            SELECT tu.codigo_TransporteUsuario, tu.placa, tu.documento_usuario,
                   tu.anios_experiencia, e.nombre_estado
            FROM TransporteUsuario tu
            JOIN Estados e ON tu.id_estado = e.id_estado
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TransporteUsuarioDto dto = new TransporteUsuarioDto(
                    rs.getInt("codigo_TransporteUsuario"),
                    rs.getString("placa"),
                    rs.getLong("documento_usuario"),
                    rs.getInt("anios_experiencia"),
                    rs.getString("nombre_estado")
                );
                lista.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Guarda un nuevo TransporteUsuario en la base de datos.
     */
    public boolean guardar(TransporteUsuarioDto dto) {
        String sql = """
            INSERT INTO TransporteUsuario (placa, documento_usuario, anios_experiencia, id_estado)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

         int idEstado = estadoDao.obtenerIdPorNombre(dto.getEstado()) ;

            ps.setString(1, dto.getPlaca());
            ps.setLong(2, dto.getDocumentoUsuario());
            ps.setInt(3, dto.getAniosExperiencia());
            ps.setInt(4, idEstado);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Busca un TransporteUsuario por su ID.
     */
    public TransporteUsuarioDto buscarPorId(int id) {
        String sql = """
            SELECT tu.codigo_TransporteUsuario, tu.placa, tu.documento_usuario,
                   tu.anios_experiencia, e.nombre_estado
            FROM TransporteUsuario tu
            JOIN Estados e ON tu.id_estado = e.id_estado
            WHERE tu.codigo_TransporteUsuario = ?
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TransporteUsuarioDto(
                        rs.getInt("codigo_TransporteUsuario"),
                        rs.getString("placa"),
                        rs.getLong("documento_usuario"),
                        rs.getInt("anios_experiencia"),
                        rs.getString("nombre_estado")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Actualiza un TransporteUsuario por su ID.
     */
    public boolean actualizarPorId(TransporteUsuarioDto dto) {
        String sql = """
            UPDATE TransporteUsuario
            SET placa = ?, documento_usuario = ?, anios_experiencia = ?, id_estado = ?
            WHERE codigo_TransporteUsuario = ?
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

           int idEstado = estadoDao.obtenerIdPorNombre(dto.getEstado());

            ps.setString(1, dto.getPlaca());
            ps.setLong(2, dto.getDocumentoUsuario());
            ps.setInt(3, dto.getAniosExperiencia());
            ps.setInt(4, idEstado);
            ps.setInt(5, dto.getCodigoTransporteUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Elimina lógicamente un registro cambiando su estado.
     */
    public boolean eliminarLogicoPorId(int id, String nuevoEstado,TransporteUsuarioDto dto) {
        String sql = "UPDATE TransporteUsuario SET id_estado = ? WHERE codigo_TransporteUsuario = ?";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

           int idEstado = estadoDao.obtenerIdPorNombre(dto.getEstado());

            ps.setInt(1, idEstado);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}