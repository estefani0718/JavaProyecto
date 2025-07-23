/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoPaquete;
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
@Path("/tipopaquete") // Ruta base para acceder al recurso
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoPaqueteControlador {

    TipoPaqueteServicios servicio = new TipoPaqueteServicios();

    // Obtener todos los paquetes activos
    @GET
    public List<TipoPaquete> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    // Obtener un paquete por su ID
    @GET
    @Path("/{id}")
    public TipoPaquete obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }

    // Insertar un nuevo tipo de paquete
    @POST
    public Response insertar(TipoPaquete paquete) {
        boolean insertado = servicio.insertar(paquete);
        if (insertado) {
            return Response.status(Response.Status.CREATED).entity("Tipo de paquete creado correctamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al crear tipo de paquete").build();
        }
    }

    // Actualizar un tipo de paquete
    @PUT
    public Response actualizar(TipoPaquete paquete) {
        boolean actualizado = servicio.actualizar(paquete);
        if (actualizado) {
            return Response.ok("Tipo de paquete actualizado correctamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al actualizar tipo de paquete").build();
        }
    }

    // Eliminar lógicamente un tipo de paquete (estado = 3)
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        boolean eliminado = servicio.eliminarLogicamente(id);

        if (eliminado) {
            return Response.ok("Tipo de paquete eliminado correctamente (lógico)").build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("No se puede eliminar este tipo de paquete porque está relacionado con otros datos.")
                    .build();
        }
    }
}
