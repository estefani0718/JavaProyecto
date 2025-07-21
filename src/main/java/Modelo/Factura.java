/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author eeste
 */
/**
 * Clase modelo que representa la entidad Factura.
 * Cada factura tiene una fecha de entrega, un cliente asociado (documento_usuario),
 * observaciones y el precio total.
 */
public class Factura {
    private int codigoFactura;        // ID único de la factura
    private Date fechaEntrega;        // Fecha en que se entrega la factura
    private long documentoUsuario;    // Documento del cliente (relación con tabla Usuarios)
    private String observaciones;     // Comentarios u observaciones sobre la factura
    private double precioTotal;       // Valor total de la factura

    // Getters y Setters
    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
