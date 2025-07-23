/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */
import Modelo.TipoCliente;
import Modelo.TipoClienteDao;
import java.sql.Connection;
import java.util.List;

public class TipoClienteServicios {
    private TipoClienteDao dao;

    public TipoClienteServicios(Connection conexion) {
        this.dao = new TipoClienteDao();
    }

    // Obtener todos los tipos de cliente
    public List<TipoCliente> obtenerTodos() {
        return dao.obtenerTodos();
    }

    // Obtener un tipo de cliente por su ID
    public TipoCliente obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }

    // Insertar un nuevo tipo de cliente
    public boolean insertar(TipoCliente tipo) {
        return dao.insertar(tipo);
    }

    // Actualizar un tipo de cliente existente
    public boolean actualizar(TipoCliente tipo) {
        return dao.actualizar(tipo);
    }

    // Eliminar un tipo de cliente por ID
    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
