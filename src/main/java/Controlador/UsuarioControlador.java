    
package Controlador;



import Controlador.UsuariosServicios;
import Modelo.UsuariosDto;
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
 * Controlador REST para manejar las solicitudes relacionadas con la entidad Usuarios.
 */
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioControlador {

    private final UsuariosServicios servicio = new UsuariosServicios();

    /**
     * Lista todos los usuarios con nombres legibles de relaciones.
     * @return Lista de usuarios o error 500.
     */
    @GET
    public Response listarUsuariosConNombres() {
        try {
            List<UsuariosDto> lista = servicio.obtenerTodosConNombres();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            return Response.serverError().entity("Error al listar usuarios: " + e.getMessage()).build();
        }
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado o 404 si no existe.
     */
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") int id) {
        try {
            UsuariosDto usuario = servicio.obtenerPorId(id);
            if (usuario != null) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Error al obtener usuario: " + e.getMessage()).build();
        }
    }

    /**
     * Obtiene un usuario por su número de documento.
     * @param documento Número de documento del usuario.
     * @return Usuario encontrado o 404 si no existe.
     */
    @GET
    @Path("/documento/{documento}")
    public Response obtenerPorDocumento(@PathParam("documento") long documento) {
        try {
            UsuariosDto usuario = servicio.obtenerPorDocumento(documento);
            if (usuario != null) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Error al obtener usuario: " + e.getMessage()).build();
        }
    }

    /**
     * Registra un nuevo usuario a partir de un DTO que contiene strings legibles.
     * @param dto DTO con nombres legibles.
     * @return Mensaje de éxito o error.
     */
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registrarUsuario(UsuariosDto dto) {
        try {
            boolean registrado = servicio.registrarUsuarioDesdeDTO(dto);
            if (registrado) {
                return Response.status(Response.Status.CREATED).entity("Usuario registrado correctamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo registrar el usuario").build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Error al registrar usuario: " + e.getMessage()).build();
        }
    }

    /**
     * Actualiza un usuario por su ID.
     * @param id ID del usuario.
     * @param dto DTO con los nuevos datos.
     * @return Mensaje de éxito o error.
     */
    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response actualizarUsuarioPorId(@PathParam("id") int id, UsuariosDto dto) {
        try {
            boolean actualizado = servicio.actualizarPorId(id, dto);
            if (actualizado) {
                return Response.ok("Usuario actualizado correctamente").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Error al actualizar usuario: " + e.getMessage()).build();
        }
    }

    /**
     * Actualiza un usuario por su documento.
     * @param documento Documento del usuario.
     * @param dto DTO con los nuevos datos.
     * @return Mensaje de éxito o error.
     */
    @PUT
    @Path("/documento/{documento}")
     @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response actualizarUsuarioPorDocumento(@PathParam("documento") long documento, UsuariosDto dto) {
        try {
            boolean actualizado = servicio.actualizarPorDocumento(documento, dto);
            if (actualizado) {
                return Response.ok("Usuario actualizado correctamente").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Error al actualizar usuario: " + e.getMessage()).build();
        }
    }

    /**
     * Deshabilita un usuario (eliminación lógica) cambiando su estado a 3.
     * @param documento Documento del usuario.
     * @return Mensaje de éxito o error.
     */
    @DELETE
    @Path("/documento/{documento}")
     @Consumes(MediaType.APPLICATION_JSON)
    public Response deshabilitarUsuario(@PathParam("documento") long documento) {
        try {
            boolean deshabilitado = servicio.deshabilitarUsuario(documento);
            if (deshabilitado) {
                return Response.ok("Usuario deshabilitado correctamente").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado o ya está deshabilitado").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Error al deshabilitar usuario: " + e.getMessage()).build();
        }
    }
    
   @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuariosDto dto) {
        try {
            UsuariosDto usuarioValidado = servicio.login(dto.getUsuario(), dto.getContrasena());

            if (usuarioValidado != null) {
                return Response.ok(usuarioValidado).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                               .entity("{\"mensaje\": \"Credenciales inválidas\"}")
                               .build();
            }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\": \"Error del servidor\"}")
                           .build();
        }
    }

}