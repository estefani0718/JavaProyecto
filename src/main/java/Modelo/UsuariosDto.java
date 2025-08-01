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

DTO para representar los datos del usuario con los nombres legibles
de entidades relacionadas en lugar de sus IDs numéricos.
*/
public class UsuariosDto {
    private int id;
    private String nombre_usuario; 
    private long documento_usuario;
    private String tipo_documento;  // Ej: "Cédula"
    private String genero_usuario;
    private String direccion_usuario;
    private String telefono_usuario;
    private String correo;
    private String estado;          
    private String rol;             
    private String residencia;      
    private String usuario;
    private String contrasena;
    
    
     public UsuariosDto(int id, String nombre_usuario, long documento_usuario, String tipo_documento, String genero_usuario, String direccion_usuario, String telefono_usuario, String correo, String estado, String rol, String residencia, String usuario, String contrasena) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.documento_usuario = documento_usuario;
        this.tipo_documento = tipo_documento;
        this.genero_usuario = genero_usuario;
        this.direccion_usuario = direccion_usuario;
        this.telefono_usuario = telefono_usuario;
        this.correo = correo;
        this.estado = estado;
        this.rol = rol;
        this.residencia = residencia;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    UsuariosDto() {
        
    }

    // Getters y setters (puedes generarlos con tu IDE)
    
    public int getId() {
    return id;
}

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public long getDocumento_usuario() {
        return documento_usuario;
    }

    public void setDocumento_usuario(long documento_usuario) {
        this.documento_usuario = documento_usuario;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getGenero_usuario() {
        return genero_usuario;
    }

    public void setGenero_usuario(String genero_usuario) {
        this.genero_usuario = genero_usuario;
    }

    public String getDireccion_usuario() {
        return direccion_usuario;
    }

    public void setDireccion_usuario(String direccion_usuario) {
        this.direccion_usuario = direccion_usuario;
    }

    public String getTelefono_usuario() {
        return telefono_usuario;
    }

    public void setTelefono_usuario(String telefono_usuario) {
        this.telefono_usuario = telefono_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
