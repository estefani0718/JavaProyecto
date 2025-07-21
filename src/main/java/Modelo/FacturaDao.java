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
 * DAO para manejar operaciones CRUD sobre la tabla Factura.
 */
public class FacturaDao {
    private Connection conexion;

    public FacturaDao(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todas las facturas
    public List<Factura> obtenerTodas() {
       // Se declara e inicializa una lista vacía de objetos Factura usando ArrayList.
       // Esta lista se utilizará para almacenar todas las facturas que se obtendrán desde la base de datos.
        List<Factura> lista = new ArrayList<>();

        String sql = "SELECT * FROM Factura";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Factura f = new Factura();
                f.setCodigoFactura(rs.getInt("codigo_factura"));
                f.setFechaEntrega(rs.getDate("fecha_entrega"));
                f.setDocumentoUsuario(rs.getLong("documento_usuario"));
                f.setObservaciones(rs.getString("observaciones"));
                f.setPrecioTotal(rs.getDouble("precio_total"));
                lista.add(f);
            }
          // Captura la excepción SQLException, que se lanza si ocurre un error relacionado con la base de datos,
          // como problemas de conexión, errores en la consulta SQL o violaciones de restricciones.
        } catch (SQLException e) {
            System.out.println("Error al obtener facturas: " + e.getMessage());
        }

        return lista;
    }

    // Obtener una factura por su ID
    public Factura obtenerPorId(int id) {
        Factura f = null;
        String sql = "SELECT * FROM Factura WHERE codigo_factura = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                f = new Factura();
                f.setCodigoFactura(rs.getInt("codigo_factura"));
                f.setFechaEntrega(rs.getDate("fecha_entrega"));
                f.setDocumentoUsuario(rs.getLong("documento_usuario"));
                f.setObservaciones(rs.getString("observaciones"));
                f.setPrecioTotal(rs.getDouble("precio_total"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener factura por ID: " + e.getMessage());
        }

        return f;
    }

    // Insertar una nueva factura
    public boolean insertar(Factura f) {
        String sql = "INSERT INTO Factura (fecha_entrega, documento_usuario, observaciones, precio_total) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, f.getFechaEntrega());
            stmt.setLong(2, f.getDocumentoUsuario());
            stmt.setString(3, f.getObservaciones());
            stmt.setDouble(4, f.getPrecioTotal());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar factura: " + e.getMessage());
            return false;
        }
    }

    // Actualizar una factura existente
    public boolean actualizar(Factura f) {
        String sql = "UPDATE Factura SET fecha_entrega = ?, documento_usuario = ?, observaciones = ?, precio_total = ? WHERE codigo_factura = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, f.getFechaEntrega());
            stmt.setLong(2, f.getDocumentoUsuario());
            stmt.setString(3, f.getObservaciones());
            stmt.setDouble(4, f.getPrecioTotal());
            stmt.setInt(5, f.getCodigoFactura());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar factura: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una factura por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Factura WHERE codigo_factura = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
            return false;
        }
    }
}