/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */
import Modelo.Residencia;
import Modelo.ResidenciaDao;
import Utils.ClaseConexion;

import java.sql.Connection;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Servicio REST para la entidad Residencia
 */
@Path("/residencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResidenciaServicios {

    private ResidenciaDao dao;

    public ResidenciaServicios() {
        Connection conexion = ClaseConexion.obtenerConexion(); // Asegúrate de tener esta clase en Utils
        dao = new ResidenciaDao();
    }

    @GET
    public List<Residencia> obtenerTodas() {
        return dao.obtenerTodas();
    }

    @GET
    @Path("/{id}")
    public Residencia obtenerPorId(@PathParam("id") int id) {
        return dao.obtenerPorId(id);
    }

    @POST
    public String insertar(Residencia residencia) {
        boolean exito = dao.insertar(residencia);
        return exito
            ? "Residencia insertada correctamente."
            : "Error al insertar residencia.";
    }

    @PUT
    @Path("/{id}")
    public String actualizar(@PathParam("id") int id, Residencia residencia) {
        residencia.setCodigoResidencia(id);
        boolean exito = dao.actualizar(residencia);
        return exito
            ? "Residencia actualizada correctamente."
            : "Error al actualizar residencia.";
    }

    @DELETE
    @Path("/{id}")
    public String eliminar(@PathParam("id") int id) {
        boolean exito = dao.eliminar(id);
        return exito
            ? "Residencia eliminada correctamente."
            : "Error al eliminar residencia.";
    }
}