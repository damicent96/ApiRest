
package Modelo.Dto;

public class UsuarioRESTDTO {
    private Integer id;
    private String usuario;
    private String clave;
    private Boolean estado;
    private Integer idEmpresa;
    private String  nroIp;
    private String  token;
    
            

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNroIp() {
        return nroIp;
    }

    public void setNroIp(String nroIp) {
        this.nroIp = nroIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    
}
