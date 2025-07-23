/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoDocumento;
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
import javax.ws.rs.core.Response;

/**
 *
 * @author eeste
 */
/**
 * Controlador REST para la entidad TipoDocumento
 * Expone endpoints para operaciones CRUD usando Jersey y Jackson.
 */
@Path("/TipoDocumento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoDocumentoControlador {

    private TipoDocumentoServicios servicio = new TipoDocumentoServicios();

    @GET
    public List<TipoDocumento> listar() {
        return servicio.listar();
    }

    @GET
    @Path("/{id}")
    public TipoDocumento obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }

    @POST
    public Response guardar(TipoDocumento tipo) {
        boolean exito = servicio.insertar(tipo).contains("correctamente");
        if (exito) {
            return Response.status(Response.Status.CREATED).entity("Tipo de documento registrado exitosamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar el tipo de documento").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, TipoDocumento tipo) {
        boolean exito = servicio.actualizar(id, tipo).contains("correctamente");
        if (exito) {
            return Response.ok("Tipo de documento actualizado correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Tipo de documento no encontrado").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        boolean exito = servicio.eliminar(id).contains("correctamente");
        if (exito) {
            return Response.ok("Tipo de documento eliminado correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Tipo de documento no encontrado").build();
        }
    }
}
