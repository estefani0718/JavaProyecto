/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoPaquete;
import Modelo.TipoPaqueteDao;
import Conexion.ClaseConexion;
import Modelo.TipoPaqueteDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eeste
 */
public class TipoPaqueteServicios {

    private final TipoPaqueteDao dao = new TipoPaqueteDao();

    /**
     * Lista todos los paquetes registrados.
     */
    public List<TipoPaqueteDto> listar() {
        return dao.listar();
    }

    /**
     * Guarda un nuevo paquete.
     */
    public boolean guardar(TipoPaqueteDto dto) {
        return dao.guardar(dto);
    }

    /**
     * Busca un paquete por su ID.
     */
    public TipoPaqueteDto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    /**
     * Actualiza los datos de un paquete por su ID.
     */
    public boolean actualizarPorId(TipoPaqueteDto dto) {
        return dao.actualizarPorId(dto);
    }

    /**
     * Elimina lógicamente un paquete por su ID (cambiando su estado).
     */
    public boolean eliminarLogico(int id, String nuevoEstado) {
        return dao.eliminarLogico(id, nuevoEstado);
    }
}

