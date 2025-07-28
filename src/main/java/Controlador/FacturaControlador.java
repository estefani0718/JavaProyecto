/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Factura;
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
@Path("/facturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacturaControlador {

    private FacturaServicios servicio = new FacturaServicios();

    @GET
    public Response listarFacturas() {
        List<Factura> lista = servicio.listarFacturas();
        if (lista == null) return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerFactura(@PathParam("id") int id) {
        Factura f = servicio.obtenerPorId(id);
        if (f == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(f).build();
    }

    @POST
    public Response registrarFactura(Factura factura) {
        boolean exito = servicio.registrarFactura(factura);
        if (exito) return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo crear la factura").build();
    }

    @PUT
    public Response actualizarFactura(Factura factura) {
        boolean exito = servicio.actualizarFactura(factura);
        if (exito) return Response.ok().build();
        return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo actualizar la factura").build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarFactura(@PathParam("id") int id) {
        boolean exito = servicio.eliminarFactura(id);
        if (exito) return Response.ok().entity("Factura inactivada correctamente").build();
        return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo eliminar (inactivar) la factura").build();
    }
}