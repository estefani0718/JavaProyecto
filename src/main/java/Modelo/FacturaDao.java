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


/**
 * DAO para gestionar operaciones CRUD de facturas utilizando FacturaDto.
 */
public class FacturaDao {

    // Método para listar todas las facturas con estado distinto a "Inactivo" o "Eliminado"
    public List<FacturaDto> listar() {
        List<FacturaDto> lista = new ArrayList<>();
        String sql = """
            SELECT f.codigo_factura, f.fecha_entrega, u.documento_usuario, u.nombre_usuario,
                   f.observaciones, f.precio_total, e.nombre_estado
            FROM factura f
            JOIN usuarios u ON f.id_usuario = u.id_usuario
            JOIN estados e ON f.id_estado = e.id_estado
            WHERE e.nombre_estado != 'Inactivo';
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FacturaDto dto = new FacturaDto();
                dto.setCodigoFactura(rs.getInt("codigo_factura"));
                dto.setFechaEntrega(rs.getDate("fecha_entrega"));
                dto.setDocumentoUsuario(rs.getLong("documento_usuario"));
                dto.setNombreUsuario(rs.getString("nombre_usuario"));
                dto.setObservaciones(rs.getString("observaciones"));
                dto.setPrecioTotal(rs.getDouble("precio_total"));
                dto.setEstado(rs.getString("nombre_estado"));
                lista.add(dto);
            }

        } catch (Exception e) {
            System.err.println("Error al listar facturas: " + e.getMessage());
        }

        return lista;
    }

    // Método para guardar una nueva factura
    public boolean guardar(FacturaDto dto) {
        String sql = """
            INSERT INTO factura (fecha_entrega, id_usuario, observaciones, precio_total, id_estado)
            VALUES (?, 
                (SELECT id_usuario FROM usuarios WHERE documento_usuario = ?),
                ?, ?, 
                (SELECT id_estado FROM estados WHERE nombre_estado = ?));
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, dto.getFechaEntrega());
            stmt.setLong(2, dto.getDocumentoUsuario());
            stmt.setString(3, dto.getObservaciones());
            stmt.setDouble(4, dto.getPrecioTotal());
            stmt.setString(5, dto.getEstado());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error al guardar factura: " + e.getMessage());
            return false;
        }
    }
    
        /**
     * Obtiene una factura específica por su código (ID).
     *
     * @param codigoFactura Código de la factura a buscar.
     * @return Objeto FacturaDto si se encuentra, de lo contrario null.
     */
    public FacturaDto obtenerPorId(int codigoFactura) {
        String sql = """
            SELECT f.codigo_factura, f.fecha_entrega, f.documento_usuario, 
                   u.nombre_usuario, f.observaciones, f.precio_total, e.nombre_estado
            FROM factura f
            JOIN usuarios u ON f.documento_usuario = u.documento_usuario
            JOIN estados e ON f.id_estado = e.id_estado
            WHERE f.codigo_factura = ?;
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codigoFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FacturaDto factura = new FacturaDto();
                    factura.setCodigoFactura(rs.getInt("codigo_factura"));
                    factura.setFechaEntrega(rs.getDate("fecha_entrega"));
                    factura.setDocumentoUsuario(rs.getLong("documento_usuario"));
                    factura.setNombreUsuario(rs.getString("nombre_usuario"));
                    factura.setObservaciones(rs.getString("observaciones"));
                    factura.setPrecioTotal(rs.getDouble("precio_total"));
                    factura.setEstado(rs.getString("estado"));
                    return factura;
                }
            }

        } catch (Exception e) {
            System.err.println("Error al obtener factura por ID: " + e.getMessage());
        }
        return null;
    }
    /**
 * Obtiene todas las facturas asociadas a un documento de usuario.
    *
    * @param documentoUsuario Documento del usuario.
    * @return Lista de objetos FacturaDto correspondientes al usuario.
    */
   public List<FacturaDto> obtenerPorDocumento(long documentoUsuario) {
       String sql = """
           SELECT f.codigo_factura, f.fecha_entrega, f.documento_usuario, 
                  u.nombre_usuario, f.observaciones, f.precio_total, e.nombre_estado
           FROM factura f
           JOIN usuarios u ON f.documento_usuario = u.documento_usuario
           JOIN estados e ON f.id_estado = e.id_estado
           WHERE f.documento_usuario = ?;
       """;

       List<FacturaDto> facturas = new ArrayList<>();

       try (Connection con = ClaseConexion.obtenerConexion();
            PreparedStatement stmt = con.prepareStatement(sql)) {

           stmt.setLong(1, documentoUsuario);
           try (ResultSet rs = stmt.executeQuery()) {
               while (rs.next()) {
                   FacturaDto factura = new FacturaDto();
                   factura.setCodigoFactura(rs.getInt("codigo_factura"));
                   factura.setFechaEntrega(rs.getDate("fecha_entrega"));
                   factura.setDocumentoUsuario(rs.getLong("documento_usuario"));
                   factura.setNombreUsuario(rs.getString("nombre_usuario"));
                   factura.setObservaciones(rs.getString("observaciones"));
                   factura.setPrecioTotal(rs.getDouble("precio_total"));
                   factura.setEstado(rs.getString("estado"));
                   facturas.add(factura);
               }
           }

       } catch (Exception e) {
           System.err.println("Error al obtener facturas por documento: " + e.getMessage());
       }

       return facturas;
   }


    // Método para actualizar una factura por su ID
    public boolean actualizarPorId(FacturaDto dto) {
        String sql = """
            UPDATE factura SET
                fecha_entrega = ?,
                observaciones = ?,
                precio_total = ?,
                id_estado = (SELECT id_estado FROM estados WHERE nombre_estado = ?)
            WHERE codigo_factura = ?;
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, dto.getFechaEntrega());
            stmt.setString(2, dto.getObservaciones());
            stmt.setDouble(3, dto.getPrecioTotal());
            stmt.setString(4, dto.getEstado());
            stmt.setInt(5, dto.getCodigoFactura());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error al actualizar factura por ID: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar una factura por documento de usuario
    public boolean actualizarPorDocumento(FacturaDto dto) {
        String sql = """
            UPDATE factura f
            JOIN usuarios u ON f.id_usuario = u.id_usuario
            SET f.fecha_entrega = ?,
                f.observaciones = ?,
                f.precio_total = ?,
                f.id_estado = (SELECT id_estado FROM estados WHERE nombre_estado = ?)
            WHERE u.documento_usuario = ?;
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, dto.getFechaEntrega());
            stmt.setString(2, dto.getObservaciones());
            stmt.setDouble(3, dto.getPrecioTotal());
            stmt.setString(4, dto.getEstado());
            stmt.setLong(5, dto.getDocumentoUsuario());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error al actualizar factura por documento: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar lógicamente una factura (cambio de estado)
    public boolean eliminarLogico(int codigoFactura) {
        String sql = """
            UPDATE factura SET id_estado = (
                SELECT id_estado FROM estados WHERE nombre_estado = 'Inactivo'
            )
            WHERE codigo_factura = ?;
        """;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codigoFactura);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error al eliminar lógicamente la factura: " + e.getMessage());
            return false;
        }
    }
}