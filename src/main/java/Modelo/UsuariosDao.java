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
import javax.swing.JOptionPane;

/**
 *
 * @author eeste
 */
/**
 * DAO para manejar operaciones CRUD de la tabla Usuarios.
 */
public class UsuariosDao {

    // ------------------------ REGISTRAR USUARIO CON STRINGS LEGIBLES ------------------------
    public boolean registrarUsuarioConNombres(UsuariosDto dto) throws SQLException {
        String sql = "INSERT INTO Usuarios (nombre_usuario, documento_usuario, codigo_Tdocumento, genero_usuario, direccion_usuario, telefono_usuario, correo, id_estado, codigo_rol, codigo_residencia, codigo_tipoC, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Convertir strings a IDs
        int idTipoDoc = new TipoDocumentoDAO().obtenerIdPorNombre(dto.getTipo_documento());
        int idEstado = new EstadosDao().obtenerIdPorNombre(dto.getEstado());
        int idRol = new RolesDao().obtenerIdPorNombre(dto.getRol());
        int idResidencia = new ResidenciaDao().obtenerIdPorNombre(dto.getResidencia());
        int idTipoCliente = new TipoClienteDao().obtenerIdPorNombre(dto.getTipo_cliente());

        try (Connection cn = ClaseConexion.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dto.getNombre_usuario());
            ps.setLong(2, dto.getDocumento_usuario());
            ps.setInt(3, idTipoDoc);
            ps.setString(4, dto.getGenero_usuario());
            ps.setString(5, dto.getDireccion_usuario());
            ps.setString(6, dto.getTelefono_usuario());
            ps.setString(7, dto.getCorreo());
            ps.setInt(8, idEstado);
            ps.setInt(9, idRol);
            ps.setInt(10, idResidencia);
            ps.setInt(11, idTipoCliente);
            ps.setString(12, dto.getUsuario());
            ps.setString(13, dto.getContrasena());

            return ps.executeUpdate() > 0;
        }
    }

    // ------------------------ LISTAR USUARIOS CON STRINGS LEGIBLES ------------------------
    public List<UsuariosDto> listarUsuariosConNombres() throws SQLException {
        List<UsuariosDto> lista = new ArrayList<>();
        String sql = "SELECT u.*, td.tipo_documento, e.nombre_estado, r.rol, res.nombre_municipio, tc.tipo_cliente " +
                     "FROM Usuarios u " +
                     "JOIN TipoDocumento td ON u.codigo_Tdocumento = td.codigo_Tdocumento " +
                     "JOIN Estados e ON u.id_estado = e.id_estado " +
                     "JOIN Roles r ON u.codigo_rol = r.codigo_rol " +
                     "JOIN Residencia res ON u.codigo_residencia = res.codigo_residencia " +
                     "JOIN TipoCliente tc ON u.codigo_tipoC = tc.codigo_tipoC";

        try (Connection cn = ClaseConexion.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuariosDto dto = new UsuariosDto();
                 dto.setId(rs.getInt("id")); 
                dto.setNombre_usuario(rs.getString("nombre_usuario"));
                dto.setDocumento_usuario(rs.getLong("documento_usuario"));
                dto.setTipo_documento(rs.getString("tipo_documento"));
                dto.setGenero_usuario(rs.getString("genero_usuario"));
                dto.setDireccion_usuario(rs.getString("direccion_usuario"));
                dto.setTelefono_usuario(rs.getString("telefono_usuario"));
                dto.setCorreo(rs.getString("correo"));
                dto.setEstado(rs.getString("nombre_estado"));
                dto.setRol(rs.getString("rol"));
                dto.setResidencia(rs.getString("nombre_municipio"));
                dto.setTipo_cliente(rs.getString("tipo_cliente"));
                dto.setUsuario(rs.getString("usuario"));
                dto.setContrasena(rs.getString("contrasena"));
                lista.add(dto);
            }
        }

        return lista;
    }

    // ------------------------ BUSCAR USUARIO POR ID ------------------------
    public UsuariosDto obtenerPorId(int id) throws SQLException {
        for (UsuariosDto u : listarUsuariosConNombres()) {
            if (u.getDocumento_usuario() == id) {
                return u;
            }
        }
        return null;
    }

    // ------------------------ BUSCAR USUARIO POR DOCUMENTO ------------------------
    public UsuariosDto buscarPorDocumento(long documento) throws SQLException {
        for (UsuariosDto u : listarUsuariosConNombres()) {
            if (u.getDocumento_usuario() == documento) {
                return u;
            }
        }
        return null;
    }

    // ------------------------ ACTUALIZAR POR ID ------------------------
    public boolean actualizarUsuarioPorId(int id, UsuariosDto dto) throws SQLException {
        String sql = "UPDATE Usuarios SET nombre_usuario=?, documento_usuario=?, codigo_Tdocumento=?, genero_usuario=?, direccion_usuario=?, telefono_usuario=?, correo=?, id_estado=?, codigo_rol=?, codigo_residencia=?, codigo_tipoC=?, usuario=?, contrasena=? WHERE id=?";

        int idTipoDoc = new TipoDocumentoDAO().obtenerIdPorNombre(dto.getTipo_documento());
        int idEstado = new EstadosDao().obtenerIdPorNombre(dto.getEstado());
        int idRol = new RolesDao().obtenerIdPorNombre(dto.getRol());
        int idResidencia = new ResidenciaDao().obtenerIdPorNombre(dto.getResidencia());
        int idTipoCliente = new TipoClienteDao().obtenerIdPorNombre(dto.getTipo_cliente());

        try (Connection cn = ClaseConexion.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dto.getNombre_usuario());
            ps.setLong(2, dto.getDocumento_usuario());
            ps.setInt(3, idTipoDoc);
            ps.setString(4, dto.getGenero_usuario());
            ps.setString(5, dto.getDireccion_usuario());
            ps.setString(6, dto.getTelefono_usuario());
            ps.setString(7, dto.getCorreo());
            ps.setInt(8, idEstado);
            ps.setInt(9, idRol);
            ps.setInt(10, idResidencia);
            ps.setInt(11, idTipoCliente);
            ps.setString(12, dto.getUsuario());
            ps.setString(13, dto.getContrasena());
            ps.setInt(14, id);

       // Ejecuta la sentencia SQL (INSERT, UPDATE o DELETE) y retorna true si al menos una fila fue afectada.
       // Esto indica que la operación en la base de datos se realizó con éxito.
        return ps.executeUpdate() > 0;
        }
    }

    // ------------------------ ACTUALIZAR POR DOCUMENTO ------------------------
    public boolean actualizarUsuarioPorDocumento(long documento, UsuariosDto dto) throws SQLException {
        String sql = "UPDATE Usuarios SET nombre_usuario=?, codigo_Tdocumento=?, genero_usuario=?, direccion_usuario=?, telefono_usuario=?, correo=?, id_estado=?, codigo_rol=?, codigo_residencia=?, codigo_tipoC=?, usuario=?, contrasena=? WHERE documento_usuario=?";

        int idTipoDoc = new TipoDocumentoDAO().obtenerIdPorNombre(dto.getTipo_documento());
        int idEstado = new EstadosDao().obtenerIdPorNombre(dto.getEstado());
        int idRol = new RolesDao().obtenerIdPorNombre(dto.getRol());
        int idResidencia = new ResidenciaDao().obtenerIdPorNombre(dto.getResidencia());
        int idTipoCliente = new TipoClienteDao().obtenerIdPorNombre(dto.getTipo_cliente());

        try (Connection cn = ClaseConexion.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dto.getNombre_usuario());
            ps.setInt(2, idTipoDoc);
            ps.setString(3, dto.getGenero_usuario());
            ps.setString(4, dto.getDireccion_usuario());
            ps.setString(5, dto.getTelefono_usuario());
            ps.setString(6, dto.getCorreo());
            ps.setInt(7, idEstado);
            ps.setInt(8, idRol);
            ps.setInt(9, idResidencia);
            ps.setInt(10, idTipoCliente);
            ps.setString(11, dto.getUsuario());
            ps.setString(12, dto.getContrasena());
            ps.setLong(13, documento);

            return ps.executeUpdate() > 0;
        }
    }

    // ------------------------ ELIMINACIÓN LÓGICA (DESHABILITAR) ------------------------
    public boolean deshabilitarUsuarioPorDocumento(long documento) {
        String sql = "UPDATE Usuarios SET id_estado = 3 WHERE documento_usuario = ?";
        try (Connection cn = ClaseConexion.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, documento);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}