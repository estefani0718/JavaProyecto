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
 * DAO para gestionar la tabla Roles (CRUD básico).
 */
public class RolesDao {
    private Connection conexion;

    // Constructor que recibe la conexión activa a la base de datos
    public RolesDao() {
        this.conexion = ClaseConexion.obtenerConexion();
    }
    public int obtenerIdPorNombre(String nombreRol) {
    int id = -1;
    String sql = "SELECT codigo_rol FROM Roles  WHERE rol = ?";
    try (Connection con = ClaseConexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nombreRol);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("codigo_rol");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener ID del Rol: " + e.getMessage());
    }
    return id;
    }

    // Obtener todos los roles existentes
    public List<Roles> obtenerTodos() {
        List<Roles> lista = new ArrayList<>();
        String sql = "SELECT * FROM Roles";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Roles rol = new Roles();
                rol.setCodigoRol(rs.getInt("codigo_rol"));
                rol.setRol(rs.getString("rol"));
                lista.add(rol);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener roles: " + e.getMessage());
        }

        return lista;
    }
    
    // Obtener un rol específico por su ID
    public Roles obtenerPorId(int id) {
        Roles rol = null;
        String sql = "SELECT * FROM Roles WHERE codigo_rol = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rol = new Roles();
                rol.setCodigoRol(rs.getInt("codigo_rol"));
                rol.setRol(rs.getString("rol"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener rol por ID: " + e.getMessage());
        }

        return rol;
    }

    // Insertar un nuevo rol
    public boolean insertar(Roles rol) {
        String sql = "INSERT INTO Roles (rol) VALUES (?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, rol.getRol());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar rol: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un rol existente
    public boolean actualizar(Roles rol) {
        String sql = "UPDATE Roles SET rol = ? WHERE codigo_rol = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, rol.getRol());
            stmt.setInt(2, rol.getCodigoRol());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un rol por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Roles WHERE codigo_rol = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar rol: " + e.getMessage());
            return false;
        }
    }
}
