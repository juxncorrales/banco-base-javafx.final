package co.edu.uniquindio.banco.modelo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Compra {
    private Usuario usuario;
    private Evento evento;
    private int cantidadEntradas;
    private String localidad;
    private double total;

    public Compra(Usuario usuario, Evento evento, int cantidadEntradas, String localidad, double total) {
        this.usuario = usuario;
        this.evento = evento;
        this.cantidadEntradas = cantidadEntradas;
        this.localidad = localidad;
        this.total = total;
    }

}
