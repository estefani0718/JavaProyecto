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

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Controlador REST para la entidad Residencia
 */
@Path("/Residencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResidenciaControlador {

    private ResidenciaServicios servicio = new ResidenciaServicios();

    @GET
    public List<Residencia> listar() {
        return servicio.obtenerTodas();
    }

    @GET
    @Path("/{id}")
    public Residencia obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }

    @POST
    public Response guardar(Residencia residencia) {
        boolean exito = servicio.insertar(residencia).contains("correctamente");
        if (exito) {
            return Response.status(Response.Status.CREATED).entity("Residencia registrada exitosamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar la residencia").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, Residencia residencia) {
        boolean exito = servicio.actualizar(id, residencia).contains("correctamente");
        if (exito) {
            return Response.ok("Residencia actualizada correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Residencia no encontrada").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        boolean exito = servicio.eliminar(id).contains("correctamente");
        if (exito) {
            return Response.ok("Residencia eliminada correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Residencia no encontrada").build();
        }
    }
}
