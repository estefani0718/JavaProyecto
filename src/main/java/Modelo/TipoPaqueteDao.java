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
public class TipoPaqueteDao {
    private Connection conexion;

    public TipoPaqueteDao(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todos los paquetes que no estén marcados como eliminados (estado lógico)
    public List<TipoPaquete> obtenerTodos() {
        List<TipoPaquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoPaquete WHERE id_estado != 3"; // Suponiendo que 3 es 'eliminado'

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoPaquete paquete = new TipoPaquete();
                paquete.setCodigoTpaquete(rs.getInt("codigo_Tpaquete"));
                paquete.setNombrePaquete(rs.getString("nombre_paquete"));
                paquete.setDetallesAdicionales(rs.getString("detalles_adicionales"));
                paquete.setCodigoTransporteUsuario(rs.getInt("codigo_TransporteUsuario"));
                paquete.setCodigoCategoria(rs.getInt("codigo_paquete"));
                paquete.setCodigoFactura(rs.getInt("codigo_factura"));
                paquete.setIdEstado(rs.getInt("id_estado"));
                paquete.setOrigen(rs.getString("origen"));
                paquete.setDestino(rs.getString("destino"));
                paquete.setValorPaquete(rs.getBigDecimal("valor_paquete"));
                lista.add(paquete);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener paquetes: " + e.getMessage());
        }

        return lista;
    }

    // Obtener un paquete por su ID
    public TipoPaquete obtenerPorId(int id) {
        TipoPaquete paquete = null;
        String sql = "SELECT * FROM TipoPaquete WHERE codigo_Tpaquete = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paquete = new TipoPaquete();
                paquete.setCodigoTpaquete(rs.getInt("codigo_Tpaquete"));
                paquete.setNombrePaquete(rs.getString("nombre_paquete"));
                paquete.setDetallesAdicionales(rs.getString("detalles_adicionales"));
                paquete.setCodigoTransporteUsuario(rs.getInt("codigo_TransporteUsuario"));
                paquete.setCodigoCategoria(rs.getInt("codigo_paquete"));
                paquete.setCodigoFactura(rs.getInt("codigo_factura"));
                paquete.setIdEstado(rs.getInt("id_estado"));
                paquete.setOrigen(rs.getString("origen"));
                paquete.setDestino(rs.getString("destino"));
                paquete.setValorPaquete(rs.getBigDecimal("valor_paquete"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener paquete por ID: " + e.getMessage());
        }

        return paquete;
    }

    // Insertar un nuevo paquete
    public boolean insertar(TipoPaquete paquete) {
        String sql = "INSERT INTO TipoPaquete (nombre_paquete, detalles_adicionales, codigo_TransporteUsuario, codigo_paquete, codigo_factura, id_estado, origen, destino, valor_paquete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, paquete.getNombrePaquete());
            stmt.setString(2, paquete.getDetallesAdicionales());
            stmt.setInt(3, paquete.getCodigoTransporteUsuario());
            stmt.setInt(4, paquete.getCodigoCategoria());
            stmt.setInt(5, paquete.getCodigoFactura());
            stmt.setInt(6, paquete.getIdEstado());
            stmt.setString(7, paquete.getOrigen());
            stmt.setString(8, paquete.getDestino());
            stmt.setBigDecimal(9, paquete.getValorPaquete());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar paquete: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un paquete
    public boolean actualizar(TipoPaquete paquete) {
        String sql = "UPDATE TipoPaquete SET nombre_paquete = ?, detalles_adicionales = ?, codigo_TransporteUsuario = ?, codigo_paquete = ?, codigo_factura = ?, id_estado = ?, origen = ?, destino = ?, valor_paquete = ? WHERE codigo_Tpaquete = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, paquete.getNombrePaquete());
            stmt.setString(2, paquete.getDetallesAdicionales());
            stmt.setInt(3, paquete.getCodigoTransporteUsuario());
            stmt.setInt(4, paquete.getCodigoCategoria());
            stmt.setInt(5, paquete.getCodigoFactura());
            stmt.setInt(6, paquete.getIdEstado());
            stmt.setString(7, paquete.getOrigen());
            stmt.setString(8, paquete.getDestino());
            stmt.setBigDecimal(9, paquete.getValorPaquete());
            stmt.setInt(10, paquete.getCodigoTpaquete());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar paquete: " + e.getMessage());
            return false;
        }
    }

    // Eliminación lógica del paquete (cambiar estado a 'eliminado')
    public boolean eliminarLogicamente(int id) {
        String sql = "UPDATE TipoPaquete SET id_estado = 3 WHERE codigo_Tpaquete = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar paquete: " + e.getMessage());
            return false;
        }
    }
}