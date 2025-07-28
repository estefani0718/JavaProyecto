/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoTransporte;
import Modelo.TipoTransporteDao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author eeste
 */
/**
 * Servicio REST para la entidad TipoTransporte
 */
@Path("/tipotransporte")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoTransporteServicios {

     private final TipoTransporteDao dao;

    public TipoTransporteServicios() {
        this.dao = new TipoTransporteDao();
    }

    /**
     * Inserta un nuevo registro de TipoTransporte en la base de datos.
     * @param transporte Objeto de tipo TipoTransporte.
     * @return true si la operación fue exitosa, false si falló.
     */
    public boolean insertarTipoTransporte(TipoTransporte transporte) {
        return dao.insertar(transporte);
    }

    /**
     * Obtiene todos los registros de TipoTransporte.
     * @return Lista de objetos TipoTransporte.
     */
    public List<TipoTransporte> listarTodos() {
        return dao.obtenerTodos();
    }

    /**
     * Obtiene un TipoTransporte por su placa.
     * @param placa Placa del vehículo.
     * @return Objeto TipoTransporte si lo encuentra, null si no existe.
     */
    public TipoTransporte obtenerPorPlaca(String placa) {
        return dao.obtenerPorPlaca(placa);
    }

    /**
     * Actualiza los datos de un TipoTransporte según su placa.
     * @param transporte Objeto con datos actualizados.
     * @return true si se actualizó correctamente, false si falló.
     */
    public boolean actualizarTipoTransporte(TipoTransporte transporte) {
        return dao.actualizar(transporte);
    }

    /**
     * Elimina un TipoTransporte de la base de datos por su placa.
     * @param placa Placa del vehículo.
     * @return true si se eliminó correctamente, false si falló.
     */
    public boolean eliminarTipoTransporte(String placa) {
        return dao.eliminar(placa);
    }
}