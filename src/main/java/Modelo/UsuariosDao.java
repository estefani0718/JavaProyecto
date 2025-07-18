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
public class UsuariosDao {
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    // Listar todos los usuarios
    public List<Usuarios> listarTodos() {
        List<Usuarios> lista = new ArrayList<>();
        try {
            cn = ClaseConexion.obtenerConexion();
            String sql = "SELECT * FROM Usuarios";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuarios u = new Usuarios(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    rs.getLong("documento_usuario"),
                    rs.getInt("codigo_Tdocumento"),
                    rs.getString("genero_usuario"),
                    rs.getString("direccion_usuario"),
                    rs.getString("telefono_usuario"),
                    rs.getString("correo"),
                    rs.getInt("id_estado"),
                    rs.getInt("codigo_rol"),
                    rs.getInt("codigo_residencia"),
                    rs.getInt("codigo_tipoC"),
                    rs.getString("usuario"),
                    rs.getString("contrasena")
                );
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Obtener un usuario por documento
    public Usuarios obtenerPorDocumento(int documento) {
        Usuarios usuario = null;
        try {
            cn = ClaseConexion.obtenerConexion();
            String sql = "SELECT * FROM Usuarios WHERE documento_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuarios(
                    rs.getInt("id"),
                    rs.getString("nombre_usuario"),
                    rs.getLong("documento_usuario"),
                    rs.getInt("codigo_Tdocumento"),
                    rs.getString("genero_usuario"),
                    rs.getString("direccion_usuario"),
                    rs.getString("telefono_usuario"),
                    rs.getString("correo"),
                    rs.getInt("id_estado"),
                    rs.getInt("codigo_rol"),
                    rs.getInt("codigo_residencia"),
                    rs.getInt("codigo_tipoC"),
                    rs.getString("usuario"),
                    rs.getString("contrasena")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return usuario;
    }

    // Guardar un nuevo usuario
    public boolean guardar(Usuarios u) {
        try {
            cn = ClaseConexion.obtenerConexion();
            String sql = "INSERT INTO Usuarios (nombre_usuario, documento_usuario, codigo_Tdocumento, genero_usuario, direccion_usuario, telefono_usuario, correo, id_estado, codigo_rol, codigo_residencia, codigo_tipoC, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getNombre_usuario());
            ps.setLong(2, u.getDocumento_usuario());
            ps.setInt(3, u.getCodigo_Tdocumento());
            ps.setString(4, u.getGenero_usuario());
            ps.setString(5, u.getDireccion_usuario());
            ps.setString(6, u.getTelefono_usuario());
            ps.setString(7, u.getCorreo());
            ps.setInt(8, u.getId_estado());
            ps.setInt(9, u.getCodigo_rol());
            ps.setInt(10, u.getCodigo_residencia());
            ps.setInt(11, u.getCodigo_tipoC());
            ps.setString(12, u.getUsuario());
            ps.setString(13, u.getContrasena());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    // Actualizar un usuario por documento
    public boolean actualizar(int documento, Usuarios u) {
        try {
            cn = ClaseConexion.obtenerConexion();
            String sql = "UPDATE Usuarios SET nombre_usuario = ?, codigo_Tdocumento = ?, genero_usuario = ?, direccion_usuario = ?, telefono_usuario = ?, correo = ?, id_estado = ?, codigo_rol = ?, codigo_residencia = ?, codigo_tipoC = ?, usuario = ?, contrasena = ? WHERE documento_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getNombre_usuario());
            ps.setInt(2, u.getCodigo_Tdocumento());
            ps.setString(3, u.getGenero_usuario());
            ps.setString(4, u.getDireccion_usuario());
            ps.setString(5, u.getTelefono_usuario());
            ps.setString(6, u.getCorreo());
            ps.setInt(7, u.getId_estado());
            ps.setInt(8, u.getCodigo_rol());
            ps.setInt(9, u.getCodigo_residencia());
            ps.setInt(10, u.getCodigo_tipoC());
            ps.setString(11, u.getUsuario());
            ps.setString(12, u.getContrasena());
            ps.setInt(13, documento);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    // Eliminar un usuario por documento
    public boolean eliminar(int documento) {
        try {
            cn = ClaseConexion.obtenerConexion();
            String sql = "DELETE FROM Usuarios WHERE documento_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, documento);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    // Cerrar recursos
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cn != null) cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

   
   
   
   
   
   
  
   
 
  
    
