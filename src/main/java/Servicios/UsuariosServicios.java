/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Modelo.Usuarios;
import Modelo.UsuariosDao;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Servicio que encapsula la lógica de negocio para la entidad Usuarios.
 * Se comunica con la capa DAO (UsuariosDAO).
 */
public class UsuariosServicios {

    private final UsuariosDao usuariosDAO;

    public UsuariosServicios() {
        this.usuariosDAO = new UsuariosDao();
    }

    // Obtener todos los usuarios
    public List<Usuarios> obtenerTodos() {
        return usuariosDAO.listarTodos();
    }

    // Obtener un usuario por documento
    public Usuarios obtenerPorDocumento(int documento) {
        return usuariosDAO.obtenerPorDocumento(documento);
    }

    // Guardar un nuevo usuario
    public boolean guardar(Usuarios usuario) {
        return usuariosDAO.guardar(usuario);
    }

    // Actualizar un usuario existente
    public boolean actualizar(int documento, Usuarios usuario) {
        return usuariosDAO.actualizar(documento, usuario);
    }

    // Eliminar un usuario por documento
    public boolean eliminar(int documento) {
        return usuariosDAO.eliminar(documento);
    }
}