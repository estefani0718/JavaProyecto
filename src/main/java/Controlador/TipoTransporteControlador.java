/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoTransporte;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eeste
 */
/**
 * Controlador REST para la entidad TipoTransporte
 * Expone endpoints para operaciones CRUD.
 */
@Path("/TipoTransporte")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoTransporteControlador {

    private TipoTransporteServicios servicio = new TipoTransporteServicios();

    @GET
    public List<TipoTransporte> listar() {
        return servicio.obtenerTodos();
    }

    @GET
    @Path("/{placa}")
    public TipoTransporte obtenerPorPlaca(@PathParam("placa") String placa) {
        return servicio.obtenerPorPlaca(placa);
    }

    @POST
    public Response guardar(TipoTransporte transporte) {
        boolean exito = servicio.insertar(transporte).contains("correctamente");
        if (exito) {
            return Response.status(Response.Status.CREATED).entity("Tipo de transporte registrado exitosamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar el tipo de transporte").build();
        }
    }

    @PUT
    @Path("/{placa}")
    public Response actualizar(@PathParam("placa") String placa, TipoTransporte transporte) {
        boolean exito = servicio.actualizar(placa, transporte).contains("correctamente");
        if (exito) {
            return Response.ok("Tipo de transporte actualizado correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Tipo de transporte no encontrado").build();
        }
    }

    @DELETE
    @Path("/{placa}")
    public Response eliminar(@PathParam("placa") String placa) {
        boolean exito = servicio.eliminar(placa).contains("correctamente");
        if (exito) {
            return Response.ok("Tipo de transporte eliminado correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontró el tipo de transporte").build();
        }
    }
}