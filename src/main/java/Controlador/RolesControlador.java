/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Roles;
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
@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolesControlador {
    private RolesServicios servicio = new RolesServicios();

    @GET
    public List<Roles> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GET
    @Path("/{id}")
    public Roles obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }

    @POST
    public boolean insertar(Roles rol) {
        return servicio.insertar(rol);
    }

    @PUT
    public boolean actualizar(Roles rol) {
        return servicio.actualizar(rol);
    }

    @DELETE
    @Path("/{id}")
    public boolean eliminar(@PathParam("id") int id) {
        return servicio.eliminar(id);
    }
}
