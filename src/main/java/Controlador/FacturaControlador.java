/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Factura;
import Modelo.FacturaDto;
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
 * Controlador REST para gestionar operaciones sobre las facturas.
 * Expone endpoints HTTP que interactúan con el servicio.
 */
@Path("/facturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacturaControlador {

    private final FacturaServicios servicio = new FacturaServicios();

    /**
     * Endpoint para listar todas las facturas.
     */
    @GET
    public List<FacturaDto> listarFacturas() {
        return servicio.listarFacturas();
    }

    /**
     * Endpoint para obtener una factura por su ID.
     */
    @GET
    @Path("/{id}")
    public Response obtenerFacturaPorId(@PathParam("id") int id) {
        FacturaDto factura = servicio.obtenerFacturaPorId(id);
        if (factura != null) {
            return Response.ok(factura).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Factura no encontrada con ID: " + id)
                    .build();
        }
    }

    /**
     * Endpoint para obtener la primera factura asociada al documento de un usuario.
     */
    @GET
    @Path("/documento/{documento}")
    public Response obtenerPrimeraFacturaPorDocumento(@PathParam("documento") Long documento) {
        FacturaDto factura = servicio.obtenerPrimeraFacturaPorDocumento(documento);
        if (factura != null) {
            return Response.ok(factura).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron facturas para el documento: " + documento)
                    .build();
        }
    }

    /**
     * Endpoint para registrar una nueva factura.
     */
    @POST
    public Response registrarFactura(FacturaDto factura) {
        boolean registrada = servicio.registrarFactura(factura);
        if (registrada) {
            return Response.status(Response.Status.CREATED)
                    .entity("Factura registrada exitosamente").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No se pudo registrar la factura").build();
        }
    }

    /**
     * Endpoint para actualizar una factura por su ID.
     */
    @PUT
    @Path("/id")
    public Response actualizarFacturaPorId(FacturaDto factura) {
        boolean actualizada = servicio.actualizarFacturaPorId(factura);
        if (actualizada) {
            return Response.ok("Factura actualizada por ID correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo actualizar la factura").build();
        }
    }

    /**
     * Endpoint para actualizar una factura por documento del usuario.
     */
    @PUT
    @Path("/documento")
    public Response actualizarFacturaPorDocumento(FacturaDto factura) {
        boolean actualizada = servicio.actualizarFacturaPorDocumento(factura);
        if (actualizada) {
            return Response.ok("Factura actualizada por documento correctamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo actualizar la factura").build();
        }
    }

    /**
     * Endpoint para eliminar lógicamente una factura (cambio de estado).
     */
    @DELETE
    @Path("/{id}")
    public Response eliminarFactura(@PathParam("id") int id) {
        boolean eliminada = servicio.eliminarFactura(id);
        if (eliminada) {
            return Response.ok("Factura eliminada lógicamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo eliminar la factura").build();
        }
    }
}