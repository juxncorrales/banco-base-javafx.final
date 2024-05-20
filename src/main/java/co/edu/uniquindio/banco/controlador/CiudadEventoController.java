package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Evento;
import co.edu.uniquindio.banco.service.UniEventosService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class CiudadEventoController {

    @FXML
    private ComboBox<String> ciudadesComboBox;

    @FXML
    private ListView<String> eventosListView;

    @FXML
    private Button seleccionarButton;

    private UniEventosService service;

    @FXML
    public void initialize() {
        ObservableList<String> ciudades = FXCollections.observableArrayList("Bogotá", "Medellín", "Cali");
        ciudadesComboBox.setItems(ciudades);
        ciudadesComboBox.setOnAction(event -> cargarEventos());
        seleccionarButton.setOnAction(event -> seleccionarEvento());
    }

    public void setService(UniEventosService service) {
        this.service = service;
    }

    private void cargarEventos() {
        String ciudadSeleccionada = ciudadesComboBox.getValue();
        if (ciudadSeleccionada != null) {
            List<Evento> eventos = service.obtenerEventosPorCiudad(ciudadSeleccionada);
            eventosListView.getItems().clear();
            for (Evento evento : eventos) {
                eventosListView.getItems().add(evento.getNombreEvento() + " - " + evento.getDescripcion() + " - $" + evento.getPrecio());
            }
        }
    }

    private void seleccionarEvento() {
        String eventoSeleccionado = eventosListView.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado != null) {
            // Aquí agregar lógica para proceder a la compra
            System.out.println("Evento seleccionado: " + eventoSeleccionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección de Evento");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un evento.");
            alert.showAndWait();
        }
    }
}
