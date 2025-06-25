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


public class UsuariosServicios {

 private UsuariosDao dao = new UsuariosDao();

    public List<Usuarios> obtenerTodos() {
        return dao.listarTodos();
    }

    public Usuarios obtenerPorDocumento(int documento) {
        return dao.listarTodos().stream()
                .filter(u -> u.getDocumento_usuario() == documento)
                .findFirst()
                .orElse(null);
    }

    public boolean guardar(Usuarios usuario) {
        return dao.guardarUsuario(usuario);
    }

    public boolean actualizar(int documento, Usuarios usuarioNuevo) {
        List<Usuarios> lista = dao.listarTodos();

        for (Usuarios u : lista) {
            if (u.getDocumento_usuario() == documento) {
                u.setNombre_usuario(usuarioNuevo.getNombre_usuario());
                u.setCodigo_Tdocumento(usuarioNuevo.getCodigo_Tdocumento());
                u.setGenero_usuario(usuarioNuevo.getGenero_usuario());
                u.setDireccion_usuario(usuarioNuevo.getDireccion_usuario());
                u.setTelefono_usuario(usuarioNuevo.getTelefono_usuario());
                u.setCorreo(usuarioNuevo.getCorreo());
                u.setId_estado(usuarioNuevo.getId_estado());
                u.setCodigo_rol(usuarioNuevo.getCodigo_rol());
                u.setCodigo_residencia(usuarioNuevo.getCodigo_residencia());
                u.setCodigo_tipoC(usuarioNuevo.getCodigo_tipoC());
                // Aquí deberías usar dao.actualizar(u) si lo implementas
                return true;
            }
        }

        return false;
    }

    public boolean eliminar(int documento) {
        //
        return obtenerPorDocumento(documento) != null;
    }
}
