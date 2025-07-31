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
 * DTO para representar los datos de la tabla TipoPaquete.
 * Contiene información relacionada con los paquetes enviados en una factura.
 * Las claves foráneas están representadas por su nombre legible (String), no por ID.
 */
public class TipoPaqueteDto {

    private int codigoTpaquete;           // ID único del tipo de paquete (PK)
    private String nombrePaquete;         // Nombre del paquete
    private String detallesAdicionales;   // Información adicional del paquete
    private String categoriaPaquete;      // Nombre de la categoría (antes era codigoPaquete - int FK)
    private String estado;                // Estado (ej: "Activo", "Inactivo")
    private String origen;                // Ciudad o lugar de origen
    private String metodoPago;            // Método de pago (ej: "Efectivo", "Tarjeta")
    private String destino;               // Ciudad o lugar de destino

    // Constructor vacío
    public TipoPaqueteDto() {
    }

    // Constructor completo
    public TipoPaqueteDto(int codigoTpaquete, String nombrePaquete, String detallesAdicionales,
                          String categoriaPaquete, String estado, String origen,
                          String metodoPago, String destino) {
        this.codigoTpaquete = codigoTpaquete;
        this.nombrePaquete = nombrePaquete;
        this.detallesAdicionales = detallesAdicionales;
        this.categoriaPaquete = categoriaPaquete;
        this.estado = estado;
        this.origen = origen;
        this.metodoPago = metodoPago;
        this.destino = destino;
    }

    // Getters y Setters

    public int getCodigoTpaquete() {
        return codigoTpaquete;
    }

    public void setCodigoTpaquete(int codigoTpaquete) {
        this.codigoTpaquete = codigoTpaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }

    public String getCategoriaPaquete() {
        return categoriaPaquete;
    }

    public void setCategoriaPaquete(String categoriaPaquete) {
        this.categoriaPaquete = categoriaPaquete;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
