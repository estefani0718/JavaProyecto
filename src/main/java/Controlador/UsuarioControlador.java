    
package Controlador;

import Modelo.Usuarios;
import Utils.ClaseConexion;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eeste
 */

/**
 * Controlador REST para la entidad Usuarios
 * Expone endpoints para operaciones CRUD usando Jersey y Jackson.
 */
@Path("/Usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioControlador {

    private UsuariosServicios servicio = new UsuariosServicios();

    @GET
    public List<Usuarios> listar() throws Exception {
        return servicio.obtenerTodos();
    }

    @GET
    @Path("/{id}")
    public Usuarios obtenerPorId(@PathParam("id") int id) throws Exception {
        return servicio.obtenerPorId(id);
    }

    @GET
    @Path("/documento/{documento}")
    public Usuarios obtenerPorDocumento(@PathParam("documento") long documento) throws Exception {
        return servicio.obtenerPorDocumento(documento);
    }

    @POST
    public Response guardar(Usuarios usuario) throws Exception {
        boolean exito = servicio.guardar(usuario);
        if (exito) {
            return Response.status(Response.Status.CREATED).entity("Usuario registrado exitosamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar el usuario").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, Usuarios usuario) throws Exception {
        boolean exito = servicio.actualizar(id, usuario);
        if (exito) {
            return Response.ok("Usuario actualizado correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }
    }

    @DELETE
    @Path("/{documento}")
    public Response eliminarUsuario(@PathParam("documento") long documento) {
        boolean exito = servicio.eliminar(documento);
        if (exito) {
            return Response.ok("Usuario deshabilitado (estado cambiado)").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontró el usuario").build();
        }
    }
}