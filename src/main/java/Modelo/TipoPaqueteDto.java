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
 */
public class TipoPaqueteDto {
    
    private int codigoTpaquete;           // ID único del tipo de paquete (PK)
    private String nombrePaquete;         // Nombre del paquete
    private String detallesAdicionales;   // Información adicional del paquete
    private int codigoPaquete;            // FK a la tabla CategoriaPaquete
    private int codigoFactura;            // FK a la tabla Factura
    private int idEstado;                 // FK al estado (activo, inactivo, etc.)
    private String origen;                // Lugar de origen del paquete
    private String metodoPago;            // Método de pago (efectivo, tarjeta, etc.)
    private String destino;               // Lugar de destino del paquete

    // Constructor vacío
    public TipoPaqueteDto() {
    }

    // Constructor completo
    public TipoPaqueteDto(int codigoTpaquete, String nombrePaquete, String detallesAdicionales,
                          int codigoPaquete, int codigoFactura, int idEstado,
                          String origen, String metodoPago, String destino) {
        this.codigoTpaquete = codigoTpaquete;
        this.nombrePaquete = nombrePaquete;
        this.detallesAdicionales = detallesAdicionales;
        this.codigoPaquete = codigoPaquete;
        this.codigoFactura = codigoFactura;
        this.idEstado = idEstado;
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

    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
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