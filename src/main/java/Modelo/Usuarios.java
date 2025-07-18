/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eeste
 */
public class Usuarios {

    private int id; // NUEVO CAMPO
    private String nombre_usuario;
    private long documento_usuario;
    private int codigo_Tdocumento;
    private String genero_usuario;
    private String direccion_usuario;
    private String telefono_usuario;
    private String correo;
    private int id_estado;
    private int codigo_rol;
    private int codigo_residencia;
    private int codigo_tipoC;
    private String usuario;
    private String contrasena;

    public Usuarios() {}

    // Constructor completo incluyendo ID
    public Usuarios(int id, String nombre_usuario, long documento_usuario, int codigo_Tdocumento, String genero_usuario,
                    String direccion_usuario, String telefono_usuario, String correo, int id_estado,
                    int codigo_rol, int codigo_residencia, int codigo_tipoC, String usuario, String contrasena) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.documento_usuario = documento_usuario;
        this.codigo_Tdocumento = codigo_Tdocumento;
        this.genero_usuario = genero_usuario;
        this.direccion_usuario = direccion_usuario;
        this.telefono_usuario = telefono_usuario;
        this.correo = correo;
        this.id_estado = id_estado;
        this.codigo_rol = codigo_rol;
        this.codigo_residencia = codigo_residencia;
        this.codigo_tipoC = codigo_tipoC;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Constructor sin ID (para guardar)
    public Usuarios(String nombre_usuario, long documento_usuario, int codigo_Tdocumento, String genero_usuario,
                    String direccion_usuario, String telefono_usuario, String correo, int id_estado,
                    int codigo_rol, int codigo_residencia, int codigo_tipoC, String usuario, String contrasena) {
        this.nombre_usuario = nombre_usuario;
        this.documento_usuario = documento_usuario;
        this.codigo_Tdocumento = codigo_Tdocumento;
        this.genero_usuario = genero_usuario;
        this.direccion_usuario = direccion_usuario;
        this.telefono_usuario = telefono_usuario;
        this.correo = correo;
        this.id_estado = id_estado;
        this.codigo_rol = codigo_rol;
        this.codigo_residencia = codigo_residencia;
        this.codigo_tipoC = codigo_tipoC;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre_usuario() { return nombre_usuario; }
    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }

    public long getDocumento_usuario() { return documento_usuario; }
    public void setDocumento_usuario(long documento_usuario) { this.documento_usuario = documento_usuario; }

    public int getCodigo_Tdocumento() { return codigo_Tdocumento; }
    public void setCodigo_Tdocumento(int codigo_Tdocumento) { this.codigo_Tdocumento = codigo_Tdocumento; }

    public String getGenero_usuario() { return genero_usuario; }
    public void setGenero_usuario(String genero_usuario) { this.genero_usuario = genero_usuario; }

    public String getDireccion_usuario() { return direccion_usuario; }
    public void setDireccion_usuario(String direccion_usuario) { this.direccion_usuario = direccion_usuario; }

    public String getTelefono_usuario() { return telefono_usuario; }
    public void setTelefono_usuario(String telefono_usuario) { this.telefono_usuario = telefono_usuario; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public int getId_estado() { return id_estado; }
    public void setId_estado(int id_estado) { this.id_estado = id_estado; }

    public int getCodigo_rol() { return codigo_rol; }
    public void setCodigo_rol(int codigo_rol) { this.codigo_rol = codigo_rol; }

    public int getCodigo_residencia() { return codigo_residencia; }
    public void setCodigo_residencia(int codigo_residencia) { this.codigo_residencia = codigo_residencia; }

    public int getCodigo_tipoC() { return codigo_tipoC; }
    public void setCodigo_tipoC(int codigo_tipoC) { this.codigo_tipoC = codigo_tipoC; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}