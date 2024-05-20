package co.edu.uniquindio.banco.service;


import co.edu.uniquindio.banco.modelo.Evento;
import co.edu.uniquindio.banco.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UniEventosService {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    public UniEventosService() {
        // Inicializar con algunos datos de ejemplo
        eventos.add(new Evento("Bogotá", "Concierto de Rock", "Gran concierto de rock", 100.0));
        eventos.add(new Evento("Medellín", "Festival de Jazz", "Festival de jazz internacional", 150.0));
    }

    public boolean registrarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail())) {
                return false; // Usuario ya registrado
            }
        }
        usuarios.add(usuario);
        return true;
    }

    public Usuario iniciarSesion(String email, String contraseña) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getContrasena().equals(contraseña)) {
                return u;
            }
        }
        return null; // Usuario no encontrado
    }

    public List<Evento> obtenerEventosPorCiudad(String ciudad) {
        List<Evento> eventosPorCiudad = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getCiudad().equalsIgnoreCase(ciudad)) {
                eventosPorCiudad.add(e);
            }
        }
        return eventosPorCiudad;
    }

    public double calcularTotal(double precioEntrada, int cantidad) {
        return precioEntrada * cantidad;
    }
}
