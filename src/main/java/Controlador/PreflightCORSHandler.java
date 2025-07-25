package Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eeste
 */
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Maneja las solicitudes HTTP OPTIONS (preflight) para CORS.
 */
@Path("{any:.*}")
public class PreflightCORSHandler {

    @OPTIONS
    public Response handlePreflight() {
        return Response.ok().build();
    }
}