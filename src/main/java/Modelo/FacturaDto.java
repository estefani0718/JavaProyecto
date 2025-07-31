/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * DTO para la entidad Factura
 * Se utiliza para transferir datos entre capas (Controlador - Servicio - DAO)
 */
package Modelo;

/**
 * @author eeste
 */
public class FacturaDto {

    // -----------------------------
    // Atributos
    // -----------------------------

    /** Código único de la factura. Autogenerado al insertar. Se usa para listar o actualizar. */
    private Integer codigoFactura;

    /** Fecha de entrega de la factura. Se almacena como String para facilidad de formato. */
    private String fechaEntrega;

    /** Documento del usuario asociado a la factura (clave foránea hacia Usuarios). */
    private Long documentoUsuario;

    /** Nombre del usuario. No se almacena en la tabla, pero se usa para mostrar en listados. */
    private String nombreUsuario;

    /** Observaciones adicionales escritas al registrar la factura. */
    private String observaciones;

    /** Precio total de la factura (suma de valores + impuestos, etc.). */
    private Double precioTotal;

    /** Estado de la factura. Se maneja como texto ("Activo", "Anulado", etc.). */
    private String estado;
    /** Valor base del paquete antes de impuestos o cargos adicionales. */
    private Double valorPaquete;


    // -----------------------------
    // Getters y Setters
    // -----------------------------

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(Long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // con sus getter y setter
    public Double getValorPaquete() {
        return valorPaquete;
    }

    public void setValorPaquete(Double valorPaquete) {
        this.valorPaquete = valorPaquete;
    }
}
