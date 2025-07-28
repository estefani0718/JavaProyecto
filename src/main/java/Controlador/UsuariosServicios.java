 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;




import Modelo.UsuariosDto;
import java.sql.SQLException;
import java.util.List;
import Modelo.UsuariosDao;
import java.sql.SQLException;


/**
 * Servicio que encapsula la lógica de negocio para la entidad Usuarios.
 * Se comunica con la capa DAO (UsuariosDAO).
 */
public class UsuariosServicios {

    private final UsuariosDao usuariosDao = new UsuariosDao();
    
    public UsuariosDto login(String usuario, String contrasena) throws SQLException {
       return usuariosDao.validarLogin(usuario, contrasena);
    }
    //-----------------------------------------
    public List<Integer> obtenerIdsUsuarios() throws SQLException {
    return usuariosDao.obtenerIdsUsuarios();
    }

    //-------------------------------------------
    public List<UsuariosDto> listarUsuariosPorRol(String nombreRol) throws SQLException {
    return usuariosDao.listarPorRol(nombreRol);
   }
  //-------------------------------------------
    public List<Long> obtenerDocumentosUsuarios() throws SQLException {
    return usuariosDao.obtenerDocumentosUsuarios();
}

    /**
     * Lista todos los usuarios con los nombres legibles de las relaciones (no solo IDs).
     * @return Lista de usuarios DTO.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public List<UsuariosDto> obtenerTodosConNombres() throws SQLException {
        return usuariosDao.listarUsuariosConNombres();
    }

    /**
     * Busca un usuario por su ID (id de tabla, no documento).
     * @param id El ID del usuario.
     * @return El usuario encontrado, o null si no existe.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public UsuariosDto obtenerPorId(int id) throws SQLException {
        return usuariosDao.obtenerPorId(id);
    }

    /**
     * Busca un usuario por su número de documento.
     * @param documento Número de documento del usuario.
     * @return El usuario encontrado, o null si no existe.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public UsuariosDto obtenerPorDocumento(long documento) throws SQLException {
        return usuariosDao.buscarPorDocumento(documento);
    }

    /**
     * Registra un nuevo usuario usando los nombres legibles de relaciones (DTO).
     * @param dto Objeto DTO que contiene los datos del usuario.
     * @return true si se registró correctamente, false si falló.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public boolean registrarUsuarioDesdeDTO(UsuariosDto dto) throws SQLException {
        return usuariosDao.registrarUsuarioConNombres(dto);
    }

    /**
     * Actualiza un usuario por su ID usando nombres legibles (DTO).
     * @param id ID del usuario en la base de datos.
     * @param dto Datos nuevos del usuario.
     * @return true si se actualizó correctamente, false si falló.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public boolean actualizarPorId(int id, UsuariosDto dto) throws SQLException {
        return usuariosDao.actualizarUsuarioPorId(id, dto);
    }

    /**
     * Actualiza un usuario por su número de documento usando nombres legibles (DTO).
     * @param documento Número de documento del usuario.
     * @param dto Datos nuevos del usuario.
     * @return true si se actualizó correctamente, false si falló.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public boolean actualizarPorDocumento(long documento, UsuariosDto dto) throws SQLException {
        return usuariosDao.actualizarUsuarioPorDocumento(documento, dto);
    }

    /**
     * Deshabilita un usuario (eliminación lógica) por número de documento.
     * Cambia su estado a "3" (elimnado).
     * @param documento Documento del usuario.
     * @return true si se deshabilitó correctamente, false si falló.
     */
    public boolean deshabilitarUsuario(long documento) {
        return usuariosDao.deshabilitarUsuarioPorDocumento(documento);
    }
    
}