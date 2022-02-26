package Modelo.Dto;

public class perfilesDTO {

    private Integer id_perfil;
    private String descripcion;
    private String comentario;
    private Boolean estado;

    public perfilesDTO() {
    }

    public perfilesDTO(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public perfilesDTO(Integer id_perfil, String descripcion) {
        this.id_perfil = id_perfil;
        this.descripcion = descripcion;
    }

    public perfilesDTO(Integer id_perfil, String descripcion, String comentario) {
        this.id_perfil = id_perfil;
        this.descripcion = descripcion;
        this.comentario = comentario;
    }

    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
