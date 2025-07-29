/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * DTO que representa la entidad TransporteUsuario.
 * Contiene información sobre el transporte asociado a un usuario,
 * incluyendo el estado en formato de texto (por ejemplo, "Activo", "Inactivo").
 */
public class TransporteUsuarioDto {
    
    private int codigoTransporteUsuario;   // ID único del transporte del usuario (PK)
    private String placa;                  // Placa del vehículo (FK a TipoTransporte)
    private long documentoUsuario;         // Documento del usuario (FK a Usuarios)
    private int aniosExperiencia;          // Años de experiencia del conductor
    private String estado;                 // Estado textual del registro (Activo, Inactivo, etc.)

    // Constructor vacío
    public TransporteUsuarioDto() {
    }

    // Constructor completo
    public TransporteUsuarioDto(int codigoTransporteUsuario, String placa, long documentoUsuario,
                                int aniosExperiencia, String estado) {
        this.codigoTransporteUsuario = codigoTransporteUsuario;
        this.placa = placa;
        this.documentoUsuario = documentoUsuario;
        this.aniosExperiencia = aniosExperiencia;
        this.estado = estado;
    }

    // Getters y Setters

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
