    
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
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioControlador {
    //metodos  crud para: mostrar todos los usuarios , obtener , guardar , actualizar y eliminar.
     private UsuariosServicios servicio = new UsuariosServicios();
     
    // metodo de prueba si el api funciona 
    @GET
    @Path("/prueba")
    @Produces(MediaType.TEXT_PLAIN)
    public String prueba() {
        return "API funcionando ";
    }
     
     
    @GET
    public Response listarUsuarios() {
        List<Usuarios> lista = servicio.obtenerTodos();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{documento}")
    public Response obtenerUsuario(@PathParam("documento") int documento) {
        Usuarios usuario = servicio.obtenerPorDocumento(documento);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response guardarUsuario(Usuarios usuario) {
        boolean exito = servicio.guardar(usuario);
        if (exito) {
            return Response.status(Response.Status.CREATED)
                    .entity("Usuario guardado exitosamente:\n" + usuario)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al guardar el usuario.")
                    .build();
        }
    } 


    @PUT
    @Path("/{documento}")
    public Response actualizarUsuario(@PathParam("documento") int documento, Usuarios usuario) {
        boolean exito = servicio.actualizar(documento, usuario);
        if (exito) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{documento}")
    public Response eliminarUsuario(@PathParam("documento") int documento) {
        boolean exito = servicio.eliminar(documento);
        if (exito) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
     
}
