/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Estados;
import Modelo.EstadosDao;
import Utils.ClaseConexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eeste
 */
public class EstadosServicios {
    private EstadosDao dao;

    public EstadosServicios() {
        Connection conexion = ClaseConexion.obtenerConexion();
        dao = new EstadosDao();
    }
    public int obtenerIdPorNombre(String nombre) throws SQLException {
        return dao.obtenerIdPorNombre(nombre);
    }
    public List<Estados> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Estados obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }

    public boolean insertar(Estados estado) {
        return dao.insertar(estado);
    }

    public boolean actualizar(Estados estado) {
        return dao.actualizar(estado);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
