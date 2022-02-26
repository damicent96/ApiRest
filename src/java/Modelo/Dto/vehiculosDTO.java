package Modelo.Dto;

public class vehiculosDTO {

    private Integer id_vehiculo;
    private String marca;
    private String modelo;
    private String chapa;

    public vehiculosDTO() {
    }

    public vehiculosDTO(Integer id_vehiculo, String marca, String modelo, String chapa) {
        this.id_vehiculo = id_vehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.chapa = chapa;
    }

    public vehiculosDTO(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }
}
