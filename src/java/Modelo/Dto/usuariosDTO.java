
package Modelo.Dto;

public class usuariosDTO {
    private Integer id_usuario;
    private String nombres; 
    private String apellidos; 
    private String usuario; 
    private String contrasena; 
    private Boolean estado;

    public usuariosDTO() {
    }

    public usuariosDTO(Integer id_usuario, String nombres, String apellidos) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    
        

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    private perfilesDTO perfil;
    

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuarios) {
        this.usuario = usuarios;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public perfilesDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(perfilesDTO perfil) {
        this.perfil = perfil;
    }
    
}
