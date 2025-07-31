/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ClaseConexion;
import Modelo.EstadosDao;
import Modelo.FacturaDao;
import Modelo.FacturaDto;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author eeste
 */

/**
 * Servicio que actúa como intermediario entre el controlador REST y el DAO.
 * Encapsula la lógica de negocio relacionada con las facturas.
 * 
 * No maneja conexiones manualmente; el DAO se encarga de ellas.
 */
public class FacturaServicios {

    private final FacturaDao facturaDao = new FacturaDao(); // Instancia única del DAO

    /**
     * Lista todas las facturas registradas, incluyendo nombre del usuario y estado como texto.
     * @return Lista de FacturaDto
     */
    public List<FacturaDto> listarFacturas() {
        return facturaDao.listar();
    }

    /**
     * Obtiene una factura por su ID (código de factura).
     * @param id Código de la factura
     * @return FacturaDto correspondiente o null si no se encuentra
     */
    public FacturaDto obtenerFacturaPorId(int id) {
        return facturaDao.obtenerPorId(id);
    }

    /**
    * Obtiene la primera factura asociada al documento del usuario.
    * @param documento Documento del usuario
    * @return Una factura si existe, de lo contrario null
    */
   public FacturaDto obtenerPrimeraFacturaPorDocumento(Long documento) {
       List<FacturaDto> facturas = facturaDao.obtenerPorDocumento(documento);
       // Si la lista de facturas está vacía, retorna null.
       // De lo contrario, retorna la primera factura de la lista.
        return facturas.isEmpty() ? null : facturas.get(0);
   }
    /**
     * Registra una nueva factura.
     * @param facturaDto Objeto DTO con los datos necesarios para registrar
     * @return true si fue registrada correctamente
     */
    public boolean registrarFactura(FacturaDto facturaDto) {
        return facturaDao.guardar(facturaDto);
    }

    /**
     * Actualiza una factura existente por su ID (códigoFactura).
     * @param facturaDto Objeto DTO con los datos actualizados
     * @return true si la actualización fue exitosa
     */
    public boolean actualizarFacturaPorId(FacturaDto facturaDto) {
        return facturaDao.actualizarPorId(facturaDto);
    }

    /**
     * Actualiza una factura existente buscándola por documento del usuario.
     * @param facturaDto Objeto DTO con los nuevos datos
     * @return true si fue actualizada correctamente
     */
    public boolean actualizarFacturaPorDocumento(FacturaDto facturaDto) {
        return facturaDao.actualizarPorDocumento(facturaDto);
    }

    /**
     * Elimina lógicamente una factura (cambia su estado a "Inactivo", "Anulado", etc.).
     * @param id ID de la factura.
     * @return true si el cambio de estado fue exitoso
     */
    public boolean eliminarFactura(int id) {
        return facturaDao.eliminar(id);
    }
}