/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.UsuariosDto;

/**
 *
 * @author eeste
 */
public class middleware {
     /**
     * Valida todos los campos del DTO de usuario.
     * @param dto Objeto UsuariosDto con los datos a validar.
     * @return null si todo está correcto, o un mensaje de error en caso de alguna falla.
     */
    public static String validar(UsuariosDto dto) {
        
        // Validar nombre
        if (dto.getNombre_usuario() == null || dto.getNombre_usuario().trim().isEmpty()) {
            return "El nombre del usuario es obligatorio.";
        }

        // Validar documento
        if (dto.getDocumento_usuario() <= 0) {
            return "El documento debe ser un número mayor a cero.";
        }

        // Validar tipo de documento (debe venir un texto como "Cédula")
        if (dto.getTipo_documento() == null || dto.getTipo_documento().trim().isEmpty()) {
            return "Debe seleccionar un tipo de documento.";
        }

        // Validar género
        if (dto.getGenero_usuario() == null || dto.getGenero_usuario().trim().isEmpty()) {
            return "Debe seleccionar un género.";
        }

        // Validar dirección
        if (dto.getDireccion_usuario() == null || dto.getDireccion_usuario().trim().isEmpty()) {
            return "La dirección es obligatoria.";
        }

        // Validar teléfono (debe tener solo números y tener 10 dígitos)
        if (dto.getTelefono_usuario() == null || !dto.getTelefono_usuario().matches("\\d{10}")) {
            return "El teléfono debe tener exactamente 10 dígitos numéricos.";
        }

        // Validar correo (expresión regular simple para correos)
        if (dto.getCorreo() == null || !dto.getCorreo().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Debe ingresar un correo válido.";
        }

        // Validar estado (debe venir como texto: "Activo", "Inactivo", etc.)
        if (dto.getEstado() == null || dto.getEstado().trim().isEmpty()) {
            return "Debe especificar un estado válido.";
        }

        // Validar rol (debe venir como texto: "Administrador", "Domiciliario", "Cliente", etc.)
        if (dto.getRol() == null || dto.getRol().trim().isEmpty()) {
            return "Debe seleccionar un rol válido.";
        }

        // Validar residencia (Ej: "Bucaramanga", "Florida", etc.)
        if (dto.getResidencia() == null || dto.getResidencia().trim().isEmpty()) {
            return "Debe seleccionar una residencia válida.";
        }

        // Validar nombre de usuario
        if (dto.getUsuario() == null || dto.getUsuario().trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }

        // Validar contraseña (mínimo 6 caracteres)
        if (dto.getContrasena() == null || dto.getContrasena().length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        // Si todo está correcto, retornamos null
        return null;
     }
}
