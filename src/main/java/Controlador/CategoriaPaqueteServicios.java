/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */
import Modelo.CategoriaPaquetesDao;
import Modelo.CategoriasPaquetes;
import Utils.ClaseConexion;
import java.sql.Connection;
import java.util.List;

public class CategoriaPaqueteServicios {

    private CategoriaPaquetesDao dao;

    public CategoriaPaqueteServicios() {
        Connection conexion = ClaseConexion.obtenerConexion();
        this.dao = new CategoriaPaquetesDao(conexion);
    }

    public List<CategoriasPaquetes> obtenerTodas() {
        return dao.obtenerTodas();
    }

    public CategoriasPaquetes obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }

    public boolean insertar(CategoriasPaquetes cp) {
        return dao.insertar(cp);
    }

    public boolean actualizar(CategoriasPaquetes cp) {
        return dao.actualizar(cp);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
