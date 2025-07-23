/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoDocumento;
import Modelo.TipoDocumentoDAO;
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
// Este servicio expone la tabla TipoDocumento como una API REST
@Path("/tipodocumento")
public class TipoDocumentoServicios {

    TipoDocumentoDAO dao = new TipoDocumentoDAO();

    // GET: Listar todos los tipos de documento
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoDocumento> listar() {
        return dao.obtenerTodos();
    }

    // POST: Insertar nuevo tipo de documento
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertar(TipoDocumento tipo) {
        boolean exito = dao.insertar(tipo);
        if (exito) {
            return "Tipo de documento insertado correctamente.";
        } else {
            return "Error al insertar tipo de documento.";
        }
    }
        // GET: Obtener un tipo de documento por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TipoDocumento obtenerPorId(@PathParam("id") int id) {
        return dao.obtenerPorId(id);
    }
    // PUT: Actualizar tipo de documento por ID
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String actualizar(@PathParam("id") int id, TipoDocumento tipo) {
        tipo.setCodigo_Tdocumento(id); // Asigna el ID del path al objeto
        boolean exito = dao.actualizar(tipo);
        if (exito) {
            return "Tipo de documento actualizado correctamente.";
        } else {
            return "Error al actualizar tipo de documento.";
        }
    }

    // DELETE: Eliminar tipo de documento por ID
    @DELETE
    @Path("/{id}")
    public String eliminar(@PathParam("id") int id) {
        boolean exito = dao.eliminar(id);
        if (exito) {
            return "Tipo de documento eliminado correctamente.";
        } else {
            return "Error al eliminar tipo de documento.";
        }
    }
}

