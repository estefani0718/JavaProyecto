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
 * DAO (Data Access Object) para la entidad TipoPaquete.
 * Gestiona las operaciones CRUD con la base de datos.
 */
public class TipoPaqueteDao {

    /**
     * Lista todos los paquetes registrados en la base de datos.
     * @return Lista de TipoPaqueteDto
     */
    public List<TipoPaqueteDto> listar() {
        List<TipoPaqueteDto> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoPaquete";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoPaqueteDto paquete = new TipoPaqueteDto(
                    rs.getInt("codigo_Tpaquete"),
                    rs.getString("nombre_paquete"),
                    rs.getString("detalles_adicionales"),
                    rs.getInt("codigo_paquete"),
                    rs.getInt("codigo_factura"),
                    rs.getInt("id_estado"),
                    rs.getString("origen"),
                    rs.getString("metodo_pago"),
                    rs.getString("destino")
                );
                lista.add(paquete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Guarda un nuevo paquete en la base de datos.
     * @param paquete Objeto TipoPaqueteDto con los datos a registrar
     * @return true si el registro fue exitoso
     */
    public boolean guardar(TipoPaqueteDto paquete) {
        String sql = "INSERT INTO TipoPaquete(nombre_paquete, detalles_adicionales, codigo_paquete, codigo_factura, id_estado, origen, metodo_pago, destino, valor_paquete) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0.0)";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, paquete.getNombrePaquete());
            stmt.setString(2, paquete.getDetallesAdicionales());
            stmt.setInt(3, paquete.getCodigoPaquete());
            stmt.setInt(4, paquete.getCodigoFactura());
            stmt.setInt(5, paquete.getIdEstado());
            stmt.setString(6, paquete.getOrigen());
            stmt.setString(7, paquete.getMetodoPago());
            stmt.setString(8, paquete.getDestino());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un paquete por su ID.
     * @param id ID del paquete
     * @return TipoPaqueteDto si se encuentra, null si no
     */
    public TipoPaqueteDto buscarPorId(int id) {
        String sql = "SELECT * FROM TipoPaquete WHERE codigo_Tpaquete = ?";
        TipoPaqueteDto paquete = null;

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paquete = new TipoPaqueteDto(
                    rs.getInt("codigo_Tpaquete"),
                    rs.getString("nombre_paquete"),
                    rs.getString("detalles_adicionales"),
                    rs.getInt("codigo_paquete"),
                    rs.getInt("codigo_factura"),
                    rs.getInt("id_estado"),
                    rs.getString("origen"),
                    rs.getString("metodo_pago"),
                    rs.getString("destino")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paquete;
    }

    /**
     * Actualiza los datos de un paquete por su ID.
     * @param paquete Objeto TipoPaqueteDto con los nuevos datos
     * @return true si se actualizó correctamente
     */
    public boolean actualizarPorId(TipoPaqueteDto paquete) {
        String sql = "UPDATE TipoPaquete SET nombre_paquete=?, detalles_adicionales=?, codigo_paquete=?, " +
                     "codigo_factura=?, id_estado=?, origen=?, metodo_pago=?, destino=? WHERE codigo_Tpaquete=?";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, paquete.getNombrePaquete());
            stmt.setString(2, paquete.getDetallesAdicionales());
            stmt.setInt(3, paquete.getCodigoPaquete());
            stmt.setInt(4, paquete.getCodigoFactura());
            stmt.setInt(5, paquete.getIdEstado());
            stmt.setString(6, paquete.getOrigen());
            stmt.setString(7, paquete.getMetodoPago());
            stmt.setString(8, paquete.getDestino());
            stmt.setInt(9, paquete.getCodigoTpaquete());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina lógicamente un paquete (cambia su estado).
     * @param id ID del paquete
     * @param nuevoEstado ID del nuevo estado (por ejemplo, 0 para inactivo)
     * @return true si se cambió el estado correctamente
     */
    public boolean eliminarLogico(int id, int nuevoEstado) {
        String sql = "UPDATE TipoPaquete SET id_estado = ? WHERE codigo_Tpaquete = ?";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, nuevoEstado);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}