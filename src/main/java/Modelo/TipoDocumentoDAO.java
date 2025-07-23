package Modelo;

import Utils.ClaseConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eeste
 */
public class TipoDocumentoDAO {
       private Connection connection ;

    // Constructor por defecto
    public TipoDocumentoDAO() {
        this.connection = ClaseConexion.obtenerConexion();
    }
    // Listar todos
    public List<TipoDocumento> obtenerTodos() {
        List<TipoDocumento> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoDocumento";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoDocumento td = new TipoDocumento();
                td.setCodigo_Tdocumento(rs.getInt("codigo_Tdocumento"));
                td.setTipo_Documento(rs.getString("tipo_Documento"));
                lista.add(td);
            }

        } catch (SQLException e) {
           System.out.println("Error al listar todo el  tipo de documento: " + e.getMessage());
        }

        return lista;
    }
    public int obtenerIdPorNombre(String nombreTipo) throws SQLException {
        String sql = "SELECT codigo_Tdocumento FROM TipoDocumento WHERE  tipo_Documento= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreTipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("codigo_Tdocumento");
                } else {
                    throw new SQLException("Tipo de documento no encontrado: " + nombreTipo);
                }
            }
        }
    }
    // Obtener por ID
    public TipoDocumento obtenerPorId(int id) {
        TipoDocumento td = null;
        String sql = "SELECT * FROM TipoDocumento WHERE codigo_Tdocumento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                td = new TipoDocumento();
                td.setCodigo_Tdocumento(rs.getInt("codigo_Tdocumento"));
                td.setTipo_Documento(rs.getString("tipo_Documento"));
            }

        } catch (SQLException e) {
           System.out.println("Error al obtener id del tipo de documento: " + e.getMessage());
        }

        return td;
    }

    // Insertar nuevo
    public boolean insertar(TipoDocumento td) {
        String sql = "INSERT INTO TipoDocumento (tipo_Documento) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, td.getTipo_Documento());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar el tipo de documento: " + e.getMessage());
        }

        return false;
    }

    // Actualizar
    public boolean actualizar(TipoDocumento td) {
        String sql = "UPDATE TipoDocumento SET tipo_Documento = ? WHERE codigo_Tdocumento = ?";
            
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // stmt es una instancia de PreparedStatement, utilizada para ejecutar sentencias SQL con parámetros seguros.
           // Nos permite evitar inyecciones SQL al usar marcadores de posición (?) en la consulta y asignar los valores con setX().

            stmt.setString(1, td.getTipo_Documento());
            stmt.setInt(2, td.getCodigo_Tdocumento());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
           System.out.println("Error al actualizar tipo de documento: " + e.getMessage());
        }

        return false;
    }

    // Verificar si hay usuarios relacionados
    public boolean tieneUsuariosRelacionados(int idTipoDocumento) {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE id_tipo_documento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTipoDocumento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
           System.out.println("Error al actualizar tipo de documento: " + e.getMessage());
        }

        return false;
    }

    // Eliminar físicamente solo si no está relacionado
    public boolean eliminar(int id) {
        if (tieneUsuariosRelacionados(id)) {
            System.out.println("No se puede eliminar: tipo de documento está en uso por usuarios.");
            return false;
        }

        String sql = "DELETE FROM TipoDocumento WHERE codigo_Tdocumento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
           System.out.println("Error al eliminar el tipo de documento: " + e.getMessage());
        }

        return false;
    }
}