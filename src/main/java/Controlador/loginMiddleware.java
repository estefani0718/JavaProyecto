/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author eeste
 */
public class loginMiddleware {
    
     
    /**
     * Valida el usuario y contraseña ingresados en el login.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña ingresada.
     * @return null si es válido, o mensaje de error si hay un problema.
     */
    public static String validarLogin(String usuario, String contrasena) {
        
        // Validar nombre de usuario
        if (usuario == null || usuario.trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }

        // Validar contraseña (mínimo 6 caracteres)
        if (contrasena == null || contrasena.trim().isEmpty()) {
            return "La contraseña es obligatoria.";
        }

        if (contrasena.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        // Si todo está correcto
        return null;
    }
}
