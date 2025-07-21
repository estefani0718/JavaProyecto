/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eeste
 */
/**
 * Esta clase representa el modelo de datos para la tabla Roles.
 * Aquí se definen los atributos que coinciden con las columnas de la base de datos.
 * Se utiliza como puente entre la base de datos y el código Java.
 */
public class Roles {
    private int codigoRol;
    private String rol;

    public int getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(int codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
