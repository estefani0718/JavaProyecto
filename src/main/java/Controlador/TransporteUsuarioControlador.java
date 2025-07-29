/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TransporteUsuarioDao;
import Modelo.TransporteUsuarioDto;
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
@Path("/transporteUsuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransporteUsuarioControlador {

    private final TransporteUsuarioDao dao = new TransporteUsuarioDao();

    // Listar todos los registros
    @GET
    public List<TransporteUsuarioDto> listar() {
        return dao.listar();
    }

    // Buscar por ID
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        TransporteUsuarioDto dto = dao.buscarPorId(id);
        if (dto != null) {
            return Response.ok(dto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontró el transporte con ID: " + id)
                    .build();
        }
    }

    // Registrar nuevo transporte
    @POST
    public Response guardar(TransporteUsuarioDto dto) {
        boolean guardado = dao.guardar(dto);
        if (guardado) {
            return Response.status(Response.Status.CREATED)
                    .entity("Registro guardado exitosamente.")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No se pudo guardar el registro.")
                    .build();
        }
    }

    // Actualizar por ID
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, TransporteUsuarioDto dto) {
        dto.setCodigoTransporteUsuario(id);
        boolean actualizado = dao.actualizarPorId(dto);
        if (actualizado) {
            return Response.ok("Registro actualizado correctamente.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo actualizar el registro con ID: " + id)
                    .build();
        }
    }

    // Eliminar lógicamente por ID (cambiando estado)
    @DELETE
    @Path("/{id}")
    public Response eliminarLogico(@PathParam("id") int id, TransporteUsuarioDto dto) {
        boolean eliminado = dao.eliminarLogicoPorId(id, dto.getEstado(), dto);
        if (eliminado) {
            return Response.ok("Registro eliminado lógicamente.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo eliminar el registro con ID: " + id)
                    .build();
        }
    }
}