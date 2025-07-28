/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */
import Modelo.Roles;
import Modelo.RolesDao;
import Conexion.ClaseConexion;
import java.sql.Connection;
import java.util.List;

public class RolesServicios {
    private RolesDao dao;

    public RolesServicios() {
        Connection conexion = ClaseConexion.obtenerConexion();
        dao = new RolesDao();
    }

    public List<Roles> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Roles obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }

    public boolean insertar(Roles rol) {
        return dao.insertar(rol);
    }

    public boolean actualizar(Roles rol) {
        return dao.actualizar(rol);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
