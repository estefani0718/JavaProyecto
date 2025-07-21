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
 * Clase modelo que representa la tabla TransporteUsuario en la base de datos.
 * Relaciona un usuario con un vehículo y los años de experiencia del usuario en transporte.
 */
public class TransporteUsuario {
    private int codigoTransporteUsuario;  // ID único del registro
    private String placa;                 // Placa del vehículo (relación con TipoTransporte)
    private long documentoUsuario;        // Documento del usuario (relación con Usuarios)
    private int aniosExperiencia;         // Años de experiencia del usuario

    public int getCodigoTransporteUsuario() {
        return codigoTransporteUsuario;
    }

    public void setCodigoTransporteUsuario(int codigoTransporteUsuario) {
        this.codigoTransporteUsuario = codigoTransporteUsuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
