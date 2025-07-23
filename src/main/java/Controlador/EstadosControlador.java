/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Estados;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eeste
 */
@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadosControlador {
    private EstadosServicios servicio = new EstadosServicios();

    @GET
    public List<Estados> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GET
    @Path("/{id}")
    public Estados obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }
    
    @GET
    @Path("/nombreEstado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerIdPorNombre(@QueryParam("nombre") String nombre) {
        try {
            int id = servicio.obtenerIdPorNombre(nombre);
            return Response.ok(id).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Estado no encontrado: " + nombre)
                           .build();
        }
    }

    @POST
    public boolean insertar(Estados estado) {
        return servicio.insertar(estado);
    }

    @PUT
    public boolean actualizar(Estados estado) {
        return servicio.actualizar(estado);
    }

    @DELETE
    @Path("/{id}")
    public boolean eliminar(@PathParam("id") int id) {
        return servicio.eliminar(id);
    }
}
