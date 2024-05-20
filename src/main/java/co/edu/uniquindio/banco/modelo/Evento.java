package co.edu.uniquindio.banco.modelo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Evento {
    private String ciudad;
    private String nombreEvento;
    private String descripcion;
    private double precio;

    public Evento(String ciudad, String nombreEvento, String descripcion, double precio) {
        this.ciudad = ciudad;
        this.nombreEvento = nombreEvento;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}
