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

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Usuarios> listarTodos() {
        List<Usuarios> lista = new ArrayList<>();
        try {
            cn = ClaseConexion.obtenerConexion();
            String sql = "SELECT * FROM Usuarios";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuarios u = new Usuarios(
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
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    public boolean guardarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO Usuarios (" +
            "nombre_usuario, documento_usuario, codigo_Tdocumento, genero_usuario, " +
            "direccion_usuario, telefono_usuario, correo, id_estado, " +
            "codigo_rol, codigo_residencia, codigo_tipoC, usuario, contrasena) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ClaseConexion.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre_usuario());
            ps.setLong(2, usuario.getDocumento_usuario());
            ps.setInt(3, usuario.getCodigo_Tdocumento());
            ps.setString(4, usuario.getGenero_usuario());
            ps.setString(5, usuario.getDireccion_usuario());
            ps.setString(6, usuario.getTelefono_usuario());
            ps.setString(7, usuario.getCorreo());
            ps.setInt(8, usuario.getId_estado());
            ps.setInt(9, usuario.getCodigo_rol());
            ps.setInt(10, usuario.getCodigo_residencia());
            ps.setInt(11, usuario.getCodigo_tipoC());
            ps.setString(12, usuario.getUsuario());
            ps.setString(13, usuario.getContrasena());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

   
   
   
   
   
   
  
   
 
  
    
