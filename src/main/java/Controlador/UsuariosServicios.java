/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuarios;
import Modelo.UsuariosDao;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Servicio que encapsula la lógica de negocio para la entidad Usuarios.
 * Se comunica con la capa DAO (UsuariosDAO).
 */
public class UsuariosServicios {

    private UsuariosDao dao = new UsuariosDao();

     public List<Usuarios> obtenerTodos() {
        return dao.listarTodos();
    }

    public Usuarios obtenerPorId(int id) throws Exception {
        return dao.obtenerPorId(id);
    }

    public Usuarios obtenerPorDocumento(long documento) throws Exception {
        return dao.buscarPorDocumento(documento);
    }

    public boolean guardar(Usuarios usuario) throws Exception {
        return dao.guardar(usuario);
    }

    public boolean actualizar(int id, Usuarios usuario) throws Exception {
        return dao.actualizar(id, usuario);
    }

    // Eliminar (solo cambia estado del usuario)
    public boolean eliminar(long documento) {
        return dao.deshabilitarUsuarioPorDocumento(documento);
    }
}