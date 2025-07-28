/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */

import Modelo.CategoriasPaquetes;
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

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaPaqueteControlador {

    private CategoriaPaqueteServicios servicio = new CategoriaPaqueteServicios();

    @GET
    public List<CategoriasPaquetes> listar() {
        return servicio.obtenerTodas();
    }

    @GET
    @Path("/buscar/{id}")
    public Response obtenerPorId(@PathParam("id") int id) {
        CategoriasPaquetes categoria = servicio.obtenerPorId(id);
        if (categoria != null) {
            return Response.ok(categoria).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Categoría con ID " + id + " no encontrada").build();
        }
    }

    @POST
    @Path("/registrar")
    public Response registrarCategoria(CategoriasPaquetes categoria) {
        boolean creada = servicio.insertar(categoria);
        if (creada) {
            return Response.status(Response.Status.CREATED)
                    .entity("Categoría registrada correctamente").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al registrar la categoría").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarCategoria(@PathParam("id") int id, CategoriasPaquetes categoria) {
        categoria.setCodigoPaquete(id);
        boolean actualizada = servicio.actualizar(categoria);
        if (actualizada) {
            return Response.ok("Categoría actualizada correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo actualizar la categoría con ID " + id).build();
        }
    }

    @DELETE
    @Path("/eliminar/{id}")
    public Response eliminarCategoria(@PathParam("id") int id) {
        boolean eliminada = servicio.eliminar(id);
        if (eliminada) {
            return Response.ok("Categoría eliminada correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo eliminar la categoría con ID " + id).build();
        }
    }
}