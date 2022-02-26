
package Modelo.Dto;

import java.sql.Date;
import java.sql.Time;

public class notificacionesDTO {
    private Integer id_notificacion;
    private Date fecha;
    private Time hora;
    private String descripcion;
    private ubicacionesDTO ubicacion;
    private vehiculosDTO vehiculo;
    private usuariosDTO usuario;

    public Integer getId_notificacion() {
        return id_notificacion;
    }

    public void setId_notificacion(Integer id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ubicacionesDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(ubicacionesDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public vehiculosDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(vehiculosDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public usuariosDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(usuariosDTO usuario) {
        this.usuario = usuario;
    }
    
}
