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

    // Método para guardar una nueva factura en la base de datos
    public boolean guardar(FacturaDto dto) {
        String sql = "INSERT INTO Factura (fecha_entrega, documento_usuario, observaciones, valor_paquete, precio_total, id_estado) VALUES (?, ?, ?, ?, ?, (SELECT id_estado FROM Estados WHERE nombre_estado = ?))";
        try (Connection con = ClaseConexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getFechaEntrega());
            ps.setLong(2, dto.getDocumentoUsuario());
            ps.setString(3, dto.getObservaciones());
            ps.setDouble(4, dto.getValorPaquete());
            ps.setDouble(5, dto.getPrecioTotal());
            ps.setString(6, dto.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar factura: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todas las facturas incluyendo el nombre del usuario y estado legible
    public List<FacturaDto> listar() {
        List<FacturaDto> lista = new ArrayList<>();
        String sql = "SELECT f.codigo_factura, f.fecha_entrega, f.documento_usuario, u.nombre_usuario, f.observaciones, f.valor_paquete, f.precio_total, e.nombre_estado FROM Factura f JOIN Usuarios u ON f.documento_usuario = u.documento_usuario JOIN Estados e ON f.id_estado = e.id_estado";
        try (Connection con = ClaseConexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FacturaDto dto = new FacturaDto();
                dto.setCodigoFactura(rs.getInt("codigo_factura"));
                dto.setFechaEntrega(rs.getString("fecha_entrega"));
                dto.setDocumentoUsuario(rs.getLong("documento_usuario"));
                dto.setNombreUsuario(rs.getString("nombre_usuario"));
                dto.setObservaciones(rs.getString("observaciones"));
                dto.setValorPaquete(rs.getDouble("valor_paquete"));
                dto.setPrecioTotal(rs.getDouble("precio_total"));
                dto.setEstado(rs.getString("nombre_estado"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e.getMessage());
        }
        return lista;
    }

    // Método para obtener una factura por su ID (código_factura)
    public FacturaDto obtenerPorId(int codigo) {
        String sql = "SELECT f.codigo_factura, f.fecha_entrega, f.documento_usuario, u.nombre_usuario, f.observaciones, f.valor_paquete, f.precio_total, e.nombre_estado FROM Factura f JOIN Usuarios u ON f.documento_usuario = u.documento_usuario JOIN Estados e ON f.id_estado = e.id_estado WHERE f.codigo_factura = ?";
        try (Connection con = ClaseConexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FacturaDto dto = new FacturaDto();
                    dto.setCodigoFactura(rs.getInt("codigo_factura"));
                    dto.setFechaEntrega(rs.getString("fecha_entrega"));
                    dto.setDocumentoUsuario(rs.getLong("documento_usuario"));
                    dto.setNombreUsuario(rs.getString("nombre_usuario"));
                    dto.setObservaciones(rs.getString("observaciones"));
                    dto.setValorPaquete(rs.getDouble("valor_paquete"));
                    dto.setPrecioTotal(rs.getDouble("precio_total"));
                    dto.setEstado(rs.getString("nombre_estado"));
                    return dto;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener factura por ID: " + e.getMessage());
        }
        return null;
    }

    /**
   * Actualiza una factura existente en la base de datos usando el documento del usuario.
   * Nota: si hay más de una factura con ese documento, se actualizarán todas.
   * 
   * @param dto Objeto FacturaDto con los nuevos datos de la factura.
   * @return true si al menos una factura fue actualizada, false en caso contrario.
   */
  public boolean actualizarPorDocumento(FacturaDto dto) {
      String sql = "UPDATE Factura SET fecha_entrega = ?, observaciones = ?, valor_paquete = ?, precio_total = ?, id_estado = (SELECT id_estado FROM Estados WHERE nombre_estado = ?) WHERE documento_usuario = ?";
      try (Connection con = ClaseConexion.obtenerConexion(); 
           PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setString(1, dto.getFechaEntrega());          // Nueva fecha de entrega
          ps.setString(2, dto.getObservaciones());         // Nuevas observaciones
          ps.setDouble(3, dto.getValorPaquete());          // Nuevo valor del paquete
          ps.setDouble(4, dto.getPrecioTotal());           // Nuevo precio total
          ps.setString(5, dto.getEstado());                // Estado como texto (ej: "Activo")
          ps.setLong(6, dto.getDocumentoUsuario());        // Documento del usuario

          return ps.executeUpdate() > 0;

      } catch (SQLException e) {
          System.out.println("Error al actualizar factura por documento: " + e.getMessage());
          return false;
      }
  }
    /**
   * Actualiza una factura existente en la base de datos usando su ID (codigo_factura).
   * 
   * @param dto Objeto FacturaDto con los nuevos datos de la factura.
   * @return true si la actualización fue exitosa, false en caso contrario.
   */
  public boolean actualizarPorId(FacturaDto dto) {
      String sql = "UPDATE Factura SET fecha_entrega = ?, documento_usuario = ?, observaciones = ?, valor_paquete = ?, precio_total = ?, id_estado = (SELECT id_estado FROM Estados WHERE nombre_estado = ?) WHERE codigo_factura = ?";
      try (Connection con = ClaseConexion.obtenerConexion(); 
           PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setString(1, dto.getFechaEntrega());          // Nueva fecha de entrega
          ps.setLong(2, dto.getDocumentoUsuario());        // Nuevo documento del usuario
          ps.setString(3, dto.getObservaciones());         // Nuevas observaciones
          ps.setDouble(4, dto.getValorPaquete());          // Nuevo valor del paquete
          ps.setDouble(5, dto.getPrecioTotal());           // Nuevo precio total
          ps.setString(6, dto.getEstado());                // Estado como texto (ej: "Activo")
          ps.setInt(7, dto.getCodigoFactura());            // ID de la factura a actualizar

          return ps.executeUpdate() > 0;

      } catch (SQLException e) {
          System.out.println("Error al actualizar factura por ID: " + e.getMessage());
          return false;
      }
  }


    // Método para eliminar una factura lógicamente (cambia su estado a "Inactivo")
    public boolean eliminar(int codigoFactura) {
        String sql = "UPDATE Factura SET id_estado = (SELECT id_estado FROM Estados WHERE nombre_estado = 'Inactivo') WHERE codigo_factura = ?";
        try (Connection con = ClaseConexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoFactura);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener facturas por documento de usuario
    public List<FacturaDto> obtenerPorDocumento(long documento) {
        List<FacturaDto> lista = new ArrayList<>();
        String sql = "SELECT f.codigo_factura, f.fecha_entrega, f.documento_usuario, u.nombre_usuario, f.observaciones, f.valor_paquete, f.precio_total, e.nombre_estado FROM Factura f JOIN Usuarios u ON f.documento_usuario = u.documento_usuario JOIN Estados e ON f.id_estado = e.id_estado WHERE f.documento_usuario = ?";
        try (Connection con = ClaseConexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FacturaDto dto = new FacturaDto();
                    dto.setCodigoFactura(rs.getInt("codigo_factura"));
                    dto.setFechaEntrega(rs.getString("fecha_entrega"));
                    dto.setDocumentoUsuario(rs.getLong("documento_usuario"));
                    dto.setNombreUsuario(rs.getString("nombre_usuario"));
                    dto.setObservaciones(rs.getString("observaciones"));
                    dto.setValorPaquete(rs.getDouble("valor_paquete"));
                    dto.setPrecioTotal(rs.getDouble("precio_total"));
                    dto.setEstado(rs.getString("nombre_estado"));
                    lista.add(dto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener facturas por documento: " + e.getMessage());
        }
        return lista;
    }
}
