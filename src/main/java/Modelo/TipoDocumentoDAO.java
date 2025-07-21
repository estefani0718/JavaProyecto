package Modelo;

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
    private Connection connection;

    public TipoDocumentoDAO(Connection connection) {
        this.connection = connection;
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
            e.printStackTrace();
        }

        return lista;
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return false;
    }

    // Actualizar
    public boolean actualizar(TipoDocumento td) {
        String sql = "UPDATE TipoDocumento SET tipo_Documento = ? WHERE codigo_Tdocumento = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, td.getTipo_Documento());
            stmt.setInt(2, td.getCodigo_Tdocumento());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return false;
    }
}