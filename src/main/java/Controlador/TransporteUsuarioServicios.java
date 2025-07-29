/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TransporteUsuarioDao;
import Modelo.TransporteUsuarioDto;
import java.util.List;

/**
 *
 * @author eeste
 */
/**
 * Servicio que actúa como capa intermedia entre el controlador y el DAO para TransporteUsuario.
 * Encapsula la lógica de negocio relacionada con TransporteUsuario.
 */
public class TransporteUsuarioServicios {

    private final TransporteUsuarioDao dao = new TransporteUsuarioDao();

    /**
     * Lista todos los registros activos de TransporteUsuario.
     * 
     * @return Lista de TransporteUsuarioDto
     */
    public List<TransporteUsuarioDto> listar() {
        return dao.listar();
    }

    /**
     * Guarda un nuevo registro de TransporteUsuario.
     * 
     * @param dto Objeto TransporteUsuarioDto con los datos a guardar
     * @return true si fue exitoso, false si hubo error
     */
    public boolean guardar(TransporteUsuarioDto dto) {
        return dao.guardar(dto);
    }

    /**
     * Busca un registro por su ID (código de TransporteUsuario).
     * 
     * @param id ID del registro
     * @return Objeto TransporteUsuarioDto si se encuentra, null si no
     */
    public TransporteUsuarioDto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    /**
     * Actualiza un registro de TransporteUsuario por su ID.
     * 
     * @param dto Objeto con los datos actualizados
     * @return true si fue exitoso, false si hubo error
     */
    public boolean actualizarPorId(TransporteUsuarioDto dto) {
        return dao.actualizarPorId(dto);
    }

    /**
     * Elimina lógicamente un registro de TransporteUsuario por su ID cambiando su estado.
     * 
     * @param id ID del registro a eliminar
     * @param nuevoEstado Estado (nombre) a aplicar
     * @param dto Objeto que contiene el estado en forma de nombre legible
     * @return true si fue exitoso, false si hubo error
     */
    public boolean eliminarPorId(int id, String nuevoEstado, TransporteUsuarioDto dto) {
        return dao.eliminarLogicoPorId(id, nuevoEstado, dto);
    }
}
