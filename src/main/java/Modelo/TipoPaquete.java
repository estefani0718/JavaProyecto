/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eeste
 */
import java.math.BigDecimal;

/**
 * Esta clase representa el modelo de datos para la tabla TipoPaquete.
 * Aquí se definen los atributos que coinciden con las columnas de la base de datos.
 * Se utiliza como puente entre la base de datos y el código Java.
 */
public class TipoPaquete {
    private int codigoTpaquete;           // Llave primaria del paquete
    private String nombrePaquete;         // Nombre del paquete
    private String detallesAdicionales;   // Información adicional sobre el paquete
    private int codigoTransporteUsuario;  // Llave foránea hacia TransporteUsuario
    private int codigoCategoria;          // Llave foránea hacia CategoriaPaquete
    private int codigoFactura;            // Llave foránea hacia Factura
    private int idEstado;                 // Llave foránea hacia Estados (activo, entregado, eliminado)
    private String origen;                // Origen del paquete
    private String destino;               // Destino del paquete
    private BigDecimal valorPaquete;      // Costo o valor del paquete

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

    public int getCodigoTransporteUsuario() {
        return codigoTransporteUsuario;
    }

    public void setCodigoTransporteUsuario(int codigoTransporteUsuario) {
        this.codigoTransporteUsuario = codigoTransporteUsuario;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getValorPaquete() {
        return valorPaquete;
    }

    public void setValorPaquete(BigDecimal valorPaquete) {
        this.valorPaquete = valorPaquete;
    }
}

