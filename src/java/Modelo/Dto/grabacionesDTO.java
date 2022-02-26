
package Modelo.Dto;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;

public class grabacionesDTO {
    private Integer id_grabacion;
    private Date fecha;
    private Time hora;
    private Time duracion;
    private DecimalFormat tamaño;
    private vehiculosDTO vehiculo;

    public Integer getId_grabacion() {
        return id_grabacion;
    }

    public void setId_grabacion(Integer id_grabacion) {
        this.id_grabacion = id_grabacion;
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

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public DecimalFormat getTamaño() {
        return tamaño;
    }

    public void setTamaño(DecimalFormat tamaño) {
        this.tamaño = tamaño;
    }

    public vehiculosDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(vehiculosDTO vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
