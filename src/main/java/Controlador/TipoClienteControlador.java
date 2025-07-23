/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoCliente;
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


/**
 *
 * @author eeste
 */
@Path("/tipocliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoClienteControlador {

    private TipoClienteServicios servicio;

    public TipoClienteControlador() {
        this.servicio = new TipoClienteServicios(ClaseConexion.obtenerConexion());
    }

    @GET
    public List<TipoCliente> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GET
    @Path("/{id}")
    public TipoCliente obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }

    @POST
    public boolean insertar(TipoCliente tipo) {
        return servicio.insertar(tipo);
    }

    @PUT
    public boolean actualizar(TipoCliente tipo) {
        return servicio.actualizar(tipo);
    }

    @DELETE
    @Path("/{id}")
    public boolean eliminar(@PathParam("id") int id) {
        return servicio.eliminar(id);
    }
}