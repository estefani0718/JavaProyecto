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

    TipoTransporteDao dao = new TipoTransporteDao();

    @GET
    public List<TipoTransporte> obtenerTodos() {
        return dao.obtenerTodos();
    }

    @GET
    @Path("/{placa}")
    public TipoTransporte obtenerPorPlaca(@PathParam("placa") String placa) {
        return dao.obtenerPorPlaca(placa);
    }

    @POST
    public String insertar(TipoTransporte transporte) {
        boolean exito = dao.insertar(transporte);
        if (exito) {
            return "Tipo de transporte insertado correctamente.";
        } else {
            return "Error al insertar tipo de transporte.";
        }
    }

    @PUT
    @Path("/{placa}")
    public String actualizar(@PathParam("placa") String placa, TipoTransporte transporte) {
        transporte.setPlaca(placa);
        boolean exito = dao.actualizar(transporte);
        if (exito) {
            return "Tipo de transporte actualizado correctamente.";
        } else {
            return "Error al actualizar tipo de transporte.";
        }
    }

    @DELETE
    @Path("/{placa}")
    public String eliminar(@PathParam("placa") String placa) {
        boolean exito = dao.eliminar(placa);
        if (exito) {
            return "Tipo de transporte eliminado correctamente.";
        } else {
            return "Error al eliminar tipo de transporte.";
        }
    }
}