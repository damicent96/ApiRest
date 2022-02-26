
package Modelo.Dto;

public class ubicacionesDTO {
    
    private Integer id_ubicacion;
    private String latitud;
    private String longitud;
    private String descripcion;

    public ubicacionesDTO() {
    }

    public ubicacionesDTO(Integer id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }
    
        public ubicacionesDTO(Integer id_ubicacion, String descripcion) {
        this.id_ubicacion = id_ubicacion;
        this.descripcion = descripcion;
    }
    
    

    public Integer getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(Integer id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
}
