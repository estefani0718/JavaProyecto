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
 * DAO para TipoPaquete.
 * Usa JOINs para mostrar nombres legibles de relaciones (categoría, estado).
 */
public class TipoPaqueteDao {

    /**
     * Lista todos los paquetes con nombres legibles en vez de IDs.
     */
    public List<TipoPaqueteDto> listar() {
        List<TipoPaqueteDto> lista = new ArrayList<>();

        // Consulta con JOINs para obtener los nombres de categoría y estado
        String sql = "SELECT tp.codigo_Tpaquete, tp.nombre_paquete, tp.detalles_adicionales, " +
                     "cp.nombre_categoria AS nombre_categoria, " +
                     "e.nombre_estado AS nombre_estado, " +
                     "tp.origen, tp.metodo_pago, tp.destino " +
                     "FROM TipoPaquete tp " +
                     "LEFT JOIN CategoriaPaquete cp ON tp.codigo_paquete = cp.codigo_paquete " +
                     "LEFT JOIN Estados e ON tp.id_estado = e.id_estado";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoPaqueteDto paquete = new TipoPaqueteDto();
                paquete.setCodigoTpaquete(rs.getInt("codigo_Tpaquete"));
                paquete.setNombrePaquete(rs.getString("nombre_paquete"));
                paquete.setDetallesAdicionales(rs.getString("detalles_adicionales"));
                paquete.setCategoriaPaquete(rs.getString("nombre_categoria")); // se extrae de JOIN
                paquete.setEstado(rs.getString("nombre_estado")); // se extrae de JOIN
                paquete.setOrigen(rs.getString("origen"));
                paquete.setMetodoPago(rs.getString("metodo_pago"));
                paquete.setDestino(rs.getString("destino"));
                lista.add(paquete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Inserta un nuevo paquete, usando nombres legibles (texto).
     */
    public boolean guardar(TipoPaqueteDto paquete) {
        // Primero obtenemos los IDs reales de la categoría y estado a partir de los nombres
        String sql = "INSERT INTO TipoPaquete(nombre_paquete, detalles_adicionales, codigo_paquete, id_estado, origen, metodo_pago, destino) " +
                     "VALUES (?, ?, (SELECT codigo_paquete FROM CategoriaPaquete WHERE nombre_categoria = ?), " +
                     "(SELECT id_estado FROM Estados WHERE nombre_estado = ?), ?, ?, ?)";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, paquete.getNombrePaquete());
            stmt.setString(2, paquete.getDetallesAdicionales());
            stmt.setString(3, paquete.getCategoriaPaquete()); // nombre legible
            stmt.setString(4, paquete.getEstado());           // nombre legible
            stmt.setString(5, paquete.getOrigen());
            stmt.setString(6, paquete.getMetodoPago());
            stmt.setString(7, paquete.getDestino());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un paquete por ID, devolviendo nombres legibles.
     */
    public TipoPaqueteDto buscarPorId(int id) {
        TipoPaqueteDto paquete = null;

        String sql = "SELECT tp.codigo_Tpaquete, tp.nombre_paquete, tp.detalles_adicionales, " +
                     "cp.nombre_categoria AS nombre_categoria, " +
                     "e.nombre_estado AS nombre_estado, " +
                     "tp.origen, tp.metodo_pago, tp.destino " +
                     "FROM TipoPaquete tp " +
                     "LEFT JOIN CategoriaPaquete cp ON tp.codigo_paquete = cp.codigo_paquete " +
                     "LEFT JOIN Estados e ON tp.id_estado = e.id_estado " +
                     "WHERE tp.codigo_Tpaquete = ?";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paquete = new TipoPaqueteDto();
                paquete.setCodigoTpaquete(rs.getInt("codigo_Tpaquete"));
                paquete.setNombrePaquete(rs.getString("nombre_paquete"));
                paquete.setDetallesAdicionales(rs.getString("detalles_adicionales"));
                paquete.setCategoriaPaquete(rs.getString("nombre_categoria"));
                paquete.setEstado(rs.getString("nombre_estado"));
                paquete.setOrigen(rs.getString("origen"));
                paquete.setMetodoPago(rs.getString("metodo_pago"));
                paquete.setDestino(rs.getString("destino"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paquete;
    }

    /**
     * Actualiza un paquete por ID, utilizando nombres de texto.
     */
    public boolean actualizarPorId(TipoPaqueteDto paquete) {
        String sql = "UPDATE TipoPaquete SET nombre_paquete = ?, detalles_adicionales = ?, " +
                     "codigo_paquete = (SELECT codigo_paquete FROM CategoriaPaquete WHERE nombre_categoria = ?), " +
                     "id_estado = (SELECT id_estado FROM Estados WHERE nombre_estado = ?), " +
                     "origen = ?, metodo_pago = ?, destino = ? " +
                     "WHERE codigo_Tpaquete = ?";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, paquete.getNombrePaquete());
            stmt.setString(2, paquete.getDetallesAdicionales());
            stmt.setString(3, paquete.getCategoriaPaquete());
            stmt.setString(4, paquete.getEstado());
            stmt.setString(5, paquete.getOrigen());
            stmt.setString(6, paquete.getMetodoPago());
            stmt.setString(7, paquete.getDestino());
            stmt.setInt(8, paquete.getCodigoTpaquete());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina lógicamente un paquete (cambia el estado a otro valor).
     */
    public boolean eliminarLogico(int id, String nuevoEstado) {
        String sql = "UPDATE TipoPaquete SET id_estado = (SELECT id_estado FROM Estados WHERE nombre_estado = ?) WHERE codigo_Tpaquete = ?";

        try (Connection con = ClaseConexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado); // por ejemplo "Inactivo"
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}