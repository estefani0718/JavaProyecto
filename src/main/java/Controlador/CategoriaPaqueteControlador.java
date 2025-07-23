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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class CategoriaPaqueteControlador{

    private CategoriaPaqueteServicios servicio;

    public CategoriaPaqueteControlador() {
        this.servicio = new CategoriaPaqueteServicios();
    }

    @GET
    public List<CategoriasPaquetes> obtenerTodos() {
        return servicio.obtenerTodas();
    }

    @GET
    @Path("/{id}")
    public CategoriasPaquetes obtenerPorId(@PathParam("id") int id) {
        return servicio.obtenerPorId(id);
    }

    @POST
    public boolean insertar(CategoriasPaquetes cp) {
        return servicio.insertar(cp);
    }

    @PUT
    public boolean actualizar(CategoriasPaquetes cp) {
        return servicio.actualizar(cp);
    }

    @DELETE
    @Path("/{id}")
    public boolean eliminar(@PathParam("id") int id) {
        return servicio.eliminar(id);
    }
}
