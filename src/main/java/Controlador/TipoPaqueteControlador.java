/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.TipoPaqueteDto;
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
import java.util.List;

@Path("/tipopaquete")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoPaqueteControlador {

    private final TipoPaqueteServicios servicio = new TipoPaqueteServicios();

    /**
     * Lista todos los paquetes.
     */
    @GET
    public Response listar() {
        List<TipoPaqueteDto> lista = servicio.listar();
        return Response.ok(lista).build();
    }

    /**
     * Registra un nuevo tipo de paquete.
     */
    @POST
    public Response guardar(TipoPaqueteDto dto) {
        boolean creado = servicio.guardar(dto);
        if (creado) {
            return Response.status(Response.Status.CREATED).entity("Paquete registrado correctamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar el paquete").build();
        }
    }

    /**
     * Obtiene un paquete por su ID.
     */
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        TipoPaqueteDto dto = servicio.buscarPorId(id);
        if (dto != null) {
            return Response.ok(dto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Paquete no encontrado").build();
        }
    }

    /**
     * Actualiza un paquete por su ID.
     */
    @PUT
    public Response actualizar(TipoPaqueteDto dto) {
        boolean actualizado = servicio.actualizarPorId(dto);
        if (actualizado) {
            return Response.ok("Paquete actualizado correctamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo actualizar el paquete").build();
        }
    }
    
    /**
     * Elimina lógicamente un paquete (por ID y estado nuevo).
     */
     /**
     * Elimina lógicamente un paquete (por ID y estado nuevo).
     */
    @DELETE
    @Path("/{id}/{nuevoEstado}")
    public Response eliminar(@PathParam("id") int id, @PathParam("nuevoEstado") int nuevoEstado) {
        boolean eliminado = servicio.eliminarLogico(id, nuevoEstado);
        if (eliminado) {
            return Response.ok("Paquete eliminado lógicamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo eliminar el paquete").build();
        }
    }
}