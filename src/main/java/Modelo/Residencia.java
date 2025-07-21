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
 * Esta clase representa el modelo de datos para la tabla Residencia.
 * Aquí se definen los atributos que coinciden con las columnas de la base de datos.
 * Se utiliza como puente entre la base de datos y el código Java.
 */
public class Residencia {
    private int codigoResidencia;
    private String nombreMunicipio;

    // Getters y Setters
    public int getCodigoResidencia() {
        return codigoResidencia;
    }

    public void setCodigoResidencia(int codigoResidencia) {
        this.codigoResidencia = codigoResidencia;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }
}
