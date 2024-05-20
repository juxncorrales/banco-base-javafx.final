package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.app.MainApp;
import co.edu.uniquindio.banco.modelo.Usuario;
import co.edu.uniquindio.banco.service.UniEventosService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistroController {
    @FXML
    private TextField cedulaField;
    @FXML
    private TextField nombreCompletoField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private Button registroButton;

    private UniEventosService service;
    private MainApp mainApp;

    @FXML
    public void initialize() {
        registroButton.setOnAction(event -> registrarUsuario());
    }

    public void setService(UniEventosService service) {
        this.service = service;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void registrarUsuario() {
        String cedula = cedulaField.getText();
        String nombreCompleto = nombreCompletoField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();
        String contraseña = contraseñaField.getText();

        Usuario usuario = new Usuario(cedula, nombreCompleto, telefono, email, contraseña);
        boolean registrado = service.registrarUsuario(usuario);

        if (registrado) {
            // Redirigir a la pantalla de login
            Stage stage = (Stage) registroButton.getScene().getWindow();
            mainApp.mostrarPantallaLogin(stage);
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Registro");
            alert.setHeaderText(null);
            alert.setContentText("El usuario ya está registrado.");
            alert.showAndWait();
        }
    }
}
