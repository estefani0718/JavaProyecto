/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */
import Modelo.Residencia;
import Modelo.ResidenciaDao;
import Conexion.ClaseConexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Servicio REST para la entidad Residencia
 */
@Path("/residencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResidenciaServicios {

   
    /**
     * Listar todas las residencias.
     * @return lista de residencias
     */
    public List<Residencia> listarResidencias() {
        ResidenciaDao dao = new ResidenciaDao();
        return dao.obtenerTodas();
    }

    /**
     * Buscar una residencia por su ID.
     * @param id identificador de la residencia
     * @return objeto Residencia o null si no se encuentra
     */
    public Residencia buscarResidenciaPorId(int id) {
        ResidenciaDao dao = new ResidenciaDao();
        return dao.obtenerPorId(id);
    }

    /**
     * Buscar el ID de una residencia por el nombre del municipio.
     * @param nombre nombre del municipio
     * @return ID de la residencia
     * @throws SQLException si no se encuentra el nombre
     */
    public int obtenerIdPorNombre(String nombre) throws SQLException {
        ResidenciaDao dao = new ResidenciaDao();
        return dao.obtenerIdPorNombre(nombre);
    }

    /**
     * Registrar una nueva residencia.
     * @param residencia objeto Residencia con el nombre del municipio
     * @return true si se insertó correctamente
     */
    public boolean registrarResidencia(Residencia residencia) {
        ResidenciaDao dao = new ResidenciaDao();
        return dao.insertar(residencia);
    }

   /**
     * Actualizar una residencia existente por su ID.
     * @param id ID que llega por URL
     * @param residencia objeto con el nuevo nombre
     */
    public boolean actualizarResidencia(int id, Residencia residencia) {
        ResidenciaDao dao = new ResidenciaDao();
        residencia.setCodigoResidencia(id); // Muy importante: establecer el ID
        return dao.actualizar(residencia);
    }

    /**
     * Eliminar una residencia por ID.
     * @param id ID de la residencia
     * @return true si se eliminó correctamente
     */
    public boolean eliminarResidencia(int id) {
        ResidenciaDao dao = new ResidenciaDao();
        return dao.eliminar(id);
    }
}