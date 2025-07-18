    
package Controlador;

import Modelo.Usuarios;
import Servicios.UsuariosServicios;
import Utils.ClaseConexion;
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

    private final UsuariosServicios servicio = new UsuariosServicios();

    // Obtener todos los usuarios
    @GET
    public Response listarUsuarios() {
        List<Usuarios> lista = servicio.obtenerTodos();
        return Response.ok(lista).build();
    }

    // Obtener un usuario por su documento
    @GET
    @Path("/{documento}")
    public Response obtenerUsuario(@PathParam("documento") int documento) {
        Usuarios usuario = servicio.obtenerPorDocumento(documento);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con documento: " + documento)
                    .build();
        }
    }

    // Crear nuevo usuario
    @POST
    public Response guardarUsuario(Usuarios usuario) {
        boolean exito = servicio.guardar(usuario);
        if (exito) {
            return Response.status(Response.Status.CREATED)
                    .entity("Usuario guardado exitosamente: " + usuario.getNombre_usuario())
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al guardar el usuario.")
                    .build();
        }
    }

    // Actualizar usuario existente
    @PUT
    @Path("/{documento}")
    public Response actualizarUsuario(@PathParam("documento") int documento, Usuarios usuario) {
        boolean exito = servicio.actualizar(documento, usuario);
        if (exito) {
            return Response.ok("Usuario actualizado correctamente.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontró el usuario con documento: " + documento)
                    .build();
        }
    }

    // Eliminar usuario por documento
    @DELETE
    @Path("/{documento}")
    public Response eliminarUsuario(@PathParam("documento") int documento) {
        boolean exito = servicio.eliminar(documento);
        if (exito) {
            return Response.ok("Usuario eliminado correctamente.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontró el usuario con documento: " + documento)
                    .build();
        }
    }
}