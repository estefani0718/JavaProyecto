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
 * Esta clase representa el modelo de datos para la tabla TipoTransporte.
 * Aquí se definen los atributos que coinciden con las columnas de la base de datos.
 * Se utiliza como puente entre la base de datos y el código Java.
 */
public class TipoCliente {
    private int codigoTipoC;
    private String tipoCliente;

    public int getCodigoTipoC() {
        return codigoTipoC;
    }

    public void setCodigoTipoC(int codigoTipoC) {
        this.codigoTipoC = codigoTipoC;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}