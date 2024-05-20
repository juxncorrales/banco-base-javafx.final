package co.edu.uniquindio.banco.modelo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usuario {
    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String contrasena;

    public Usuario(String cedula, String nombreCompleto, String telefono, String email, String contrasena) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

}
